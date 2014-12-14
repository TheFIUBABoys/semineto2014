package seminario.latest.grails

import grails.transaction.Transactional
import seminario.domain.Service

class SubscribeController {
    def userService

    def allServicesMap = new HashMap<String, Boolean>()

    def index() {
        def subscribedServiceList = userService.getSubscriptions()

        def allServices = Service.findAll()
        for (service in allServices) {
            if (service in subscribedServiceList) {
                allServicesMap.put(service.name, true)
            } else {
                allServicesMap.put(service.name, false)
            }
        }

        [allServices: allServicesMap]
    }

    def update = {
        def myParams = params
        allServicesMap.each { key, value ->
            if (myParams.containsKey(key)) {
                allServicesMap.put(key as String, true)
            } else {
                allServicesMap.put(key as String, false)
            }
        }
        this.saveFollows()
        redirect(action: 'index')
    }

    @Transactional
    def saveFollows() {
        def user = userService.getLocalUser()
        allServicesMap.each { key, value ->
            if (value) {
                user.addToFollowing(Service.findByName(key))
            } else {
                user.removeFromFollowing(Service.findByName(key))
            }
        }
    }
}
