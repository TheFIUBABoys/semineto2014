package seminario.domain

import com.seminario.User

class Service {

    String name
    ServiceStatus lastStatus
    Date lastStatusTime

    //Belongs to is for many to many relations
    static belongsTo = User
    static hasMany = [twKeyword: String, subscriptor: User]

    Service(String name) {
        this.name = name
    }

    def updateStatus(ServiceStatus serviceStatus) {
        lastStatus = serviceStatus
        lastStatusTime = new Date()
    }

    def retrieveCurrentStatus() {
        return lastStatus
    }

    static constraints = {
        name(blank: false, unique: true)
        lastStatus(nullable: true)
        lastStatusTime(nullable: true)
    }

}
