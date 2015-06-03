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
