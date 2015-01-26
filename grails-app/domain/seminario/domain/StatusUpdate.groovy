package seminario.domain

import com.seminario.User

class StatusUpdate {

    String sourceType
    String sourceUrl
    String sourceId
    Date dateCreated
    Date sourceDateCreated
    String body
    PublicTransport service
    User user

    StatusUpdate(String sourceType, String body, PublicTransport service) {
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
        sourceDateCreated(nullable:true)
        user(nullable:true)
    }

}
