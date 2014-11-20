package seminario.latest.grails

import seminario.domain.StatusUpdate

/**
 * Created by tomas on 20/11/14.
 */
class FlushTweetsJob {
    static triggers = {
        simple repeatInterval: 12*60*60*1000 // execute job once every 12 hours
    }

    def execute() {
        log.info("Job: FlushTweets Starting")
        def toErase = StatusUpdate.findAllByDateCreatedLessThan(TimeUtils.subtractHours(new Date(),6));
        log.debug(toErase)
        log.info("Job: FlushTweets Finishing")
    }
}
