package org.assessmentbuddy

import org.assessmentbuddy.model.Role

class HomeController {
    def index() {
        if (!session.user.isAttached()) {
            session.user.attach()
        }
        
        // Based on user role(s), determine which menu items to show
        def isFullAdmin = session.user.isAdmin()
        def isAnyAdmin = session.user.roles.any { it.roleType == Role.RoleType.ADMIN }
        
        [ isFullAdmin: isFullAdmin, isAnyAdmin: isAnyAdmin ]
    }
}
