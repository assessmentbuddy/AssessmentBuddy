package org.assessmentbuddy.model

import grails.test.spock.IntegrationSpec

class AcademicYearIntegrationSpec extends IntegrationSpec {

    def setup() {
    }

    def cleanup() {
    }

    void "create and save some initial AcademicYear objects"() {
        given: "initial AcademicYear objects"
        def ay2015_2016 = new AcademicYear(start: 2015, end: 2016)
        def ay2016_2017 = new AcademicYear(start: 2016, end: 2017)
        
        when: "saving them to the database"
        ay2015_2016.save()
        ay2016_2017.save()
        
        then: "they are saved successfully"
        ay2015_2016.errors.errorCount == 0
        ay2016_2017.errors.errorCount == 0
        AcademicYear.get(ay2015_2016.id).start == 2015
        AcademicYear.get(ay2015_2016.id).end == 2016
        AcademicYear.get(ay2016_2017.id).start == 2016
        AcademicYear.get(ay2016_2017.id).end == 2017
    }
}
