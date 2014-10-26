package seminario.domain

import com.seminario.User

class StatusUpdate {

    String sourceType
    String sourceUrl
    Date dateCreated
    String body
    Service service
    User user

    StatusUpdate(String sourceType, String body) {
        this.sourceType = sourceType
        this.body = body
    }

    static constraints = {
        sourceType(blank: false)
        body(blank: false)
    }

}
