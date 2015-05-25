package org.assessmentbuddy.model

class Indicator {
    String shortName
    String description
    
    static belongsTo = [ outcome : Outcome ]
    
    static hasMany = [ measurements : Measurement ]

    static constraints = {
    }
}
