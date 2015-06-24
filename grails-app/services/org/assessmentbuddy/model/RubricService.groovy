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
    def saveRubric(rubricParams, program) {
        def rubricToSave
        if (rubricParams.id) {
            // update existing rubric
            rubricToSave = Rubric.get(params.id.toLong())
            if (!rubricToSave) {
                throw new NoSuchIdException("No rubric found for id ${params.id}", params.id.toLong())
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
    }
}
