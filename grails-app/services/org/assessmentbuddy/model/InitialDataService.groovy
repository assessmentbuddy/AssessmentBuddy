package org.assessmentbuddy.model

import grails.transaction.Transactional

@Transactional
class InitialDataService {

    def createInitialTerms() {
        def terms = [
            new Term(name: "Winter", seq: 0),
            new Term(name: "Spring", seq: 1),
            new Term(name: "Summer", seq: 2),
            new Term(name: "Summer I", seq: 3),
            new Term(name: "Summer II", seq: 4),
            new Term(name: "Fall", seq: 5),
        ]
        
        terms.each {
            $it.save()
        }
    }
}
