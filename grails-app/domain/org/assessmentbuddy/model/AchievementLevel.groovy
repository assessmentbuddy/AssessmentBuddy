package org.assessmentbuddy.model

class AchievementLevel {
    String name
    int rank
    
    static belongsTo = [ rubric : Rubric ]
    
    static hasMany = [ achievementLevelTallies : AchievementLevelTally, targets : Target ]

    static constraints = {
    }
}
