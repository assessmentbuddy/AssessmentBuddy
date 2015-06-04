package org.assessmentbuddy

class LoginFilters {

    def filters = {
        all(controller:'login', action:'*', invert: true) {
            before = {
                if (!session.user) {
                    redirect(controller: "login", action: "index")
                    return false
                }
            }
        }
    }
}
