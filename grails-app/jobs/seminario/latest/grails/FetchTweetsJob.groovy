package seminario.latest.grails

import seminario.domain.PublicTransport
import seminario.domain.StatusUpdate
import twitter4j.Query


class FetchTweetsJob {
    static triggers = {
      simple repeatInterval: 15*60*1000 // execute job once in 15 minutes
    }

    def twitter4jService

    def execute() {
        log.info("Job: FetchTweets Starting")
        /*
        Este metodo es una cagada por:
         - No nos interesa para NADA guardar los tweets una vez que los usamos para predecir.
         - No tiene en cuenta para nada el peso de las keywords.
         */
        // For each Service:
        def allServices = PublicTransport.getAll();
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
                Date createdAt = data.tweet.getCreatedAt()
                PublicTransport currentService = data.service

                if (!StatusUpdate.findBySourceId(tweetId)){
                    def newUpdate = new StatusUpdate('twitter', body, currentService)
                    newUpdate.setSourceId(tweetId)
                    newUpdate.setSourceUrl(TwitterUtils.getTweetUrl(screenName, tweetId))
                    newUpdate.setSourceDateCreated(createdAt)
                    if (!newUpdate.save()) {
                        log.error ("Fail to save tweet with id: " + data.tweet.getId())
                    }
                }
            }

        }

        log.info("Job: FetchTweets Finishing")
    }


}
