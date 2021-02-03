package com.example.greening

class Person {
    var id : String = ""
    var password : String = ""
    var challenge :  Array<String> = arrayOf("")
    var level : Int = 0
    var challengeCount : Int = 0

    constructor(){}

    constructor(id:String, password:String)
    {
        this.id = id
        this.password = password
        challenge
        level
        challengeCount
    }
    
    fun levelUP()
    {
        level++
    }
    
    //챌린지 참여
    fun join(challegeID : String)
    {
        challenge += challegeID
        challengeCount ++
    }
}