package seminario.domain

import com.seminario.User
import org.apache.commons.lang.StringUtils

//Class to wrap the tweets
class StatusUpdate {
    String sourceType
    String sourceUrl
    String sourceId
    Date dateCreated
    Date sourceDateCreated
    String body
    PublicTransport transport
    User user
    Integer score;

    StatusUpdate(String sourceType, String body, PublicTransport transport) {
        this.sourceType = sourceType
        this.body = body
        this.transport = transport
        this.dateCreated = new Date()
        process()
    }

    def wordsAreVerySimilar(String word1, String word2) {
        def distance = StringUtils.getLevenshteinDistance(word1, word2)
        return (distance >= 0.9)
    }

    def rate() {
        def keywords = Keyword.findAll()
        for (Keyword k in keywords) {
            for (String word in body.split(' ')) {
                if (wordsAreVerySimilar(k.phrase, word)) {
                    //Contains the keyword
                    if (k.type.equals('up')) {
                        score += k.score
                    } else {
                        score -= k.score
                    }
                }
            }
        }
    }

    def rateForStation() {
        //TODO - To show a list of stations with a score per station
        rate()
    }

    def process() {
        //First check if the tweet has a station
        def stations = transport.getStations()
        def alreadyRated = false
        for (Station station in stations) {
            for (String word in body.split(' ')) {
                if (wordsAreVerySimilar(word, station.getName())) {
                    //There's a station's name in the tweet
                    //Posibly move the new status generation to the status class
                    rateForStation()
                    alreadyRated = true
                    break;
                }
            }
            if (alreadyRated) {
                break;
            }
        }
        //Even if we rated for the station, we rate for the global public transport status
        rate()
    }


    static constraints = {
        sourceType(blank: false)
        body(blank: false)
        sourceId(nullable: true)
        sourceUrl(nullable: true)
        sourceDateCreated(nullable: true)
        user(nullable: true)
    }

}
