package seminario.domain

import com.seminario.User

class PublicTransport {

    String name
    ServiceStatus mostRecentStatus
    Date lastStatusTime

    //Belongs to is for many to many relations
    static belongsTo = User
    static hasMany = [twKeyword: String, subscriptor: User]

    PublicTransport(String name) {
        this.name = name
    }

    def updateStatus(ServiceStatus serviceStatus) {
        mostRecentStatus = serviceStatus
        lastStatusTime = new Date()
    }

    def retrieveCurrentStatus() {
        return mostRecentStatus
    }

    def getNextUpdatedStatus(List<StatusUpdate> statusUpdates){
        //TODO - Considering the received statuses and the current one, return a new updated status.
    }

    def isOnline(){
        return mostRecentStatus.status.prediction.equals(Status.StatusValue.StatusValueUp)
    }

    static constraints = {
        name(blank: false, unique: true)
        mostRecentStatus(nullable: true)
        lastStatusTime(nullable: true)
    }

}
