package seminario.latest.grails

import grails.transaction.Transactional
import seminario.domain.Service
import seminario.domain.ServiceStatus
import seminario.domain.Status
import seminario.domain.StatusUpdate

@Transactional
class ServiceStatusService {

    def statusClassifierService

    @Override
    Object getProperty(String property) {
        return super.getProperty(property)
    }

    def determineStatus (Service service, Date sinceDate) {
        List<StatusUpdate> statusUpdates = StatusUpdate.findAllByCreatedAtGreaterThanEqualsAndService(sinceDate, service)

        Status status = statusClassifierService.classify(statusUpdates)
        ServiceStatus serviceStatus = new ServiceStatus(service, status)

        // Save a new service status instead of updating the service's one to save
        // the service's history of status update in db.
        serviceStatus.save()
    }
}
