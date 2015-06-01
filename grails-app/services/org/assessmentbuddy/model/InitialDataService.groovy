package org.assessmentbuddy.model

import grails.transaction.Transactional

@Transactional
class InitialDataService {
    def bcryptService
    
    def createInitialPrograms() {
        def p = new Program(name: "Computer Science")
        p.save()
    }
    
    def createInitialUsersAndRoles() {
        // Initial program(s) need to already exist
        def p = Program.findByName("Computer Science")

        def u = new User(
            userName: 'dhovemey',
            passwordHash: bcryptService.hashPassword("muffin"),
            fullName: "David Hovemeyer",
            email: "dhovemey@ycp.edu"
        )
        u.save()
        
        def r = new Role(role: Role.RoleType.ADMIN, program: p, score: Role.Scope.ALL_PROGRAMS)
        r.save()
    }

    def createInitialTerms() {
        def terms = [
            new Term(name: "Winter", seq: 0),
            new Term(name: "Spring", seq: 1),
            new Term(name: "Summer", seq: 2),
            new Term(name: "Summer I", seq: 3),
            new Term(name: "Summer II", seq: 4),
            new Term(name: "Fall", seq: 5),
        ]
        
        terms.each { $it.save() }
    }
}
