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
            session.user = user
            redirect(controller: "home")
            return
        }
        
        flash.message = "No such username/password"
        
        render( view: 'index' )
    }
}
