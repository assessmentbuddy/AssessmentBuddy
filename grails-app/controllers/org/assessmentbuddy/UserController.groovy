package org.assessmentbuddy

import grails.util.Mixin

import org.assessmentbuddy.model.BaseException
import org.assessmentbuddy.model.PermissionsCheck
import org.assessmentbuddy.model.PermissionsException
import org.assessmentbuddy.model.Program
import org.assessmentbuddy.model.Role
import org.assessmentbuddy.model.SaveFailedException
import org.assessmentbuddy.model.StandardExceptionHandlers
import org.assessmentbuddy.model.User

@Mixin(PermissionsCheck)
class UserController extends StandardExceptionHandlers {
    def bcryptService
    def userService

    def index() {
        // For admin, show paginated list of users, otherwise, show current user
        def users = session.user.isAdmin() ? User.list(params) : [ session.user ]
        def userCount = users.size()
        
        println "Paginating ${userCount} user(s)"
        
        [ users: users, userCount: userCount ]
    }
    
    def create() {
        permCheck(canEditUser(null), session.user, "create/edit user")
        // Edit a new user
        redirect( action: 'edit' )
    }
    
    def edit() {
        permCheck(canEditUser(params.id?.toLong()), session.user, "create/edit user")

        User userToEdit
        def password = "", passwordConfirm = ""
        
        if (flash.userToEdit) {
            // These are from previous edit form entries
            (userToEdit, password, passwordConfirm) = loadForReediting(flash)
        } else {
            if (!params.id) {
                // Create new user
                userToEdit = new User()
            } else {
                // Edit existing user
                userToEdit = userService.findUserForId(params.id.toLong())
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
            programs: Program.list().sort { it.name },
            roleIds: userToEdit.roles.collect { it.id }
        ]
    }
    
    def save() {
        permCheck(canEditUser(params.id?.toLong()), session.user, "create/edit user")

        def userToEditParams = params.userToEdit

        // FIXME: permissions check (make sure logged in user is an admin
        // or is the same as the user being edited)

        // If a password was specified, make sure the confirmation password matches
        if (params.password && params.password != "") {
            // A password was specified
            
            println "Checking passwords: ${params.password}, ${params.passwordConfirm}"
            
            // Ensure that password confirmation field matches
            if (params.password != params.passwordConfirm) {
                // Confirmation field doesn't match, redirect to edit page
                flash.message = "Password confirmation field does not match"
                storeForReediting(flash, new User(userToEditParams), params)
                redirect( action: 'edit', id: userToSave.id )
                return
            }
        }
        
        // If a new user is being created, make sure that a password was specified
        if (!params.id && (!params.password || params.password == '')) {
            flash.message = "Please specify a password"
            storeForReediting(flash, new User(userToEditParams), params)
            redirect( action: 'edit' )
            return
        }
        
        // Marshal information about role(s) to delete and add.
        // Only a full admin is allowed to edit user roles.
        def rolesToDelete = []
        def roleToAddParams = [:]
        if (session.user.isAdmin()) {
            // Find roles to delete.  The roleIds hidden form parameter
            // specifies the ids of the user's roles.
            if (params.roleIds) {
                def roleIds = params.roleIds.split(/\s+/).collect { it.toLong() }
                rolesToDelete = roleIds.findAll { params["rolesToDelete.${roleId}"] }
            }
            // Set role to add parameters
            roleToAddParams = params.roleToAdd
        }

        // Attempt to persist changes to user and roles
        try {
            userService.saveUserAndUpdateRoles(params?.id, params?.password, userToEditParams, rolesToDelete, roleToAddParams)
        } catch (SaveFailedException e) {
            // Failed to save, redirect to edit page
            flash.message = "Could not save user: ${e.getMessage()}"
            storeForReediting(flash, e.getBean(), params)
            redirect( action: 'edit', id: params.id )
            return
        }
        
        // save successful
        flash.message = "User ${params.userToEdit.userName} saved successfully"
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
