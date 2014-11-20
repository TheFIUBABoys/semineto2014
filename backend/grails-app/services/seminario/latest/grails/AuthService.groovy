package seminario.latest.grails

import com.seminario.User
import grails.transaction.Transactional
import org.apache.shiro.crypto.hash.Sha256Hash

@Transactional
class AuthService {

    def createUser(String username, String password){
        User newUser = new User(username, new Sha256Hash(password).toHex())
        newUser.addToPermissions("*:*")
        newUser.save()
    }
}
