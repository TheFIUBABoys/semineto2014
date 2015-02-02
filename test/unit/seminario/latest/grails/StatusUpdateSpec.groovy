package seminario.latest.grails

import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import seminario.domain.StatusUpdate
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(StatusUpdate)
class StatusUpdateSpec extends Specification {

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
