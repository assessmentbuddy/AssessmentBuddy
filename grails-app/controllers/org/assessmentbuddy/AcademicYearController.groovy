package org.assessmentbuddy

import grails.util.Mixin

import org.assessmentbuddy.model.AcademicYear
import org.assessmentbuddy.model.PermissionsCheck
import org.assessmentbuddy.model.SaveFailedException
import org.assessmentbuddy.model.StandardExceptionHandlers

@Mixin(PermissionsCheck)
class AcademicYearController extends StandardExceptionHandlers {
    def academicYearService

    def index() {
        def academicYears = []
        def mayEdit = false
        
        if (!canEditAcademicYear().call(session.user)) {
            flash.message = "You do not have permission to create/edit academic years"
        } else {
            academicYears = AcademicYear.list().sort { it.start }
            mayEdit = true
        }
        
        [ academicYears: academicYears, mayEdit: mayEdit ]
    }
    
    def create() {
        permCheck(canEditAcademicYear(), session.user, "create/edit academic year")
        redirect(action: 'edit')
    }
    
    def edit() {
        permCheck(canEditAcademicYear(), session.user, "create/edit academic year")

        def academicYearToEdit
        if (flash.academicYearToEdit) {
            // From previous form data
            academicYearToEdit = flash.academicYearToEdit
        } else {
            if (params.id) {
                // Load from database
                academicYearToEdit = academicYearService.findAcademicYearForId(params.id.toLong())
            } else {
                // Create new AcademicYear
                academicYearToEdit = new AcademicYear()
            }
        }
        
        [academicYearToEdit: academicYearToEdit]
    }
    
    def save() {
        permCheck(canEditAcademicYear(), session.user, "create/edit academic year")

        try {
            academicYearService.saveAcademicYear(params)
        } catch (SaveFailedException e) {
            flash.message = e.getMessage()
            flash.academicYearToEdit = e.getBean()
            redirect(action: 'edit', id: params.id)
            return
        }
        
        flash.message = "Academic year saved successfully"
        redirect(action: 'index')
    }
}
