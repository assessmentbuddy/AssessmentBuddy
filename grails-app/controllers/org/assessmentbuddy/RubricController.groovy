package org.assessmentbuddy

import grails.util.Mixin

import org.assessmentbuddy.model.PermissionsCheck
import org.assessmentbuddy.model.Rubric
import org.assessmentbuddy.model.SaveFailedException
import org.assessmentbuddy.model.StandardExceptionHandlers

@Mixin(PermissionsCheck)
class RubricController extends StandardExceptionHandlers {
    def rubricService

    def index() {
        def rubrics = []
        def mayEdit = false
        
        if (!session.program) {
            flash.message = "Please select a program"
        } else if (!canEditRubric(session.program).call(session.user)) {
            flash.message = "You don't have permission to create/edit rubrics in ${session.program.name}"
        } else {
            rubrics = rubricService.findRubricsForProgram(session.program)
            mayEdit = true
        }
        
        [rubrics: rubrics, mayEdit: mayEdit]
    }
    
    def create() {
        permCheck(canEditRubric(session.program), session.user, "Create/edit rubric")
        redirect(action: 'edit')
    }
    
    def edit() {
        permCheck(canEditRubric(session.program), session.user, "Create/edit rubric")
        
        def rubricToEdit
        if (flash.rubricToEdit) {
            // From previous form submission
            rubricToEdit = flash.rubricToEdit
        } else if (params.id) {
            // Load from database
            rubricToEdit = rubricService.findRubricForId(params.id.toLong())
        } else {
            // Create new rubric
            rubricToEdit = new Rubric()
        }
        
        [rubricToEdit: rubricToEdit]
    }
    
    def save() {
        permCheck(canEditRubric(session.program), session.user, "Create/edit rubric")
        
        // TODO: marshal changes to achievement levels
        
        try {
            rubricService.saveRubric(params, session.program)
        } catch (SaveFailedException e) {
            flash.message = e.getMessage()
            flash.rubricToEdit = e.getBean()
            redirect(action: 'edit', id: params.id)
            return
        }
        
        flash.message = "Rubric saved successfully"
        redirect(action: 'index')
    }
}
