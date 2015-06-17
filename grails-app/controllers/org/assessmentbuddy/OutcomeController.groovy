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
        flash.message = "In theory we should have saved the outcome"
        
        redirect( action: 'index' )
    }
}
