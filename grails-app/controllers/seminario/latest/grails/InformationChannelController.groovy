package seminario.latest.grails

import twitter4j.Query

class InformationChannelController {
    def static EXCLUDE_RETWEETS_QUERY = "+exclude:retweets"

    def twitter4jService

    def index() {

    }

    def buildQueryFromList(listOfKeywords) {
        def result = ""
        for (String keyword : listOfKeywords) {
            result += keyword + " OR "

        }
        result = result.substring(0, result.size() - 4) // Remove the last " OR "
        def query = new Query(result + EXCLUDE_RETWEETS_QUERY)
        query.setCount(100)
        return query
    }

    def show = {
        if (!params.keywords) {
            params.keywords = ["tren"]
        }
        def channelKeywords = params.keywords
        def query = buildQueryFromList(channelKeywords)
        def result = twitter4jService.search(query)

        def tweets = result.getTweets()
        [tweets: tweets, topics: params.keywords]
    }

}
