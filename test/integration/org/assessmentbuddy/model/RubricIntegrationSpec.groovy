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

class RubricIntegrationSpec extends IntegrationSpec {
    def initialDataService

    def setup() {
        initialDataService.createTestingPrograms()
        initialDataService.createTestingRubrics()
    }

    def cleanup() {
    }

    void "find standard rubric"() {
        given: "an existing program"
        
        when: "the program is loaded"
        def compSci = Program.findByName("Computer Science")
        
        then: "its rubrics and achievement levels can be found"
        def rubrics = compSci.rubrics
        rubrics.size() == 1
        
        def stdRubric = rubrics[0]
        stdRubric.name == "Standard CS Rubric"
        
        def achievementLevels = stdRubric.achievementLevels.sort { it.rank }
        achievementLevels.size() == 3
        achievementLevels[0].name == "Needs Improvement"
        achievementLevels[0].rank == 0
        achievementLevels[1].name == "Meets Expectations"
        achievementLevels[1].rank == 70
        achievementLevels[2].name == "Exceeds Expectations"
        achievementLevels[2].rank == 85
    }
}
