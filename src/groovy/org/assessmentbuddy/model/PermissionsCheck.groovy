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
     * Return a predicate to check whethre the logged-in user
     * has permission to create or edit an indicator in the
     * given program associated with the given outcome.
     * If the outcome is not specified (null), then the
     * predicate answers whether the user may create a new
     * indicator for the program.
     */
    Closure canEditIndicator(Program program, Outcome outcome) {
        return { loggedInUser ->
            // Make sure program is specified
            if (!program) {
                return false
            }
            // User must have admin rights for the program
            if (!loggedInUser.hasAdminRightsIn(program)) {
                return false
            }
            // If an outcome is specified,
            // make sure it is really in the program
            if (outcome && (!outcome.program || outcome.program.id != program.id)) {
                return false
            }
            return true
        }
    }

    /**
     * Return a predicate that checks whether the logged-in user
     * can create/edit academic years.
     */
    Closure canEditAcademicYear() {
        return { loggedInUser -> loggedInUser.isAdmin() }
    }
    
    /**
     * Return a predicate that checks whether the logged-in user
     * can create/edit rubrics in the specified program.
     */
    Closure canEditRubric(Program program) {
        return { loggedInUser -> program && loggedInUser.hasAdminRightsIn(program) }
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
