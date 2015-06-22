package org.assessmentbuddy.model

class PermissionsException extends BaseException {
    PermissionsException(User loggedInUser, String what) {
        super("User ${loggedInUser.userName} does not have permission to ${what}")
    }
}
