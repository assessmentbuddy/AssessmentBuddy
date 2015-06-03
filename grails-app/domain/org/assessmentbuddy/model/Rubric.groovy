package org.assessmentbuddy.model

class Rubric {
    String name
    
    static belongsTo = [ program : Program ]
    
    static hasMany = [ achievementLevels : AchievementLevel ]

    static constraints = {
        name size: 1..64
    }
}
