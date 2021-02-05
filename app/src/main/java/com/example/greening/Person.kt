package com.example.greening

class Person {
    var id : String = ""
    var password : String = ""
    var level : Int = 0
    var challengeCount : Int = 0


    constructor(id:String, password:String)
    {
        this.id = id
        this.password = password
        level
        challengeCount
    }

    constructor(id: String, password: String, level: Int)
    {
        this.id = id
        this.password = password
        this.level = level
        challengeCount
    }

    constructor()
    {
        id
        password
        level
        challengeCount
    }

    fun levelUP()
    {
        level++
    }

}