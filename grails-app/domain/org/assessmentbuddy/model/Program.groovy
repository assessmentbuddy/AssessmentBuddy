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

/**
 * An academic program.
 */
class Program {
    String name

    static hasMany = [ outcomes : Outcome, courses : Course, rubrics : Rubric ]
    
    static constraints = {
        name size: 1..64
    }
}
