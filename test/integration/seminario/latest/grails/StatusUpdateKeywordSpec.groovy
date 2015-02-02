package seminario.latest.grails

import seminario.domain.StatusUpdate
import spock.lang.*

/**
 *
 */
class StatusUpdateKeywordSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }


    void "test rating positive"() {
        when:
        def statusUpdate = new StatusUpdate()
        statusUpdate.body = "interrumpido"
        statusUpdate.rate()

        then:
        statusUpdate.score < 0

    }
}
