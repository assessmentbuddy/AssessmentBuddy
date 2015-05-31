package org.assessmentbuddy.model

/**
 * A user.
 */
class User {
    String userName
    String passwordHash
    String fullName
    String email

    static constraints = {
        userName unique: true
    }
}
