package seminario.domain

class ServiceStatus {

    StatusClassifier statusClassifier

    ServiceStatus(StatusClassifier statusClassifier) {
        this.statusClassifier = statusClassifier
    }

    def classifyUpdates () {
        // Somehow this returns a Status
        return statusClassifier.classify()
    }

    static constraints = {
    }
}
