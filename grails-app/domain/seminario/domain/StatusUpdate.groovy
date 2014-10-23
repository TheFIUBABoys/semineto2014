package seminario.domain

import com.seminario.User

class StatusUpdate {

    String sourceType
    String sourceUrl
    Date createdAt
    String body
    User user

    static constraints = {
        sourceType(blank: false)
        createdAt(blank: false)
        body(blank: false)
    }

}
