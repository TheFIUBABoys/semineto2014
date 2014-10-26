package seminario.domain

class Service {

    String name
    ServiceStatus lastStatus
    Date lastStatusTime

    Service(String name) {
        this.name = name
    }

    def updateStatus (ServiceStatus newStatusUpdate) {
        lastStatus = newStatusUpdate
        lastStatusTime = new Date()
    }

    def retrieveCurrentStatus () {
        return lastStatus
    }

    static constraints = {
        name(blank: false, unique: true)
    }

}
