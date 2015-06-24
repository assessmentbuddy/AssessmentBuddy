package org.assessmentbuddy.model

class StandardExceptionHandlers {
    def permissionsException(final PermissionsException e) {
        log.error("Permissions exception", e)
        response.sendError(403)
    }
    
    def noSuchIdException(final NoSuchIdException e) {
        log.error("No such id", e)
        response.sendError(404)
    }
}
