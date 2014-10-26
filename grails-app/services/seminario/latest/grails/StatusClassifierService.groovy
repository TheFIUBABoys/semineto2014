package seminario.latest.grails

import grails.transaction.Transactional
import seminario.domain.Keyword
import seminario.domain.Status
import seminario.domain.StatusUpdate

@Transactional
class StatusClassifierService {

    def classify(StatusUpdate statusUpdate) {
        Integer positiveScore = positiveScore(statusUpdate)
        Integer negativeScore = negativeScore(statusUpdate)

        Integer accuracy = positiveScore >= negativeScore ? positiveScore : negativeScore
        String prediction = positiveScore >= negativeScore ? 'up' : 'down'

        // Do some stuff with the status update and reach a prediction with an accuracy.
        return new Status(accuracy, prediction)
    }

    def classify(List<StatusUpdate> statusUpdates) {
        List<Status> statuses = statusUpdates.collect { statusUpdate -> classify(statusUpdate) }
        Map grouped = statuses.groupBy { status -> status.prediction }
        grouped = grouped.collectEntries { prediction, List<Status> statusGroup ->
            Integer totalScore = statusGroup.inject(0) { acc, Status status -> acc + status.accuracy }
            [(prediction): (100 * totalScore) / statusGroup.size()]
        }

        // Ugly way to find max, but Map's max returns 'Entry' and is kind of not typesafe (object, object)
        String maxPrediction = statuses.first().prediction
        Integer maxAccuracy = statuses.first().accuracy
        grouped.each { String prediction, Integer accuracy ->
            if (accuracy > maxAccuracy) {
                maxPrediction = prediction
                maxAccuracy = accuracy
            }
        }
        return new Status(maxAccuracy, maxPrediction)
    }

    def positiveScore(StatusUpdate statusUpdate) {
        scoreHelper(statusUpdate, Keyword.findAllByType('up'))
    }

    def negativeScore(StatusUpdate statusUpdate) {
        scoreHelper(statusUpdate, Keyword.findAllByType('down'))
    }

    private scoreHelper(StatusUpdate statusUpdate, List<Keyword> keywords) {
        keywords.inject(0) { Integer acc, Keyword keyword ->
            if (statusUpdate.body.toLowerCase().count(keyword.phrase) > 0) acc + keyword.score
            else acc
        }
    }
}
