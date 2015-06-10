package org.assessmentbuddy

class SecurityFilters {
    def filters = {
		// Make sure user is logged in for any controller other than
		// the login controller.
        loginCheck(controller:'login|assets', action:'*', invert: true) {
            before = {
                if (!session.user) {
                    redirect(controller: "login", action: "index")
                    return false
                }
            }
        }
		
		// Make sure access to the user controller is appropriately
		// restricted.
		userCheck(controller:'user', action:'*') {
			before = {
				def user = session.user
				
				if (!user.isAttached()) {
					user.attach()
				}
				
				// An admin can perform any user action.
				// Any user can perform the edit action on his/her own account.
				if (!(user.isAdmin() || (actionName == 'edit' && params.id && user.id == params.id))) {
					render(status: 401, text: "Permission denied")
					return false
				}
			}
		}
    }
}
