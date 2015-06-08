// AssessmentBuddy - Grails webapp for recording and analyzing assessment data
// Copyright (C) 2015, David H. Hovemeyer <david.hovemeyer@gmail.com>
//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU Affero General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU Affero General Public License for more details.
//
// You should have received a copy of the GNU Affero General Public License
// along with this program.  If not, see <http://www.gnu.org/licenses/>.

package org.assessmentbuddy.model

import grails.transaction.Transactional

/**
 * Create initial data for testing.
 */
@Transactional
class InitialDataService {
    def bcryptService
    
	def createInitialAdminUser() {
        def admin = new User(
            userName: "admin",
            passwordHash: bcryptService.hashPassword("admin"),
            fullName: "Super User",
            email: "admin@ycp.edu"
        )
        
        def adminRole = new Role(roleType: Role.RoleType.ADMIN, program: null, scope: Role.Scope.ALL_PROGRAMS)
        admin.addToRoles(adminRole)
        
        admin.save(failOnError: true)
	}
	
    def createTestingPrograms() {
        def p = new Program(name: "Computer Science")
        p.save(failOnError: true)
    }
    
    def createTestingOutcomesAndIndicators() {
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
        
        p.save(failOnError: true)
    }
    
    def createTestingUsersAndRoles() {
        // Initial program(s) need to already exist
        def compSci = Program.findByName("Computer Science")

        def u = new User(
            userName: "dhovemey",
            passwordHash: bcryptService.hashPassword("muffin"),
            fullName: "David Hovemeyer",
            email: "dhovemey@ycp.edu"
        )
        
        def r = new Role(roleType: Role.RoleType.REPORTER, program: compSci, scope: Role.Scope.ONE_PROGRAM)
        
        u.addToRoles(r)
        
        u.save(failOnError: true)
    }

    def createTestingTerms() {
        def terms = [
            new Term(name: "Winter", seq: 0),
            new Term(name: "Spring", seq: 1),
            new Term(name: "Summer", seq: 2),
            new Term(name: "Summer I", seq: 3),
            new Term(name: "Summer II", seq: 4),
            new Term(name: "Fall", seq: 5),
        ]
        
        terms.each { it.save(failOnError: true) }
        
        def ay2015_2016 = new AcademicYear(start: 2015, end: 2016)
        def ay2016_2017 = new AcademicYear(start: 2016, end: 2017)

        ay2015_2016.save(failOnError: true)
        ay2016_2017.save(failOnError: true)
    }
    
    def createTestingRubrics() {
        // programs need to have been created already
        def compSci = Program.findByName("Computer Science")
        
        def stdRubric = new Rubric(
            name: "Standard CS Rubric"
        )
        
        def needsImprovement = new AchievementLevel( name: "Needs Improvement", rank: 0 )
        def meetsExpectations = new AchievementLevel( name: "Meets Expectations", rank: 70 )
        def exceedsExpectations = new AchievementLevel( name: "Exceeds Expectations", rank: 85 )
        stdRubric.addToAchievementLevels(needsImprovement)
        stdRubric.addToAchievementLevels(meetsExpectations)
        stdRubric.addToAchievementLevels(exceedsExpectations)
        
        compSci.addToRubrics(stdRubric)
        compSci.save(failOnError: true)
    }
    
    def createTestingCourses() {
        // initial programs need to exist
        def compSci = Program.findByName("Computer Science")
        
        Course cs101 = new Course(
            shortName: "CS 101",
            title: "Fundamentals of Computer Science I"
        )
        compSci.addToCourses(cs101)
        
        compSci.save(failOnError: true)
    }
    
    def createTestingMeasurements() {
        // initial programs, rubrics, and academic years/terms need to exist
        def compSci = Program.findByName("Computer Science")
        def cs101 = Course.where { program == compSci && shortName == "CS 101" }.get()
        def spring = Term.where { name == "Spring" }.get()
        def ay2015_2016 = AcademicYear.where { start == 2015 && end == 2016 }.get()
        def stdRubric = Rubric.where { program == compSci && name == "Standard CS Rubric" }.get()
        def achievementLevels = stdRubric.achievementLevels.sort { it.rank }
        def indicator = Indicator.where { shortName == "a.1" }.get()
        
        def measurement = new Measurement(
            program: compSci,
            course: cs101,
            term: spring,
            academicYear: ay2015_2016,
            object: "Exam 4, Question 10, a programming question to search for the largest element in an array",
            discussion: "Several students had index out of bounds errors.",
            indicator: indicator,
            rubric: stdRubric
        )
        
        def needsImprovementTally = new AchievementLevelTally(
            count: 3,
            achievementLevel: achievementLevels[0],
            measurement: measurement
        )
        def meetsExpectationsTally = new AchievementLevelTally(
            count: 15,
            achievementLevel: achievementLevels[1],
            measurement: measurement
        )
        def exceedsExpectationsTally = new AchievementLevelTally(
            count: 4,
            achievementLevel: achievementLevels[2],
            measurement: measurement
        )
        
        measurement.save(failOnError: true)
    }
}
