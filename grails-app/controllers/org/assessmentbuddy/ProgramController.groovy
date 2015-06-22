package org.assessmentbuddy

import grails.util.Mixin

import org.assessmentbuddy.model.PermissionsCheck
import org.assessmentbuddy.model.PermissionsException
import org.assessmentbuddy.model.Program

@Mixin(PermissionsCheck)
class ProgramController {

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
        
        if (!params.id) {
            if (flash.programToEdit) {
                // This was set by a previous action
                programToEdit = flash.programToEdit
            } else {
                // Edit a new progrma
                programToEdit = new Program()
            }
        } else {
            // Edit existing program
            programToEdit = Program.get(params.id)
            if (!programToEdit) {
                response.sendError(404)
                return
            }
        }
        
        [ programToEdit : programToEdit ]
    }
    
    def save() {
        permCheck(canEditProgram(), session.user, "Create/edit program")

        def program
        
        if (params.id) {
            // Editing existing program
            program = Program.get(params.id.toLong())
            if (!program) {
                response.sendError(404)
                return
            }
            program.properties = params
        } else {
            // Creating new program
            program = new Program(params)
        }
        
        if (!program.save(flush: true)) {
            flash.message = "Could not save program"
            flash.programToEdit = program
            redirect( action: 'edit', id: program.id )
            return
        }
        
        flash.message = "Program ${program.name} saved successfully"
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
            def program = Program.get(progId)
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
    
    def permissionsException(final PermissionsException e) {
        logException(e)
        response.sendError(403)
    }
}
