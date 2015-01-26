package seminario.latest.grails

import seminario.domain.PublicTransport
import seminario.domain.StatusUpdate

class PredictionController {
    def twitter4jService
    def statusClassifierService

    def index() {
        def serviceList = PublicTransport.findAll()
        def service = PublicTransport.findByName("Linea Urquiza") // TODO: Need to fetch the Service in the select
        def updates = StatusUpdate.findAllByService(service)

        [positiveUpdates: positiveUpdates(updates), negativeUpdates: negativeUpdates(updates), serviceList: serviceList, serviceName: "Linea Urquiza"]
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
