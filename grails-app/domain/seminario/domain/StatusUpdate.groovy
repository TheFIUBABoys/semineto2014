package seminario.domain

import com.seminario.User

class StatusUpdate {

    String sourceType
    String sourceUrl
    String sourceId
    Date dateCreated
    String body
    Service service
    User user

    StatusUpdate(String sourceType, String body, Service service) {
        this.sourceType = sourceType
        this.body = body
        this.service = service
        this.dateCreated = new Date()
    }

    static constraints = {
        sourceType(blank: false)
        body(blank: false)
        sourceId(nullable:true)
        sourceUrl(nullable:true)
        user(nullable:true)
    }

}
