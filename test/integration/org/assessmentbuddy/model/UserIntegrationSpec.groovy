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

import grails.test.spock.IntegrationSpec

class UserIntegrationSpec extends IntegrationSpec {
    def bcryptService
    def initialDataService

    def setup() {
        initialDataService.createInitialPrograms()
        initialDataService.createInitialUsersAndRoles()
    }

    def cleanup() {
    }
    
    def "change a user property"() {
        given: "an existing user"
        
        when: "a property is changed"
        def loadedUser = User.findByUserName("dhovemey")
        loadedUser.email = "david.hovemeyer@gmail.com"
        loadedUser.save()
        
        then: "changed property is loaded from the database"
        User.findByUserName("dhovemey").email == "david.hovemeyer@gmail.com"
    }
    
    def "change user password"() {
        given: "an existing user"
        
        when: "the password is changed"
        def loadedUser = User.findByUserName("dhovemey")
        loadedUser.passwordHash = bcryptService.hashPassword("frotz")
        loadedUser.save()
        
        then: "the new password is in effect"
        bcrypt.checkPassword("frotz", User.findByUserName("dhovemey").passwordHash)
    }
    
    def "check that user role exists"() {
        given: "an existing user"
        
        when: "users are loaded"
        def compSci = Program.findByName("Computer Science")
        def admin = User.findByUserName("admin")
        def u = User.findByUserName("dhovemey")
        
        then: "the users' roles exist"
        admin != null
        u != null
        
        def adminRoles = admin.roles
        adminRoles.size() == 1
        adminRoles[0].roleType == Role.RoleType.ADMIN
        adminRoles[0].scope == Role.Scope.ALL_PROGRAMS
        adminRoles[0].program == null

        def roles = u.roles
        roles.size() == 1
        roles[0].roleType == Role.RoleType.REPORTER
        roles[0].scope == Role.Scope.ONE_PROGRAM
        roles[0].program.id == compSci.id
    }
}
