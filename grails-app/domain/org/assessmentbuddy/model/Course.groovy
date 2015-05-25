package org.assessmentbuddy.model

class Course {
    String shortName
    String title
    
    static belongsTo = [ program : Program ]

    static constraints = {
    }
}
