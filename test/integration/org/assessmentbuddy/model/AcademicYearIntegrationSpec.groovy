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
