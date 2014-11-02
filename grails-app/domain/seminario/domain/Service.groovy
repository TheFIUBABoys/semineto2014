package seminario.domain

class Service {

    String name
    ServiceStatus lastStatus
    Date lastStatusTime

    static hasMany = [twKeyword: String]

    Service(String name) {
        this.name = name
    }

    def updateStatus (ServiceStatus serviceStatus) {
        lastStatus = serviceStatus
        lastStatusTime = new Date()
    }

    def retrieveCurrentStatus () {
        return lastStatus
    }

    static constraints = {
        name(blank: false, unique: true)
        lastStatus(nullable: true)
        lastStatusTime(nullable: true)
    }

}
