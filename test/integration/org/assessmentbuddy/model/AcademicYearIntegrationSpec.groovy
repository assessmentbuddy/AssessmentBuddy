package org.assessmentbuddy.model

import grails.test.spock.IntegrationSpec

class AcademicYearIntegrationSpec extends IntegrationSpec {
    def initialDataService

    def setup() {
        initialDataService.createInitialTerms() // also creates AcademicYears
    }

    def cleanup() {
    }

    void "check existing AcademicYear objects"() {
        given: "existing AcademicYear objects"
        
        when: "they are loaded from the database"
        def ay2015_2016 = AcademicYear.where { start == 2015 && end == 2016 }.get()
        def ay2016_2017 = AcademicYear.where { start == 2016 && end == 2017 }.get()
        
        then: "they contain the expected start and end years"
        ay2015_2016 != null
        ay2016_2017 != null
        ay2015_2016.start == 2015
        ay2015_2016.end == 2016
        ay2016_2017.start == 2016
        ay2016_2017.end == 2017
    }
}
