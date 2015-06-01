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
}
