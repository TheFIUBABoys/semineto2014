package seminario.latest.grails

import grails.transaction.Transactional
import seminario.domain.Keyword
import seminario.domain.Status
import seminario.domain.StatusUpdate

@Transactional
class StatusClassifierService {

    static List<Keyword> upKeywords = Keyword.findAllByType('up')
    static List<Keyword> downKeywords = Keyword.findAllByType('down')

    def classify(StatusUpdate statusUpdate) {
        Float positiveScore = positiveScore(statusUpdate)
        Float negativeScore = negativeScore(statusUpdate)

        Float accuracy = positiveScore >= negativeScore ? positiveScore : negativeScore
        String prediction = positiveScore >= negativeScore ? 'up' : 'down'

        // Do some stuff with the status update and reach a prediction with an accuracy.
        return new Status(accuracy, prediction)
    }

    def classify(List<StatusUpdate> statusUpdates) {
        List<Status> statuses = statusUpdates.collect { statusUpdate -> classify(statusUpdate) }
        Map grouped = statuses.groupBy { status -> status.prediction }
        grouped = grouped.collectEntries { prediction, List<Status> statusGroup ->
            Float totalScore = statusGroup.inject(0) { acc, Status status -> acc + status.accuracy }
            [(prediction): totalScore / statusGroup.size()]
        }

        // Ugly way to find max, but Map's max returns 'Entry' and is kind of not typesafe (object, object)
        String maxPrediction = statuses.first().prediction
        Float maxAccuracy = statuses.first().accuracy
        grouped.each { String prediction, Float accuracy ->
            if (accuracy > maxAccuracy) {
                maxPrediction = prediction
                maxAccuracy = accuracy
            }
        }
        return new Status(maxAccuracy, maxPrediction)
    }

    private static positiveScore(StatusUpdate statusUpdate) {
        scoreHelper(statusUpdate, upKeywords)
    }

    private static negativeScore(StatusUpdate statusUpdate) {
        scoreHelper(statusUpdate, downKeywords)
    }

    private static scoreHelper(StatusUpdate statusUpdate, List<Keyword> keywords) {
        keywords.inject(0.0f) { Float acc, Keyword keyword ->
            if (statusUpdate.body.toLowerCase().count(keyword.phrase) > 0) acc + keyword.score
            else acc
        }
    }
}
