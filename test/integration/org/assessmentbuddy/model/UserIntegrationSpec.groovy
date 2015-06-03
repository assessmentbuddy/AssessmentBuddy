package org.assessmentbuddy.model

import grails.test.spock.IntegrationSpec

class UserIntegrationSpec extends IntegrationSpec {
    def bcryptService
    def initialDataService

    def setup() {
        initialDataService.createInitialPrograms()
        initialDataService.createInitialUsersAndRoles()
    }

    def cleanup() {
    }
    
    def "change a user property"() {
        given: "an existing user"
        
        when: "a property is changed"
        def loadedUser = User.findByUserName("dhovemey")
        loadedUser.email = "david.hovemeyer@gmail.com"
        loadedUser.save()
        
        then: "changed property is loaded from the database"
        User.findByUserName("dhovemey").email == "david.hovemeyer@gmail.com"
    }
    
    def "check that user role exists"() {
        given: "an existing user"
        
        when: "users are loaded"
        def compSci = Program.findByName("Computer Science")
        def admin = User.findByUserName("admin")
        def u = User.findByUserName("dhovemey")
        
        then: "the users' roles exist"
        admin != null
        u != null
        
        def adminRoles = admin.roles
        adminRoles.size() == 1
        adminRoles[0].roleType == Role.RoleType.ADMIN
        adminRoles[0].scope == Role.Scope.ALL_PROGRAMS
        adminRoles[0].program == null

        def roles = u.roles
        roles.size() == 1
        roles[0].roleType == Role.RoleType.REPORTER
        roles[0].scope == Role.Scope.ONE_PROGRAM
        roles[0].program.id == compSci.id
    }
}
