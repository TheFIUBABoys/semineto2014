package seminario.domain

class ServiceStatus {

    Service service
    Status status

    ServiceStatus(Service service, Status status) {
        this.service = service
        this.status = status
    }

    static constraints = {
    }

}
