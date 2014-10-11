package seminario.latest.grails

import twitter4j.Query

class LandingPageController {
    def twitter4jService

    def downKeywords = [
            "no viene": 0.2,
            "no sale" : 1.0,
            "parado"  : 0.4,
            "demorado": 0.2
    ]
    def upKeywords = [
            "en horario": 0.5,
            "saliendo"  : 0.2,
            "viajando"  : 0.4,
            "llegando"  : 0.3
    ]

    def index() {}

    def tweets() {
        def query = new Query("tren tigre +exclude:retweets");
        query.setCount(100);
        def result = twitter4jService.search(query);

        def tweets = result.getTweets()
        def positiveTweets = tweets.findAll { tweet ->
            def score = downKeywords.keySet().inject(0) { acc, key ->
                if (tweet.text.count(key) > 0) acc + downKeywords[key]
                else acc
            }
            if (score >= 0.5) true
        }
        def negativeTweets = tweets.findAll { tweet ->
            def score = upKeywords.keySet().inject(0) { acc, key ->
                if (tweet.text.count(key) > 0) acc + upKeywords[key]
                else acc
            }
            if (score >= 0.2) true
        }

        [positiveTweets: positiveTweets, negativeTweets: negativeTweets]
    }
}
