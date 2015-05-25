package org.assessmentbuddy.model

class Outcome {
    String shortName
    String description

    static belongsTo = [ program : Program ]

    static constraints = {
    }
}
