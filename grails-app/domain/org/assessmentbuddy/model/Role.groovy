package org.assessmentbuddy.model

class Role {
    enum RoleType {
        /** An admin can do anything, add users, etc. */
        ADMIN,
        /** A reporter can add measures and view data.. */
        REPORTER,
        /** A viewer can view data. */
        VIEWER,
    }
    
    enum Scope {
        /** Role is limited to a specified program. */
        ONE_PROGRAM,
        /** Role extends to all programs. */
        ALL_PROGRAMS,
    }
    
    RoleType roleType
    Scope scope
    Program program

    static belongsTo = [ user : User ]
    
    static constraints = {
        program nullable: true
    }
}
