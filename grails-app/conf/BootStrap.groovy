//import org.assessmentbuddy.model.InitialDataService

class BootStrap {
	def initialDataService

    def init = { servletContext ->
		initialDataService.createInitialPrograms()
		initialDataService.createInitialOutcomesAndIndicators()
		initialDataService.createInitialUsersAndRoles()
		initialDataService.createInitialTerms()
		initialDataService.createInitialRubrics()
		initialDataService.createInitialCourses()
		initialDataService.createInitialMeasurements()
    }
    def destroy = {
    }
}
