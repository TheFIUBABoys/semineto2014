package seminario.latest.grails

import grails.converters.deep.JSON
import groovyjarjarantlr.collections.List
import seminario.domain.Service
import seminario.domain.StatusUpdate

class ServiceStatusController {
    def statusClassifierService

    def negative() {
        if (params.id && Service.exists(params.id)) {
            def service = Service.findById(params.id) // TODO: Need to fetch the Service in the select
            def updates = StatusUpdate.findAllByService(service)

            render negativeUpdates(updates) as JSON
        }

    }

    def positive() {
        if (params.id && Service.exists(params.id)) {
            def service = Service.findById(params.id) // TODO: Need to fetch the Service in the select
            def updates = StatusUpdate.findAllByService(service)

            render positiveUpdates(updates) as JSON
        }

    }

    def all() {
        render Service.getAll() as JSON
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
