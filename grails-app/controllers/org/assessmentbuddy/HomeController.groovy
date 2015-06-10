package org.assessmentbuddy

class HomeController {

    def index() {
        if (!session.user.isAttached()) {
            session.user.attach()
        }
        
        println "HomeController: user ${session.user.userName} has ${session.user.roles.size()} role(s)"
        
        [ user : session.user ]
    }
}
