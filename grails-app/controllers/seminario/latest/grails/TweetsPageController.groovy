package seminario.latest.grails

import twitter4j.Query
import twitter4j.Status

class TweetsPageController {
    def twitter4jService

    def downKeywords = [
            "no viene": 0.2,
            "no sale" : 1.0,
            "parado"  : 0.4,
            "demorado": 0.2,
            "mierda": 1.0,
            "tarde":0.4,
            "lleno":0.2
    ]
    def upKeywords = [
            "en horario": 0.5,
            "saliendo"  : 0.2,
            "viajando"  : 0.4,
            "llegando"  : 0.3
    ]

    def index() {
        def query = new Query("tren tigre +exclude:retweets")
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
            if (negativeScore(tweet) >= 0.2) true
        }
    }

    private ArrayList positiveTweets(tweets) {
        tweets.findAll { tweet ->
            if (positiveScore(tweet) >= 0.5) true
        }
    }

    private positiveScore(tweet) {
        upKeywords.keySet().inject(0) { acc, key ->
            if (tweet.text.count(key) > 0) acc + upKeywords[key]
            else acc
        }
    }

    private negativeScore(tweet) {
        downKeywords.keySet().inject(0) { acc, key ->
            if (tweet.text.count(key) > 0) acc + downKeywords[key]
            else acc
        }
    }

    public static Date subtractHours(Date date, Integer hours) {
        def cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, - hours);
        return cal.getTime();
    }
}
