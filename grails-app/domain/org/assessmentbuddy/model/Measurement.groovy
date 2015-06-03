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

class Measurement {
    Program program
    Course course
    Term term
    AcademicYear academicYear
    String object // what was assessed, e.g., an assignment, exam question, etc.
    String discussion
    
    static belongsTo = [ indicator : Indicator, rubric : Rubric ]
    
    static hasMany = [ achievementLevelTallies : AchievementLevelTally ]

    static constraints = {
        // Some assessments don't occur in the context of a course
        course nullable : true
        object size: 1..255
        discussion size: 0..262144 // this will map onto a text blob
    }
}
