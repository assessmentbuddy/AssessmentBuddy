package org.assessmentbuddy

import org.assessmentbuddy.model.Program

class ContextFilters {
    def filters = {
        // Based on the user's roles, populate list of programs
        // that the user can select.  This is done as a filter because
        // a program-selection UI is presented as part of the main
        // layout (since it is common to all pages)
        availablePrograms(controller:'login|assets', action:'*', invert: true) {
            before = {
                if (session.user) {
                    if (!session.user.isAttached()) {
                        session.user.attach()
                    }
                    
                    if (session.user.isAdmin()) {
                        // Administrators can select any program
                        session.availablePrograms = Program.list().sort { it.name }
                    } else {
                        // Non-administrators can choose programs according to their roles
                        session.availablePrograms =
                            session.user.roles.collect { it.program }.findAll { it != null }.toSet().toList().sort { it.name }
                    }
                    println "there are ${session.availablePrograms.size()} available programs"
                }
            }
        }
    }
}