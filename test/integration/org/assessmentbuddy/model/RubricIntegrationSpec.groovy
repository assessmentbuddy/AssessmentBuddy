package org.assessmentbuddy.model

import grails.test.spock.IntegrationSpec

class RubricIntegrationSpec extends IntegrationSpec {
    def initialDataService

    def setup() {
        initialDataService.createInitialPrograms()
        initialDataService.createInitialRubrics()
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
