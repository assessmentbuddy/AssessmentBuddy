package org.assessmentbuddy.model

class Target {
    String name
    double percentAtOrAbove
    
    static belongsTo = [ achievementLevel: AchievementLevel ]

    static constraints = {
        name size: 1..64
    }
}
