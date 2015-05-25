package org.assessmentbuddy.model

class AchievementLevelTally {
    int count
    
    static belongsTo = [ achievementLevel : AchievementLevel, measurement : Measurement ]

    static constraints = {
    }
}
