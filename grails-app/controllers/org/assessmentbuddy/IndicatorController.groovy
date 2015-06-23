package org.assessmentbuddy

import grails.util.Mixin

import org.assessmentbuddy.model.Indicator
import org.assessmentbuddy.model.NoSuchIdException
import org.assessmentbuddy.model.Outcome
import org.assessmentbuddy.model.PermissionsCheck
import org.assessmentbuddy.model.PermissionsException
import org.assessmentbuddy.model.SaveFailedException

@Mixin(PermissionsCheck)
class IndicatorController {
    def indicatorService

    def index() {
        def indicators = []
        def mayEdit = false
        
        if (!session.program) {
            flash.message = "Please choose a program"
        } else if (!canEditIndicator(session.program, null).call(session.user)) {
            flash.message = "You don't have permission to create/edit indicators in ${session.program.name}"
        } else {
            indicators = session.program ? Indicator.getIndicatorsFor(session.program) : []
            mayEdit = true
        }
        
        [ indicators: indicators, mayEdit: mayEdit ]
    }
    
    def create() {
        permCheck(canEditIndicator(session.program, null), session.user, "Create/edit indicator")
        redirect( action: 'edit' )
    }
    
    def edit() {
        permCheck(canEditIndicator(session.program, null), session.user, "Create/edit indicator")

        def indicatorToEdit
        
        // We need to know the available outcomes for the program
        def outcomes
        if (!session.program) {
            flash.message = "Please select a program"
            outcomes = []
        } else {
            outcomes = Outcome.getOutcomesFor(session.program)
        }
        
        if (params.id) {
            // Editing existing indicator
            indicatorToEdit = Indicator.get(params.id.toLong())
            if (!indicatorToEdit) {
                response.sendError(404)
                return
            }
        } else {
            if (flash.indicatorToEdit) {
                // Result of previous form submission
                indicatorToEdit = flash.indicatorToEdit
            } else {
                // Create new indicator
                indicatorToEdit = new Indicator()
            }
        }
        
        [ indicatorToEdit: indicatorToEdit, outcomes: outcomes ]
    }
    
    def save() {
        // Find the outcome
        if (!params.outcomeId || params.outcomeId.toLong() < 0) {
            flash.message = "Please choose an outcome"
            flash.indicatorToEdit = new Indicator(params)
            redirect(action: 'edit', id: params.id)
            return
        }
        def outcome = Outcome.get(params.outcomeId.toLong())
        if (!outcome) {
            flash.message = "Outcome not found?"
            flash.indicatorToEdit = new Indicator(params)
            redirect(action: 'edit', id: params.id)
            return
        }

        // Now we can do the permissions check, since we have the outcome
        permCheck(canEditIndicator(session.program, outcome), session.user, "Create/edit indicator")
        
        // Save the indicator
        try {
            indicatorService.saveIndicator(params, outcome)
        } catch (NoSuchIdException e) {
            response.sendError(404)
            return
        } catch (SaveFailedException e) {
            flash.message = e.getMessage()
            flash.indicatorToEdit = e.getBean()
            redirect(action: 'edit', id: params.id)
            return
        }
        
        flash.message = "Indicator saved successfully"
        redirect( action: 'index' )
    }
}
