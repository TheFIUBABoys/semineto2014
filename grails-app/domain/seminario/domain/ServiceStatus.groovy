package seminario.domain

class ServiceStatus {

    PublicTransport service
    Status status

    ServiceStatus(PublicTransport service, Status status) {
        this.service = service
        this.status = status
    }

    static constraints = {
    }

}
