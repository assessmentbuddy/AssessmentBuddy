package org.assessmentbuddy

import grails.util.Mixin
import org.assessmentbuddy.model.NoSuchIdException

import org.assessmentbuddy.model.PermissionsCheck
import org.assessmentbuddy.model.PermissionsException
import org.assessmentbuddy.model.Program
import org.assessmentbuddy.model.SaveFailedException
import org.assessmentbuddy.model.StandardExceptionHandlers

@Mixin(PermissionsCheck)
class ProgramController extends StandardExceptionHandlers {
    def programService

    def index() {
        def programs = []
        boolean mayEdit = false
        
        if (!canEditProgram().call(session.user)) {
            flash.message = "You don't have permission to create and edit programs"
        } else {
            programs = Program.list()
            mayEdit = true
        }
        
        [ programs: programs, mayEdit: mayEdit ]
    }
    
    def create() {
        permCheck(canEditProgram(), session.user, "Create/edit program")
        redirect( action: 'edit' )
    }
    
    def edit() {
        permCheck(canEditProgram(), session.user, "Create/edit program")

        def programToEdit
        if (flash.programToEdit) {
            // This was set by a previous action
            programToEdit = flash.programToEdit
        } else {
            if (!params.id) {
                // Edit a new progrma
                programToEdit = new Program()
            } else {
                // Edit existing program
                programToEdit = programService.findProgramForId(params.id.toLong())
            }
        }
        
        [ programToEdit : programToEdit ]
    }
    
    def save() {
        permCheck(canEditProgram(), session.user, "Create/edit program")

        try {
            programService.saveProgram(params)
        } catch (SaveFailedException e) {
            flash.message = e.getMessage()
            flash.programToEdit = e.getBean()
            redirect(action: 'edit', id: params.id)
            return
        }
       
        flash.message = "Program ${params.name} saved successfully"
        redirect( action: 'index' )
    }
    
    // This action allows the user to change the currently-selected
    // program in his/her session.
    def select() {
        def progId = params?.program?.toLong()
        if (!progId || progId < 0) {
            session.program = null
        } else {
            println "Attempting to select program ${progId}"
            def program = programService.findProgramForId(progId)
            println "Found? ${program?.name}"
            session.program = session.availablePrograms.any { it.id == program.id } ? program : null
            println "Selected: ${session.program?.name}"
        }
        
        // Redirect back to original page
        if (params.where) {
            redirect(uri: params.where)
        } else {
            redirect(controller: "home", action: "index")
        }
    }
}
