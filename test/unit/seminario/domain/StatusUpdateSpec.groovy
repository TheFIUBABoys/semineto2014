package seminario.domain

import grails.test.mixin.TestFor
import grails.test.spock.IntegrationSpec
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
class StatusUpdateSpec extends IntegrationSpec {

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
