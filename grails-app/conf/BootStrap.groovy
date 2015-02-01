import com.seminario.User
import grails.transaction.Transactional
import org.apache.shiro.crypto.hash.Sha256Hash
import seminario.domain.Keyword
import seminario.domain.PredictionRating
import seminario.domain.PublicTransport

@Transactional
class BootStrap {
    def createUser = {
        def user = new User(username: "user123", passwordHash: new Sha256Hash("password").toHex())
        user.addToPermissions("*:*")
        user.save()
    }

    def createKeyWords = {
        def downKeywords = [
                "no viene": 20,
                "no sale" : 10,
                "clavado"  : 10,
                "porqueria"  : 10,
                "interrumpido"  : 10,
                "detenida"  : 10,
                "parado"  : 10,
                "demorado": 2,
                "servicio interrumpido": 20,
                "cancelado": 10,
                "suspendido": 10,
                "cancelaciones": 10,
                "demoras": 10,
                "demoras y cancelaciones": 20,
                "mierda": 10,
                "choto": 10,
                "te odio": 10,
                "tarde": 10,
                "lleno": 2
        ]

        def upKeywords = [
                "a horario": 5,
                "bien": 3,
                "perfecto": 5,
                "rápido": 3,
                "servicio normal": 10,
                "te amo": 10,
                "saliendo"  : 2,
                "viajando"  : 4,
                "no me puedo quejar"  : 3,
                "llegando"  : 3
        ]

        upKeywords.each { String key, Integer value ->
            def keyword = new Keyword(key, value, 'up')
            keyword.save()
        }

        downKeywords.each { String key, Integer value ->
            def keyword = new Keyword(key, value, 'down')
            keyword.save()
        }
    }

    def createBaseServices = {
        def trenTigre = PublicTransport.findByName('Linea Mitre Ramal Tigre')
        if (!trenTigre) {
            trenTigre = new PublicTransport('Linea Mitre Ramal Tigre')
        }
        trenTigre.addToTwKeyword('#TrenTigre')
        trenTigre.addToTwKeyword('tren tigre')
        trenTigre.save(failOnError: true);

        def trenSuarez = PublicTransport.findByName('Linea Mitre Ramal Suarez')
        if (!trenSuarez) {
            trenSuarez = new PublicTransport('Linea Mitre Ramal Suarez')
        }
        trenSuarez.addToTwKeyword('#TrenSuarez')
        trenSuarez.addToTwKeyword('tren suarez')
        trenSuarez.save(failOnError: true);

        def trenMitre = PublicTransport.findByName('Linea Mitre Ramal Mitre')
        if (!trenMitre) {
            trenMitre = new PublicTransport('Linea Mitre Ramal Mitre')
        }
        trenMitre.addToTwKeyword('#TrenMitre')
        trenMitre.addToTwKeyword('tren mitre')
        trenMitre.save(failOnError: true);

        def trenBelgranoNorte = PublicTransport.findByName('Linea Belgrano Norte')
        if (!trenBelgranoNorte) {
            trenBelgranoNorte = new PublicTransport('Linea Belgrano Norte')
        }
        trenBelgranoNorte.addToTwKeyword('#TrenBelgrano')
        trenBelgranoNorte.addToTwKeyword('tren belgrano')
        trenBelgranoNorte.save(failOnError: true);

        def trenSanMartin = PublicTransport.findByName('Linea San Martin')
        if (!trenSanMartin) {
            trenSanMartin = new PublicTransport('Linea San Martin')
        }
        trenSanMartin.addToTwKeyword('#TrenSanMartion')
        trenSanMartin.addToTwKeyword('tren san martin')
        trenSanMartin.save(failOnError: true);

        def trenUrquiza = PublicTransport.findByName('Linea Urquiza')
        if (!trenUrquiza) {
            trenUrquiza = new PublicTransport('Linea Urquiza')
        }
        trenUrquiza.addToTwKeyword('#TrenUrquiza')
        trenUrquiza.addToTwKeyword('tren urquiza')
        trenUrquiza.save(failOnError: true);
    }

    def init = { servletContext ->
        createUser()
        createKeyWords()
        createBaseServices()
        new PredictionRating(comment:"Testerino", isPositive: true).save();
    }

    def destroy = {
    }
}
