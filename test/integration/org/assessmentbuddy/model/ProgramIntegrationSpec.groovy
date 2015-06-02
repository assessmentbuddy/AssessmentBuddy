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
    
    void "find program and check that correct outcomes are found"() {
        given: "an existing program"
        
        when: "loading an existing program"
        def p = Program.findByName("Computer Science")
        
        then: "its outcomes should be the expected ones"
        def (outcomeA, outcomeB) = p.outcomes.sort { it.shortName }
        
        outcomeA.shortName == "a"
        outcomeA.description.startsWith("An ability to apply knowledge of computing and mathematics")
        
        outcomeB.shortName == "b"
        outcomeB.description.startsWith("An ability to analyze a problem, and identify")
    }
    
    void "find a program and check that correct indicators are found"() {
        given: "an existing program"
        
        when: "loading an existing program"
        def p = Program.findByName("Computer Science")
        
        then: "its indicators should be the expected ones"
        def (outcomeA, outcomeB) = p.outcomes.sort { it.shortName }

        def (indA1, indA2, indA3) = outcomeA.indicators.sort { it.shortName }
        indA1.shortName == "a.1"
        indA1.description.startsWith("Student can correctly interpret a computational problem")
        indA2.shortName == "a.2"
        indA2.description.startsWith("Student can analyze a computation problem in order")
        indA3.shortName == "a.3"
        indA3.description.startsWith("Student can define a solution to a computational")
        
        def (indB1, indB2, indB3) = outcomeB.indicators.sort { it.shortName }
        indB1.shortName == "b.1"
        indB1.description.startsWith("Student can effectively collect and document")
        indB2.shortName == "b.2"
        indB2.description.startsWith("Student can effectively analyze and model a")
        indB3.shortName == "b.3"
        indB3.description.startsWith("Student can identify and evaluate appropriate technologies")
    }
}
