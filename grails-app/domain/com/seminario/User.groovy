package com.seminario

class User {
    String username
    String passwordHash

    User(String username, String passwordHash) {
        this.username = username
        this.passwordHash = passwordHash
    }

    static hasMany = [ roles: Role, permissions: String ]

    static constraints = {
        username(nullable: false, blank: false, unique: true)
    }
}
