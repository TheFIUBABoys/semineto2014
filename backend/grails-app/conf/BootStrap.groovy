import com.seminario.User
import grails.transaction.Transactional
import org.apache.shiro.crypto.hash.Sha256Hash
import seminario.domain.Keyword
import seminario.domain.Service

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
                "porquería"  : 10,
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
                "en horario": 5,
                "bien": 3,
                "perfecto": 5,
                "rapido": 3,
                "rápido": 3,
                "servicio normal": 10,
                "te amo": 10,
                "bienvenido al grp comunidad": -100,
                "saliendo"  : 2,
                "viajando"  : 4,
                "no me puedo quejar"  : 10,
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
        def trenTigre = Service.findByName('Linea Mitre Ramal Tigre')
        if (!trenTigre) {
            trenTigre = new Service('Linea Mitre Ramal Tigre')
        }
        trenTigre.addToTwKeyword('#TrenTigre')
        trenTigre.addToTwKeyword('tren tigre')
        trenTigre.save(failOnError: true);

        def trenSuarez = Service.findByName('Linea Mitre Ramal Suarez')
        if (!trenSuarez) {
            trenSuarez = new Service('Linea Mitre Ramal Suarez')
        }
        trenSuarez.addToTwKeyword('#TrenSuarez')
        trenSuarez.addToTwKeyword('tren suarez')
        trenSuarez.save(failOnError: true);

        def trenMitre = Service.findByName('Linea Mitre Ramal Mitre')
        if (!trenMitre) {
            trenMitre = new Service('Linea Mitre Ramal Mitre')
        }
        trenMitre.addToTwKeyword('#TrenMitre')
        trenMitre.addToTwKeyword('tren mitre')
        trenMitre.save(failOnError: true);

        def trenBelgranoNorte = Service.findByName('Linea Belgrano Norte')
        if (!trenBelgranoNorte) {
            trenBelgranoNorte = new Service('Linea Belgrano Norte')
        }
        trenBelgranoNorte.addToTwKeyword('#TrenBelgrano')
        trenBelgranoNorte.addToTwKeyword('tren belgrano')
        trenBelgranoNorte.save(failOnError: true);

        def trenSanMartin = Service.findByName('Linea San Martin')
        if (!trenSanMartin) {
            trenSanMartin = new Service('Linea San Martin')
        }
        trenSanMartin.addToTwKeyword('#TrenSanMartion')
        trenSanMartin.addToTwKeyword('tren san martin')
        trenSanMartin.save(failOnError: true);

        def trenUrquiza = Service.findByName('Linea Urquiza')
        if (!trenUrquiza) {
            trenUrquiza = new Service('Linea Urquiza')
        }
        trenUrquiza.addToTwKeyword('#TrenUrquiza')
        trenUrquiza.addToTwKeyword('tren urquiza')
        trenUrquiza.save(failOnError: true);
    }

    def init = { servletContext ->
        createUser()
        createKeyWords()
        createBaseServices()
    }

    def destroy = {
    }
}
