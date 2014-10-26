package seminario.domain

class Status {

    Float accuracy
    String prediction

    Status(Float accuracy, String prediction) {
        this.accuracy = accuracy
        this.prediction = prediction
    }

    static constraints = {
        prediction(blank: false)
    }

}
