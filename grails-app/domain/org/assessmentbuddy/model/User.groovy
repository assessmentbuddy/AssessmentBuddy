package org.assessmentbuddy.model

/**
 * A user.
 */
class User {
    String userName
    String passwordHash
    String fullName
    String email
    
    static hasMany = [ roles: Role ]

    static constraints = {
        userName unique: true, size: 2..64
        passwordHash size: 1..64
        fullName size: 1..200
        email size: 3..200
    }
}
