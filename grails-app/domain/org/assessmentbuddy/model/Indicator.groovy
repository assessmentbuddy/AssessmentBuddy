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

class Indicator {
    String shortName
    String description
    
    static belongsTo = [ outcome : Outcome ]
    
    static hasMany = [ measurements : Measurement ]

    static constraints = {
        shortName size: 1..40
        description size: 1..255
    }
    
    static List getIndicatorsFor(Program program) {
        return executeQuery(
            "  select i from Indicator i, Outcome o " +
            "   where i.outcome = o " +
            "     and o.program.id = :programId " +
            "order by o.shortName, i.shortName",
            [ programId: program.id ]
        )
    }
    
    String toDisplay() {
        return outcome.shortName + "." + shortName + " — " + description
    }
}
