package seminario.domain

/**
 * Created by gonchub on 10/26/14.
 */
class Keyword {

    def downKeywords = [
            "no viene": 0.2,
            "no sale" : 1.0,
            "parado"  : 0.4,
            "demorado": 0.2,
            "mierda": 1.0,
            "choto": 1.0,
            "te odio": 1.0,
            "tarde":0.4,
            "lleno":0.2
    ]
    def upKeywords = [
            "en horario": 0.5,
            "bien": 0.3,
            "perfecto": 0.5,
            "rapido": 0.3,
            "te amo": 1.0,
            "saliendo"  : 0.2,
            "viajando"  : 0.4,
            "no me puedo quejar"  : 1.0,
            "llegando"  : 0.3
    ]

    String phrase
    Float score
    String type

    Keyword(String phrase, Float score, String type) {
        this.phrase = phrase
        this.score = score
        this.type = type
    }

    static constraints = {
        type(in: ['up', 'down'])
        phrase(blank: false, unique: true)
    }

}
