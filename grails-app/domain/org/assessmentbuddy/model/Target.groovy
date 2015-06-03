package org.assessmentbuddy.model

class Target {
    String name
    double percentAtOrAbove
    AchievementLevel achievementLevel

    static constraints = {
        name size: 1..64
    }
    
//    boolean isMet(Measurement measurement) {
//        def tallies = measurement.achievementLevelTallies*
//    }
}
