package org.assessmentbuddy.model

import grails.transaction.Transactional

import org.assessmentbuddy.model.NoSuchIdException

@Transactional
class UserService {
    def bcryptService
    
    User findUserForId(long id) {
        User user = User.get(id)
        if (!user) {
            throw new NoSuchIdException("Could not find user for id ${id}", id)
        }
        return user
    }
    
    def saveUserAndUpdateRoles(userId, password, userToEditParams, rolesToDelete, roleToAddParams) {
        // attempt to save
        User userToSave
        
        if (userId) {
            // Updating an existing user: load from the database
            // and then update the properties
            userToSave = User.get(userId)
            if (!userToSave) {
                throw new NoSuchIdException("Could not find user for id ${userId}", userId)
            }
            userToSave.properties = userToEditParams
            if (password != "") {
                // A password was specified, update the password hash
                userToSave.passwordHash = bcryptService.hashPassword(password)
            }
        } else {
            // Creating a new user
            userToSave = new User(userToEditParams) // create user object based on form params
            userToSave.passwordHash = bcryptService.hashPassword(password)
        }
        
        if (!userToSave.save()) {
            /*
            // Failed to save, redirect to edit page
            flash.message = "Could not save user"
            storeForReediting(flash, userToSave, params)
            redirect( action: 'edit', id: userToSave.id )
            return
            */
           throw new SaveFailedException("Could not save user", userToSave)
        }
        
        /*
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
        */
        
    }
}
