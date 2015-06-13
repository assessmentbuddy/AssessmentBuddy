package org.assessmentbuddy

import org.assessmentbuddy.model.Program
import org.assessmentbuddy.model.Role
import org.assessmentbuddy.model.User

class UserController {
    def bcryptService

    def index() {
        // For admin, show paginated list of users, otherwise, show current user
        def users = session.user.isAdmin() ? User.list(params) : [ session.user ]
        def userCount = users.size()
        
        println "Paginating ${userCount} user(s)"
        
        [ users: users, userCount: userCount ]
    }
    
    def create() {
        // Edit a new user
        redirect( action: 'edit' )
    }
    
    def edit() {
        User userToEdit
        def password = "", passwordConfirm = ""
        
        if (!params.id) {
            // If the user to edit is in flash storage, use that,
            // otherwise create a new one
            if (flash.userToEdit) {
                // These are from previous edit form entries
                userToEdit = flash.userToEdit
                password = flash.password
                passwordConfirm = flash.passwordConfirm
            } else {
                userToEdit = new User()
            }
        } else {
            // Edit existing user
            if (flash.userToEdit) {
                // User being edited, may indicate errors
                userToEdit = flash.userToEdit
            } else {
                // Load user from database
                userToEdit = User.get(params.id)
            }
            if (!userToEdit) {
                response.sendError(404)
                return
            }
        }
        
        [
            userToEdit: userToEdit,
            password: password,
            passwordConfirm: passwordConfirm,
            roleTypes: Role.RoleType.values(),
            scopes: Role.Scope.values(),
            programs: Program.list().sort { it.name }
        ]
    }
    
    def save() {
        def userToEditParams = params.userToEdit
        def roleToAddParams = params.roleToAdd

        // attempt to save
        User userToSave
        
        if (params.id) {
            // Updating an existing user: load from the database
            // and then update the properties
            userToSave = User.get(params.id.toLong())
            userToSave.properties = userToEditParams
            println "userToSave.id=${userToSave.id}"
            println("userToSave.email=${userToSave.email}")
        } else {
            // Creating a new user
            userToSave = new User(userToEditParams) // create user object based on form params
        }
        
        if (userToEditParams.password && userToEditParams.password != "") {
            // FIXME: ensure that password and passwordConfirm match

            // Password was specified, hash it
            userToSave.passwordHash = bcryptService.hashPassword(userToEditParams.password)
        } else if (params.id) {
            // An existing user is being edited: if no password was specified,
            // then keep the existing password
            userToSave.passwordHash = User.get(params.id).passwordHash
            println "Reusing existing password hash: ${userToSave.passwordHash}"
        }
        
        if (!userToSave.save(flush: true)) {
            // Failed to save, redirect to edit page
            flash.message = "Could not save user" // TODO: diagnostics
            flash.password = userToEditParams.password
            flash.passwordConfirm = userToEditParams.passwordConfirm
            flash.userToEdit = userToSave
            redirect( action: 'edit', id: userToSave.id )
            return
        }
        
        // TODO: delete/add roles if requested
        println "Role to add roleType: ${roleToAddParams['roleType']}"
        
        // save successful
        flash.message = "User ${userToSave.userName} saved successfully"
        flash.userToEdit = null
        redirect( action: "index" )
    }
}
