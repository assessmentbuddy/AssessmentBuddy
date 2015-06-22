package org.assessmentbuddy.model

class PermissionsCheck {
    /**
     * Check whether given logged in User can edit a user
     * account with given id (null for editing a new user.)
     * Throws PermissionsException if not.
     */
    static void canEditUser(User loggedInUser, Long id) {
        if (!(loggedInUser.isAdmin() || (id != null && loggedInUser.id == id))) {
            throw new PermissionsException(loggedInUser, "edit/create user")
        }
    }
}
