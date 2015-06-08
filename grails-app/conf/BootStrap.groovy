//import org.assessmentbuddy.model.InitialDataService

class BootStrap {
    def initialDataService

    def init = { servletContext ->
        initialDataService.createInitialAdminUser()
    }
    def destroy = {
    }
}
