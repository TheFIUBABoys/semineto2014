package seminario.domain

class Status {

    Integer accuracy
    String prediction

    Status(Integer accuracy, String prediction) {
        this.accuracy = accuracy
        this.prediction = prediction
    }

    static constraints = {
        prediction(blank: false)
    }

}
