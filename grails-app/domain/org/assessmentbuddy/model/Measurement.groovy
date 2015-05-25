package org.assessmentbuddy.model

class Measurement {
    Course course
    Term term
    int year
    AcademicYear academicYear
    String object // what was assessed, e.g., an assignment, exam question, etc.
    String discussion
    
    static belongsTo = [ indicator : Indicator, rubric : Rubric ]
    
    static hasMany = [ achievementLevelTallies : AchievementLevelTally ]

    static constraints = {
        // Some assessments don't occur in the context of a course
        course nullable : true
    }
}
