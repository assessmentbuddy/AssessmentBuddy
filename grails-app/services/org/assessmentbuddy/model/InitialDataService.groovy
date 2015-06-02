package org.assessmentbuddy.model

import grails.transaction.Transactional

@Transactional
class InitialDataService {
    def bcryptService
    
    def createInitialPrograms() {
        def p = new Program(name: "Computer Science")
        p.save()
    }
    
    def createInitialOutcomesAndIndicators() {
        // Initial program(s) need to already exist
        def p = Program.findByName("Computer Science")
        
        def outcomeA = new Outcome(
            shortName: "a",
            description: "An ability to apply knowledge of computing and mathematics appropriate to the discipline"
        )
        
        def outcomeB = new Outcome(
            shortName: "b",
            description: "An ability to analyze a problem, and identify and define the computing requirements appropriate to its solution"
        )
        
        def indA1 = new Indicator(
            shortName: "a.1",
            description: "Student can correctly interpret a computational problem and define its parameters"
        )
        def indA2 = new Indicator(
            shortName: "a.2",
            description: "Student can analyze a computation problem in order to choose mathematical and algorithmic principles that can be applied to solve the problem"
        )
        def indA3 = new Indicator(
            shortName: "a.3",
            description: "Student can define a solution to a computational problem"
        )
        outcomeA.addToIndicators(indA1)
        outcomeA.addToIndicators(indA2)
        outcomeA.addToIndicators(indA3)
        
        def indB1 = new Indicator(
            shortName: "b.1",
            description: "Student can effectively collect and document system requirements"
        )
        def indB2 = new Indicator(
            shortName: "b.2",
            description: "Student can effectively analyze and model a problem domain"
        )
        def indB3 = new Indicator(
            shortName: "b.3",
            description: "Student can identify and evaluate appropriate technologies to be used in a system"
        )
        outcomeB.addToIndicators(indB1)
        outcomeB.addToIndicators(indB2)
        outcomeB.addToIndicators(indB3)
        
        p.addToOutcomes(outcomeA)
        p.addToOutcomes(outcomeB)
        
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
