package org.assessmentbuddy.model

import grails.test.spock.IntegrationSpec

class TermIntegrationSpec extends IntegrationSpec {
    def initialDataService

    def setup() {
        initialDataService.createInitialTerms()
    }

    def cleanup() {
    }

    void "test that initial terms are created"() {
        Term.findByName("Winter").seq == 0
        Term.findByName("Spring").seq == 1
        Term.findByName("Summer").seq == 2
        Term.findByName("Summer I").seq == 3
        Term.findByName("Summer II").seq == 4
        Term.findByName("Fall").seq == 5
    }
}
