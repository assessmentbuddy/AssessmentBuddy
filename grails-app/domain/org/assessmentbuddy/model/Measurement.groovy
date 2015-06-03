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
