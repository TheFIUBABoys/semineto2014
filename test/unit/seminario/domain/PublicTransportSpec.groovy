package seminario.domain

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(PublicTransport)
class PublicTransportSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        when: 'the name begins with a lower letter'
        then: 'validation should fail'
    }
}
