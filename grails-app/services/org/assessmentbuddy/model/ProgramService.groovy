package org.assessmentbuddy.model

import grails.transaction.Transactional

@Transactional
class ProgramService {

    def saveProgram(programParams) {
        def programToSave
        if (programParams.id) {
            programToSave = Program.get(programParams.id.toLong())
            if (!programToSave) {
                throw new NoSuchIdException("No such program", programParams.id.toLong())
            }
            programToSave.properties = programParams
        } else {
            programToSave = new Program(programParams)
        }
        
        if (!programToSave.save()) {
            throw SaveFailedException("Could not save program", programToSave)
        }
    }
}
