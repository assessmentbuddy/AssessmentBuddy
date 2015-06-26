package org.assessmentbuddy

import grails.converters.JSON

import grails.util.Mixin
import org.assessmentbuddy.model.NoSuchIdException

import org.assessmentbuddy.model.PermissionsCheck
import org.assessmentbuddy.model.StandardExceptionHandlers
import org.assessmentbuddy.model.Target

@Mixin(PermissionsCheck)
class TargetController extends StandardExceptionHandlers {
    def targetService
    def rubricService

    def index() {
        def targets = []
        def mayEdit = false
        
        if (!session.program) {
            flash.message = "Please choose a program"
        } else if (!canEditTarget(session.program).call(session.user)) {
            flash.message = "You do not have permission to edit targets in ${session.program.name}"
        } else {
            targets = targetService.findTargetsForProgram(session.program)
            mayEdit = true
        }
        
        [targets: targets, mayEdit: mayEdit]
    }
    
    def create() {
        permCheck(canEditTarget(session.program), session.user, "Create/edit targets")
        redirect(action: 'edit')
    }
    
    def edit() {
        permCheck(canEditTarget(session.program), session.user, "Create/edit targets")
        
        def targetToEdit
        if (flash.targetToEdit) {
            // data from previous form submission
            targetToEdit = flash.targetToEdit
        } else if (params.id) {
            // load from database
            targetToEdit = targetService.findTargetForId(params.id.toLong())
        } else {
            // create new target
            targetToEdit = new Target()
        }
        
        // allow the user to select from available rubrics
        def rubrics = rubricService.findRubricsForProgram(session.program)
        
        // If the target specifies a rubric, load the appropriate achievement levels
        def achievementLevels = []
        if (targetToEdit.rubric) {
            achievementLevels = rubricService.findAchievementLevelsForRubric(targetToEdit.rubric)
        }
        
        [targetToEdit: targetToEdit, rubrics: rubrics, achievementLevels: achievementLevels]
    }
    
    def save() {
        permCheck(canEditTarget(session.program), session.user, "Create/edit targets")

        // TODO: implement
        flash.message = "Should be saving the target"
        redirect(action: 'index')
    }
    
    def ajaxGetAchievementLevelsForRubric() {
        println "AJAX: getting achievement levels for rubric id=${params.id}"
        def achievementLevels
        try {
            def rubric = rubricService.findRubricForId(params.id.toLong())
            achievementLevels = rubric.achievementLevels
        } catch (NoSuchIdException e) {
            achievementLevels = []
        }
        render achievementLevels as JSON
    }
}