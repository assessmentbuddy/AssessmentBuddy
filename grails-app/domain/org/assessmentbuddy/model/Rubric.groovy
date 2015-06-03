package org.assessmentbuddy.model

class Rubric {
    String name
    
    static belongsTo = [ program : Program ]
    
    static hasMany = [ achievementLevels : AchievementLevel, measurement : Measurement ]

    static constraints = {
        name size: 1..64
    }
}
