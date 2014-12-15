package seminario.domain

import grails.rest.*

@Resource(uri='/api/predictionRating', formats=['json'])
class PredictionRating {
    String comment
    Boolean isPositive
    static constraints = {
        comment(blank: false)
    }
}
