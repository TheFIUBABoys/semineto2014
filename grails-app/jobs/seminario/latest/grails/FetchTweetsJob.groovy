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
                    if (tweet.getCreatedAt().after(subtractMinutes(new Date(), 60))) true
                }

                tweets.each {tweet ->
                    fetchedTweets.put(tweet.id, tweet)
                }

            }

        }

        log.info("Job: FetchTweets Finishing")
    }

    public static Date subtractMinutes(Date date, Integer minutes) {
        def cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, - minutes);
        return cal.getTime();
    }


}
