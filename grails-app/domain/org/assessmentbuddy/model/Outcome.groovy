package org.assessmentbuddy.model

class Outcome {
    String shortName
    String description

    static belongsTo = [ program : Program ]
    
    static hasMany = [ indicators : Indicator ]

    static constraints = {
        shortName size: 1..40
        description size: 1..255
    }
}
