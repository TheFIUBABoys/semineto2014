import com.seminario.User
import org.apache.shiro.crypto.hash.Sha256Hash
import seminario.domain.Keyword

class BootStrap {

    def init = { servletContext ->
        def user = new User(username: "user123", passwordHash: new Sha256Hash("password").toHex())
        user.addToPermissions("*:*")
        user.save()

        def downKeywords = [
                "no viene": 20,
                "no sale" : 10,
                "parado"  : 4,
                "clavado"  : 10,
                "porqueria"  : 10,
                "porquería"  : 10,
                "detenida"  : 10,
                "parado"  : 10,
                "demorado": 2,
                "servicio interrumpido": 20,
                "cancelado": 10,
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

    def destroy = {
    }
}
