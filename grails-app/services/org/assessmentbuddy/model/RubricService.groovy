package org.assessmentbuddy.model

import grails.transaction.Transactional

@Transactional
class RubricService {
    def findRubricsForProgram(Program program) {
        program = Program.get(program.id) // ugh, why is this necessary?
        return program.rubrics
    }
    
    def findRubricForId(long id) {
        def rubric = Rubric.get(id)
        if (!rubric) {
            throw new NoSuchIdException("No rubric found for id ${id}", id)
        }
        return rubric
    }
    
    // TODO: handle adding/deleting achievement levels
    def saveRubric(rubricParams, program, achievementLevelToAddParams, achievementLevelsToDelete) {
        def rubricToSave
        if (rubricParams.id) {
            // update existing rubric
            rubricToSave = Rubric.get(rubricParams.id.toLong())
            if (!rubricToSave) {
                throw new NoSuchIdException("No rubric found for id ${rubricParams.id}", rubricParams.id.toLong())
            }
            // update properties
            rubricToSave.properties = rubricParams
            rubricToSave.program = program
        } else {
            // create new rubric
            rubricToSave = new Rubric(rubricParams)
            rubricToSave.program = program
        }
        
        if (!rubricToSave.save()) {
            throw new SaveFailedException("Could not save rubric", rubricToSave)
        }
        
        // See if there are achievement levels to delete
        if (!achievementLevelsToDelete.isEmpty()) {
            achievementLevelsToDelete.each { id ->
                def achievementLevel = AchievementLevel.get(id)
                if (achievementLevel) {
                    rubricToSave.removeFromAchievementLevels(achievementLevel)
                    achievementLevel.delete()
                }
            }
            if (!rubricToSave.save()) {
                throw new SaveFailedException("Could not delete selected achievement levels", rubricToSave)
            }
        }
        
        // Add an achievement level if requested
        if (achievementLevelToAddParams.name && achievementLevelToAddParams.rank != '') {
            println "Adding achievement level name=${achievementLevelToAddParams.name}, rank=${achievementLevelToAddParams.rank}"
            def achievementLevel = new AchievementLevel(achievementLevelToAddParams)
            rubricToSave.addToAchievementLevels(achievementLevel)
            if (!rubricToSave.save()) {
                throw new SaveFailedException("Could not add achievement level", rubricToSave)
            }
        }
    }
}
