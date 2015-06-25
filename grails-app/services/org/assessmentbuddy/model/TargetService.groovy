package org.assessmentbuddy.model

import grails.transaction.Transactional

@Transactional
class TargetService {

    def findTargetsForProgram(aProgram) {
        return Target.executeQuery(
            "select t from Target as t " +
            " where t.rubric.program.id = :aProgramId",
            [aProgramId: aProgram.id]
        )
    }
}
