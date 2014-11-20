package seminario.domain

/**
 * Created by gonchub on 10/26/14.
 */
class Keyword {

    String phrase
    Integer score
    String type

    Keyword(String phrase, Integer score, String type) {
        this.phrase = phrase
        this.score = score
        this.type = type
    }

    static constraints = {
        type(in: ['up', 'down'])
        phrase(blank: false, unique: true)
    }

}
