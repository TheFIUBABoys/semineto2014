package com.seminario

import seminario.domain.PredictionRating
import seminario.domain.PublicTransport

class User {
    String username
    String passwordHash

    User(String username, String passwordHash) {
        this.username = username
        this.passwordHash = passwordHash
    }

    static hasMany = [ roles: Role, permissions: String, following: PublicTransport, predictionRatings: PredictionRating ]

    static constraints = {
        username(nullable: false, blank: false, unique: true)
    }

    //"user" is a reserved table in postgre, we need to change this
    static mapping = {
        table 'users'
    }
}
