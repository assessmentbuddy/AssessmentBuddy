package org.assessmentbuddy.model

class Course {
    String shortName
    String title
    
    static belongsTo = [ program : Program ]

    static constraints = {
        shortName size: 1..40
        title size: 1..255
    }
}
