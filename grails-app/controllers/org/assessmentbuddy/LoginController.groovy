package org.assessmentbuddy

import org.assessmentbuddy.model.User

class LoginController {
    def bcryptService

    def index() { }
    
    def login() {
        def userName = params.userName
        
        if (!userName || userName == "") {
            flash.message = "Please specify a username"
            render( view: 'index' )
            return
        }
        
        def password = params.password
        if (!password) {
            password = ""
        }
        
        def user = User.findByUserName(userName)
        if (user && bcryptService.checkPassword(password, user.passwordHash)) {
            setupSession(user)
            redirect(controller: "home")
            return
        }
        
        flash.message = "No such username/password"
        
        render( view: 'index' )
    }
    
    def logout() {
        if (session.user) {
            teardownSession()
        }
        redirect(action: 'index')
    }
    
    private void setupSession(User user) {
        session.user = user
        
        // If the user is associated (via his/her roles) with exactly
        // one program, then set it as the current program
        def programs = user.roles.collect { it.program }.findAll { it != null }.toSet().toList()
        session.program = programs.size() == 1 ? programs.first() : null
    }
    
    private void teardownSession() {
        session.user = null
        session.program = null
    }
}
