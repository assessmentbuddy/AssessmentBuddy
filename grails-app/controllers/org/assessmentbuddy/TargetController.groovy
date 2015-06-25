package org.assessmentbuddy

import grails.util.Mixin

import org.assessmentbuddy.model.PermissionsCheck
import org.assessmentbuddy.model.StandardExceptionHandlers

@Mixin(PermissionsCheck)
class TargetController extends StandardExceptionHandlers {
    def targetService

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
}
