package seminario.domain

class ServiceStatus {

    StatusClassifier statusClassifier

    ServiceStatus(StatusClassifier statusClassifier) {
        this.statusClassifier = statusClassifier
    }

    def classifyUpdates (Date sinceDate) {
        List<StatusUpdate> statusUpdates = StatusUpdate.findAllByCreatedAtGreaterThanEquals(sinceDate)
        return statusClassifier.classify(statusUpdates)
    }

    static constraints = {
    }

}
