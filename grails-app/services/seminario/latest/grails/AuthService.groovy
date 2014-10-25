package seminario.latest.grails

import com.seminario.User
import grails.transaction.Transactional
import org.apache.shiro.crypto.hash.Sha256Hash

@Transactional
class AuthService {

    def createUser(params){
        User newUser = new User(params.username, new Sha256Hash(params.password).toHex())
        newUser.addToPermissions("*:*")
        newUser.save()
    }
}
