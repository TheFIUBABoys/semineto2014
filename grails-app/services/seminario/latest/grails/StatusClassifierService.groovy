package seminario.latest.grails

import grails.transaction.Transactional
import seminario.domain.Status
import seminario.domain.StatusUpdate

@Transactional
class StatusClassifierService {

    def classify(StatusUpdate statusUpdate) {
        // Do some stuff with the status update and reach a prediction with an accuracy.
        return new Status(80, 'up')
    }

    def classify(List<StatusUpdate> statusUpdates) {
        List<Status> statuses = statusUpdates.collect { statusUpdate -> classify(statusUpdate) }
        Map grouped = statuses.groupBy { status -> status.prediction }
        grouped = grouped.collectEntries { prediction, List<Status> statusGroup ->
            Float totalScore = statusGroup.inject(0) { acc, Status status -> acc + status.accuracy }
            [(prediction): totalScore / statusGroup.size()]
        }

        // Ugly way to find max, but Map's max returns 'Entry' and is kind of not typesafe (object, object)
        String maxPrediction
        Integer maxAccuracy = -1
        grouped.each { String prediction, Integer accuracy ->
            if (accuracy > maxAccuracy) {
                maxPrediction = prediction
                maxAccuracy = accuracy
            }
        }
        return new Status(maxAccuracy, maxPrediction)
    }

}
