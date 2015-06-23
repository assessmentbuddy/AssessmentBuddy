package org.assessmentbuddy.model

import grails.transaction.Transactional

@Transactional
class IndicatorService {
    def saveIndicator(indicatorParams, outcome) {
        def indicatorToSave
        
        if (indicatorParams.id) {
            // Updating existing indicator
            indicatorToSave = Indicator.get(indicatorParams.id.toLong())
            if (!indicatorToSave) {
                throw new NoSuchIdException("No indicator found for id ${indicatorParams.id}")
            }
            // Update properties and outcome
            indicatorToSave.properties = indicatorParams
            indicatorToSave.outcome = outcome
        } else {
            // Creating a new indicator
            indicatorToSave = new Indicator(indicatorParams)
            indicatorToSave.outcome = outcome
        }
        
        if (!indicatorToSave.save()) {
            throw new SaveFailedException("Could not save indicator", indicatorToSave)
        }
    }
}
