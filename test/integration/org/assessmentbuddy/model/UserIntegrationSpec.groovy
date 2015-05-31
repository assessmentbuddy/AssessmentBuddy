package org.assessmentbuddy.model

import grails.test.spock.IntegrationSpec

class UserIntegrationSpec extends IntegrationSpec {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        
        given: "A brand new user"
        def me = new User(userName: "dhovemey", passwordHash: "xyz123", fullName: "David Hovemeyer", email: "dhovemey@ycp.edu")
        
        when: "The user is saved"
        me.save()
        
        then: "The user was saved successfully"
        me.errors.errorCount == 0
        me.id != null
        User.get(me.id).userName == me.userName
    }
}
