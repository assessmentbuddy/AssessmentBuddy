package org.assessmentbuddy.model

import grails.test.spock.IntegrationSpec

class ProgramIntegrationSpec extends IntegrationSpec {
    def initialDataService

    def setup() {
        initialDataService.createInitialPrograms()
        initialDataService.createInitialOutcomesAndIndicators()
    }

    def cleanup() {
    }

    void "find program and check number of outcomes"() {
        given: "an existing program"
        
        when: "loading the existing program"
        def p = Program.findByName("Computer Science")
        
        then: "check properties and number of outcomes"
        p.outcomes.size() == 2
        
    }
}
