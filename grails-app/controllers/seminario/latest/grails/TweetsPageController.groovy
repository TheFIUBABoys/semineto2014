package seminario.latest.grails

import seminario.domain.Service
import seminario.domain.StatusUpdate

class TweetsPageController {
    def twitter4jService
    def statusClassifierService

    def index() {
        def serviceList = Service.findAll()
        def service = Service.findByName(params.service)
        def updates = StatusUpdate.findAllByService(service)

        [positiveUpdates: positiveUpdates(updates), negativeUpdates: negativeUpdates(updates), serviceList: serviceList]
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
