package seminario.latest.grails

import com.seminario.User
import grails.transaction.Transactional
import org.apache.shiro.SecurityUtils
import seminario.domain.Service

@Transactional
class UserService {
    /**
     * for currently logged in user
     */
    def getLocalUserId(){
        def userName  = (String)SecurityUtils.subject?.principal
        User.findByUsername(userName)
    }

    def getSubscriptions(){
        def user = this.getLocalUser()
        Service.withCriteria {
            subscriptor {
                eq('id', user.id)
            }
        }
    }

    User getLocalUser(){
        getLocalUserId()
    }
}