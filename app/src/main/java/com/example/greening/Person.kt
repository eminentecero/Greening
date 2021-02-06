package com.example.greening

class Person {
    var nickname : String = ""
    var id : String = ""
    var password : String = ""
    var level : Int = 0
    var challengeCount : Int = 0


    constructor(nickname:String,id:String, password:String)
    {
        this.nickname = nickname
        this.id = id
        this.password = password
        level
        challengeCount
    }

    constructor(nickname:String, id: String, password: String, level: Int)
    {
        this.nickname = nickname
        this.id = id
        this.password = password
        this.level = level
        challengeCount
    }

    constructor()
    {
        nickname
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