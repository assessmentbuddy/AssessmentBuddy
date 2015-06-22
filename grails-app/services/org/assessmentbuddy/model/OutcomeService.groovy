package org.assessmentbuddy.model

import grails.transaction.Transactional

@Transactional
class OutcomeService {
    def saveOutcome(outcomeParams, currentProgram) {
        def outcomeToSave
        if (outcomeParams.id) {
            // Modifying existing program
            outcomeToSave = Outcome.get(outcomeParams.id.toLong())
            if (!outcomeToSave) {
                throw new NoSuchIdException("No such outcome", outcomeParams.id.toLong())
            }
            // Make sure it really belongs to the current program
            if (!outcomeToSave.program || outcomeToSave.program.id != currentProgram.id) {
                throw new SaveFailedException("Outcome ${outcomeToSave.id} doesn't belong to program ${currentProgram.id}?", outcomeToSave)
                return
            }
            // Looks like we can proceed
            outcomeToSave.properties = outcomeParams
        } else {
            // Saving new program
            outcomeToSave = new Outcome(outcomeParams)
            outcomeToSave.program = currentProgram
        }
        
        if (!outcomeToSave.save()) {
            throw new SaveFailedException("Could not save outcome", outcomeToSave)
            return
        }
    }
}
