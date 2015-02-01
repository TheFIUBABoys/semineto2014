package seminario.latest.grails

import seminario.domain.PublicTransport
import seminario.domain.StatusUpdate

class PredictionController {
    def twitter4jService
    def statusClassifierService
    def userService
    def serviceList

    def index() {
        serviceList = userService.getLocalUser().getFollowing()
        def selectedServiceId = params.serviceId
        if (!selectedServiceId) {
            selectedServiceId = serviceList[0].getId()
        }
        def serviceList = userService.getLocalUser().getFollowing()
        def publicTransport = PublicTransport.findById(selectedServiceId)
        def updates = StatusUpdate.findAllByTransport(publicTransport)
        [positiveUpdates: positiveUpdates(updates),
         negativeUpdates: negativeUpdates(updates),
         serviceList: serviceList,
         serviceName:publicTransport.getName(),
         serviceId: selectedServiceId]

    }

    def showService(){
        def selectedServiceId = params.service
        if (!selectedServiceId) {
            selectedServiceId = serviceList[0].getId()
        }
        redirect(action: 'index', params: [serviceId : selectedServiceId])
    }

    private ArrayList negativeUpdates(updates) {
        updates.findAll { update ->
            if (statusClassifierService.negativeScore(update) >= 0.2) true
        }
    }

    private ArrayList positiveUpdates(updates) {
        updates.findAll { update ->
            if (statusClassifierService.positiveScore(update) >= 0.5) true
        }
    }
}
