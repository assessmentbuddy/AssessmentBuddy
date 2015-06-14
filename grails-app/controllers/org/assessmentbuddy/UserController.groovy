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
                (userToEdit, password, passwordConfirm) = loadForReediting(flash)
            } else {
                userToEdit = new User()
            }
        } else {
            // Edit existing user
            if (flash.userToEdit) {
                // User being edited, may indicate errors
                (userToEdit, password, passwordConfirm) = loadForReediting(flash)
            } else {
                // Load user from database
                userToEdit = User.get(params.id)
            }
            if (!userToEdit) {
                response.sendError(404)
                return
            }
        }
        
        // make sure userToEdit is attached if it is an existing User
        if (userToEdit.id && !userToEdit.isAttached()) {
            userToEdit.attach()
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
        // FIXME: permissions check (make sure logged in user is an admid
        // or is the same as the user being edited)
        
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
        
        if (params.password && params.password != "") {
            // A password was specified
            
            println "Checking passwords: ${params.password}, ${params.passwordConfirm}"
            
            // Ensure that password confirmation field matches
            if (params.password != params.passwordConfirm) {
                // Confirmation field doesn't match, redirect to edit page
                flash.message = "Password confirmation field does not match"
                storeForReediting(flash, userToSave, params)
                redirect( action: 'edit', id: userToSave.id )
                return
            }

            // Password was specified, hash it
            userToSave.passwordHash = bcryptService.hashPassword(params.password)
        } else if (params.id) {
            // An existing user is being edited: if no password was specified,
            // then keep the existing password
            userToSave.passwordHash = User.get(params.id).passwordHash
            println "Reusing existing password hash: ${userToSave.passwordHash}"
        }
        
        if (!userToSave.save(flush: true)) {
            // Failed to save, redirect to edit page
            flash.message = "Could not save user"
            storeForReediting(flash, userToSave, params)
            redirect( action: 'edit', id: userToSave.id )
            return
        }
        
        if (session.user.isAdmin()) {
            // delete roles if requested
            def roleIds = User.get(userToSave.id).roles.collect { it.id }
            int deletedRoles = 0
            roleIds.each { roleId ->
                def deleteCheck = params["rolesToDelete.${roleId}"]
                if (deleteCheck) {
                    println "Delete role ${roleId}"
                    def role = Role.get(roleId)
                    if (role) {
                        println "Deleting role ${roleId} from user ${userToSave.id}"
                        userToSave.removeFromRoles(role)
                        role.delete()
                        deletedRoles++
                    }
                }
            }
            if (deletedRoles > 0) {
                userToSave.save(flush: true)
            }

            // add role if requested
            if (roleToAddParams.roleType && roleToAddParams.scope) {
                // roleType and scope are enum names
                def roleType = Role.RoleType.valueOf(roleToAddParams.roleType)
                def scope = Role.Scope.valueOf(roleToAddParams.scope)

                // program is a program id
                def program = roleToAddParams.program ? Program.get(roleToAddParams.program.toLong()) : null
                
                if (scope == Role.Scope.ONE_PROGRAM && !program) {
                    flash.message = "A program must be specified for a 'One program' role"
                    storeForReediting(flash, userToSave, params)
                    redirect(action: 'edit', id: userToSave.id)
                    return
                }
                
                if (scope == Role.Scope.ALL_PROGRAMS) {
                    // We don't want a program to be specified if the scope is
                    // ALL_PROGRAMS
                    program = null
                }

                def roleToAdd = new Role(roleType: roleType, scope: scope, program: program)
                userToSave.addToRoles(roleToAdd)
                userToSave.save(flush: true)
            }
        }
        
        // save successful
        flash.message = "User ${userToSave.userName} saved successfully"
        flash.userToEdit = null
        redirect( action: "index" )
    }
    
    private void storeForReediting(m, u, p) {
        m.userToEdit = u
        m.password = p.password
        m.passwordConfirm = p.passwordConfirm
    }
    
    private List loadForReediting(m) {
        return [m.userToEdit, m.password, m.passwordConfirm]
    }
}
