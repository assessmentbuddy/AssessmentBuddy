package org.assessmentbuddy.model

/**
 * Mixin to allow controllers to perform permissions checks.
 */
class PermissionsCheck {
    /**
     * Return a predicate to check whether the logged in user has
     * permission to edit user with given user id (null if creating
     * a new user.)
     */
    Closure canEditUser(Long userId) {
        return { loggedInUser -> (loggedInUser.isAdmin() || (userId != null && loggedInUser.id == userId)) }
    }
    
    /**
     * Return a predicate to check whether the logged in user
     * has permission to create and edit programs.
     * Only full admins have this privilege.
     */
    Closure canEditProgram() {
        return { loggedInUser -> loggedInUser.isAdmin() }
    }
    
    /**
     * Return a predicate to check whether the logged-in user has
     * permission to create or edit an outcome in the given program.
     */
    Closure canEditOutcome(Program program) {
        return { loggedInUser -> program != null && loggedInUser.hasAdminRightsIn(program) }
    }

    /**
     * Do a permissions check, throwing a PermissionsException if the check fails.
     */
    void permCheck(Closure check, User loggedInUser, String msgIfFail) {
        if (!check(loggedInUser)) {
            throw new PermissionsException(loggedInUser, msgIfFail)
        }
    }
}
