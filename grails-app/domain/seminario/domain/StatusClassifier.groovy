package seminario.domain

class StatusClassifier {

    ClassifierStrategy stategy

    StatusClassifier(ClassifierStrategy stategy) {
        this.stategy = stategy
    }

    def classify (List<StatusUpdate> statusUpdates) {
        stategy.classify(statusUpdates)
    }

    static constraints = {
    }

}
