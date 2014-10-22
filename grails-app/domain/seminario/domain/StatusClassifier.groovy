package seminario.domain

class StatusClassifier {

    ClassifierStrategy stategy

    StatusClassifier(ClassifierStrategy stategy) {
        this.stategy = stategy
    }

    def classify () {
        // What are we classifing.
        // Probably we are going to need a list of Updates
        stategy.classify()
    }

    static constraints = {
    }
}
