package seminario.domain

class Status {

    Integer accuracy
    String statusString

    Status(Integer accuracy, String statusString) {
        this.accuracy = accuracy
        this.statusString = statusString
    }

    static constraints = {
        statusString(blank: false)
    }
}
