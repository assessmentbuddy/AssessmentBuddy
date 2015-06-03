package org.assessmentbuddy.model

import grails.test.spock.IntegrationSpec

class MeasurementIntegrationSpec extends IntegrationSpec {
    def initialDataService

    def setup() {
        initialDataService.createInitialPrograms()
        initialDataService.createInitialTerms()
        initialDataService.createInitialRubrics()
        initialDataService.createInitialOutcomesAndIndicators()
        initialDataService.createInitialMeasurements()
    }

    def cleanup() {
    }

    void "check existing measurement"() {
        given: "an existing measurement"
        
        when: "the measurement is loaded"
        def compSci = Program.findByName("Computer Science")
        def measurement = Measurement.where { program == compSci }.get() // there should just be one
        
        then: "it contains the expected data"
        measurement.object.startsWith("Exam 4, Question 10")
        measurement.discussion.startsWith("Several students had")
        
        // TODO: check that achievement level tallies are correct
    }
}
