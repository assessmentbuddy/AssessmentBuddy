package org.assessmentbuddy.model

class Rubric {
    String name
    
    static hasMany = [ achievementLevels : AchievementLevel, measurement : Measurement ]

    static constraints = {
    }
}
