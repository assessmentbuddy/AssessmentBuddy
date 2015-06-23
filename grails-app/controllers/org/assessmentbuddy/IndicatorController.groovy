package org.assessmentbuddy

import grails.util.Mixin

import org.assessmentbuddy.model.Indicator
import org.assessmentbuddy.model.Outcome
import org.assessmentbuddy.model.PermissionsCheck
import org.assessmentbuddy.model.PermissionsException

@Mixin()
class IndicatorController {
    def indicatorService

    def index() {
        def indicators = session.program ? Indicator.getIndicatorsFor(session.program) : []
        [ indicators: indicators ]
    }
    
    def create() {
        redirect( action: 'edit' )
    }
    
    def edit() {
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
        flash.message = "Should be saving the indicator"
        redirect( action: 'index' )
    }
}
