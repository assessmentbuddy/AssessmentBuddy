package org.assessmentbuddy.model

import grails.test.spock.IntegrationSpec

class UserIntegrationSpec extends IntegrationSpec {

    def setup() {
    }

    def cleanup() {
    }

    def "save an initial user"() {
        
        given: "A brand new user"
        def me = new User(userName: "dhovemey", passwordHash: "xyz123", fullName: "David Hovemeyer", email: "dhovemey@ycp.edu")
        
        when: "The user is saved"
        me.save()
        
        then: "The user was saved successfully"
        me.errors.errorCount == 0
        me.id != null
        User.get(me.id).userName == me.userName
    }
    
    def "change a user property"() {
        given: "an existing user"
        def me = new User(userName: "dhovemey", passwordHash: "xyz123", fullName: "David Hovemeyer", email: "dhovemey@ycp.edu")
        me.save(failOnError: true)
        
        when: "a property is changed"
        def loadedUser = User.get(me.id)
        loadedUser.email = "david.hovemeyer@gmail.com"
        loadedUser.save()
        
        then: "changed property is loaded from the database"
        User.get(me.id).email == "david.hovemeyer@gmail.com"
    }
}
