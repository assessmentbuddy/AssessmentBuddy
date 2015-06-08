// AssessmentBuddy - Grails webapp for recording and analyzing assessment data
// Copyright (C) 2015, David H. Hovemeyer <david.hovemeyer@gmail.com>
//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU Affero General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU Affero General Public License for more details.
//
// You should have received a copy of the GNU Affero General Public License
// along with this program.  If not, see <http://www.gnu.org/licenses/>.

package org.assessmentbuddy.model

import grails.test.spock.IntegrationSpec

class MeasurementIntegrationSpec extends IntegrationSpec {
    def initialDataService

    def setup() {
        initialDataService.createTestingPrograms()
        initialDataService.createTestingTerms()
        initialDataService.createTestingRubrics()
        initialDataService.createTestingOutcomesAndIndicators()
        initialDataService.createTestingMeasurements()
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
