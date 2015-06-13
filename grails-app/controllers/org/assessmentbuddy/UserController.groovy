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
            userToEdit = User.get(params.id)
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
        // attempt to save
        User userToSave = new User(params) // create user object based on form params
        
        // FIXME: ensure that password and passwordConfirm match
        
        if (params.password && params.password != "") {
            userToSave.passwordHash = bcryptService.hashPassword(params.password)
        }
        
        if (!userToSave.save()) {
            // Failed to save, redirect to edit page
            flash.message = "Could not save user" // TODO: diagnostics
            flash.password = params.password
            flash.passwordConfirm = params.passwordConfirm
            flash.userToEdit = userToSave
            redirect( action: 'edit' )
            return
        }
        
        // save successful
        flash.message = "User ${userToSave.userName} saved successfully"
        redirect( action: "index" )
    }
}
