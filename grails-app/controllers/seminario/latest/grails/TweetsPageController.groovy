package seminario.latest.grails

import seminario.domain.StatusUpdate
import twitter4j.Query
import twitter4j.Status

class TweetsPageController {
    def twitter4jService
    def statusClassifierService

    def index() {
        def query = new Query("#TrenTigre +exclude:retweets")
        query.setCount(100)
        def result = twitter4jService.search(query)

        def tweets = result.getTweets().findAll{ tweet->
            //Only keep past 2 hours of tweets
            if (tweet.getCreatedAt().after(subtractHours(new Date(), 10000))) true
        }

        [positiveTweets: positiveTweets(tweets), negativeTweets: negativeTweets(tweets)]
    }

    private ArrayList negativeTweets(tweets) {
        tweets.findAll { tweet ->
            if (statusClassifierService.negativeScore(new StatusUpdate("twitter", tweet.text.toString())) >= 0.2) true
        }
    }

    private ArrayList positiveTweets(tweets) {
        tweets.findAll { tweet ->
            if (statusClassifierService.positiveScore(new StatusUpdate("twitter", tweet.text.toString())) >= 0.5) true
        }
    }

    public static Date subtractHours(Date date, Integer hours) {
        def cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, - hours);
        return cal.getTime();
    }
}
