package seminario.domain

class Status {

    Integer accuracy
    StatusValue prediction

    Status(Integer accuracy, StatusValue prediction) {
        this.accuracy = accuracy
        this.prediction = prediction
    }

    static constraints = {
        prediction(blank: false)
    }

}

enum StatusValue{
    StatusValueUp,
    StatusValueDown
}
