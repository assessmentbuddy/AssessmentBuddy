package org.assessmentbuddy.model

class Term {
    String name
    int seq

    static constraints = {
        name unique: true, size: 1..64
    }
}
