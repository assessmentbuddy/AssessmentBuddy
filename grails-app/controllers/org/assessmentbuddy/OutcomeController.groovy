package org.assessmentbuddy

import grails.util.Mixin

import org.assessmentbuddy.model.BaseException
import org.assessmentbuddy.model.NoSuchIdException
import org.assessmentbuddy.model.Outcome
import org.assessmentbuddy.model.PermissionsCheck

@Mixin(PermissionsCheck)
class OutcomeController {
    def outcomeService

    def index() {
        def outcomes = []
        boolean mayEdit = false
        
        if (!session.program) {
            flash.message = "Please select a program"
        } else if (!canEditOutcome(session.program).call(session.user)) {
            flash.message = "You don't have permission to edit outcomes in ${session.program.name}"
        } else {
            outcomes = Outcome.getOutcomesFor(session.program)
            mayEdit = true
        }
        
        //flash.message = "Looks like you can add/edit outcomes for ${session.program.name}"
        
        [ outcomes : outcomes, mayEdit: mayEdit ]
    }
    
    def create() {
        permCheck(canEditOutcome(session.program), session.user, "Create/edit outcome")
        redirect(action: 'edit')
    }
    
    def edit() {
        permCheck(canEditOutcome(session.program), session.user, "Create/edit outcome")
        
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
        permCheck(canEditOutcome(session.program), session.user, "Create/edit outcome")

        // Attempt to persist the outcome
        try {
            outcomeService.saveOutcome(params, session.program)
        } catch (NoSuchIdException e) {
            response.sendError(404)
            return
        } catch (BaseException e) {
            flash.message = "Could not save outcome: ${e.getMessage()}"
            flash.outcomeToEdit = e.getBean()
            redirect( action: 'edit', id: params.id )
            return
        }
 
        flash.message = "Outcome saved successfully"
        redirect( action: 'index' )
    }
}
