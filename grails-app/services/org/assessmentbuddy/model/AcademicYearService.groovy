package org.assessmentbuddy.model

import grails.transaction.Transactional

@Transactional
class AcademicYearService {
    AcademicYear findAcademicYearForId(long id) {
        AcademicYear academicYear = AcademicYear.get(id)
        if (!academicYear) {
            throw new NoSuchIdException("No academic year found for id ${id}", id)
        }
        return academicYear
    }
    
    def saveAcademicYear(academicYearParams) {
        def academicYearToSave
        if (academicYearParams.id) {
            // Updating existing academic year
            academicYearToSave = AcademicYear.get(academicYearParams.id.toLong())
            if (!academicYearToSave) {
                throw new NoSuchIdException("No academic year found for id ${academicYearParams.id}", academicYearParams.id.toLong())
            }
            // Update properties
            academicYearToSave.properties = academicYearParams
        } else {
            // Creating new academic year
            academicYearToSave = new AcademicYear(academicYearParams)
        }
        
        if (!academicYearToSave.save()) {
            throw new SaveFailedException("Could not save academic year", academicYearToSave)
        }
    }
}
