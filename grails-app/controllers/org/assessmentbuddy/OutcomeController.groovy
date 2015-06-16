package org.assessmentbuddy

import org.assessmentbuddy.model.Outcome

class OutcomeController {

    def index() {
        if (!session.program) {
            flash.message = "Please select a program"
            return
        }
        
        if (!session.user.hasAdminRightsIn(session.program)) {
            flash.message = "You don't have admin rights in ${session.program.name}"
            return
        }
        
        flash.message = "Looks like you can add/edit outcomes for ${session.program.name}"
        
        [ outcomes : Outcome.getOutcomesFor(session.program) ]
    }
}
