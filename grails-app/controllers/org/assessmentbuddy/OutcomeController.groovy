package org.assessmentbuddy

import org.assessmentbuddy.model.Outcome

class OutcomeController {

    def index() {
        if (!session.program) {
            flash.message = "Please select a program"
            return
        }
        
        if (!session.user.hasAdminRightsIn(session.program)) {
            flash.message = "You don't have admin rights in ${session.program.name}"
            return
        }
        
        //flash.message = "Looks like you can add/edit outcomes for ${session.program.name}"
        
        [ outcomes : Outcome.getOutcomesFor(session.program) ]
    }
    
    def create() {
        redirect(action: 'edit')
    }
    
    def edit() {
        def outcomeToEdit
        
        if (params.id) {
            // Edit existing outcome
            outcomeToEdit = Outcome.get(params.id)
            if (!outcomeToEdit) {
                response.sendError(404)
                return
            }
        } else {
            // Editing a new outcome
            if (flash.outcomeToEdit) {
                // there is an outcome from a previous form submission
                outcomeToEdit = flash.outcomeToEdit
            } else {
                // create a new Outcome
                outcomeToEdit = new Outcome()
            }
        }
        
        [ outcomeToEdit: outcomeToEdit ]
    }
    
    def save() {
        // Make sure a program is selected
        if (!session.program) {
            flash.message = "A program must be selected"
            flash.outcomeToEdit = new Outcome(params)
            redirect(action: 'edit', id: params.id)
            return
        }
        
        // Make sure the user is an admin in the selected program
        if (!session.user.hasAdminRightsIn(session.program)) {
            flash.message = "User ${session.user.userName} doesn't have permission to create outcomes in ${session.program.name}"
            flash.outcomeToEdit = new Outcome(params)
            redirect(action: 'edit', id: params.id)
            return
        }

        def outcomeToSave
        if (params.id) {
            // Modifying existing program
            outcomeToSave = Outcome.get(params.id.toLong())
            if (!outcomeToSave) {
                response.sendError(404)
                return
            }
            // Make sure it really belongs to the current program
            if (!outcomeToSave.program || outcomeToSave.program.id != session.program.id) {
                flash.message = "Outcome ${outcomeToSave.id} doesn't belong to program ${session.program.id}?"
                flash.outcomeToEdit = outcomeToSave
                redirect(action: 'edit', id: params.id)
                return
            }
            // Looks like we can proceed
            outcomeToSave.properties = params
        } else {
            // Saving new program
            outcomeToSave = new Outcome(params)
            outcomeToSave.program = session.program
        }
        
        if (!outcomeToSave.save(flush: true)) {
            flash.message = "Could not save outcome"
            flash.outcomeToEdit = outcomeToSave
            redirect(action: 'edit', id: params.id)
            return
        }
 
        flash.message = "Outcome saved successfully"
        redirect( action: 'index' )
    }
}
