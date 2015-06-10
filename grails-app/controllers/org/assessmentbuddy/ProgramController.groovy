package org.assessmentbuddy

import org.assessmentbuddy.model.Program

class ProgramController {

    def index() {
        // TODO: enumerate programs for editing
    }
    
    def create() {
        redirect( action: 'edit' )
    }
    
    def edit() {
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
}
