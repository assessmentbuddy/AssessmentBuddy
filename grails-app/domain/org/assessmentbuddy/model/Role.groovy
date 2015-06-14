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

class Role {
    enum RoleType {
        /** An admin can do anything, add users, etc. */
        ADMIN,
        /** A reporter can add measures and view data.. */
        REPORTER,
        /** A viewer can view data. */
        VIEWER;
        
        String toString() {
            String s = name();
            return s[0] + s.substring(1).toLowerCase();
        }
    }
    
    enum Scope {
        /** Role is limited to a specified program. */
        ONE_PROGRAM,
        /** Role extends to all programs. */
        ALL_PROGRAMS;
        
        String toString() {
            String s = name().replace('_', ' ');
            return s[0] + s.substring(1).toLowerCase();
        }
    }
    
    RoleType roleType
    Scope scope
    Program program

    static belongsTo = [ user : User ]
    
    static constraints = {
        program nullable: true
    }
}
