package seminario.latest.grails

import seminario.domain.Service
import seminario.domain.StatusUpdate
import twitter4j.Query


class FetchTweetsJob {
    static triggers = {
      simple repeatInterval: 15*60*1000 // execute job once in 15 minutes
    }

    def twitter4jService

    def execute() {
        log.info("Job: FetchTweets Starting")
        // For each Service:
        def allServices = Service.getAll();
        allServices.each() { service ->
            // Get their twKeywords
            def keyWords = service.twKeyword
            def fetchedTweets = [:]
            keyWords.each() { word ->
                // Fetch the tweets for each keyword
                def query = new Query(word + " +exclude:retweets")
                query.setCount(100)
                def result = twitter4jService.search(query)

                def tweets = result.getTweets().findAll { tweet ->
                    if (tweet.getCreatedAt().after(TimeUtils.subtractMinutes(new Date(), 60))) true
                }

                tweets.each { tweet ->
                    fetchedTweets.put(tweet.id, [tweet: tweet, service: service])
                }

            }

            fetchedTweets.each() { id, data ->
                String screenName = data.tweet.getUser().screenName
                String tweetId = data.tweet.getId()
                String body = data.tweet.getText()
                Service currentService = data.service

                def newUpdate = new StatusUpdate('twitter', body, currentService)
                newUpdate.setSourceId(tweetId)
                newUpdate.setSourceUrl(TwitterUtils.getTweetUrl(screenName, tweetId))
                if (!newUpdate.save()) {
                    log.error ("Fail to save tweet with id: " + data.tweet.getId())
                }
            }

        }

        log.info("Job: FetchTweets Finishing")
    }


}
