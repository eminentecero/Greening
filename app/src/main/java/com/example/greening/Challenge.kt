package com.example.greening

import java.text.SimpleDateFormat
import java.util.*

//사용자에게 보여지는 챌린지 내용을 담은 클래스
class Challenge {
    //public static Context mContext

    var id : Int = 0

    //챌린지 이름
    var name : String = ""

    //챌린지 실행 기간 - 며칠 동안 할 건지 표시
    var date : Int = 0

    var bookmark:Int = 0

    //분류하는 - 음식, 플라스틱, 운동, 자원, 기타
    var keyword : String = ""

    var StartDate :String = ""
    var LastDate :String = ""
    var SummaryLong : String = ""
    var Short1 : String = ""
    var Short2 : String = ""
    var Short3 : String = ""

    //사람들의 후기 - 후기를 총 더해서 challenge_count최종 별점 표시할 예정
    var score : Float = 0.0F

    var State: Int = -1

    //공적 메소드
        //인원 설정하는 메소드
        var count : Int = 0

        //최대 인원, 최소 인원 설정
         val MAXPER : Int = 200
         val MINPER : Int = 0


        //최대 점수, 최소 점수 설정
         val MAXSCORE : Float = 5.0F
         val MINSCORE : Float = 0.0F

        fun joinUP(){
            this.count++
        }

        fun joinDown(){
            this.count--
        }

        fun currentPersonCount() : Int{
            return count
        }

    //기본 생성자
    //기본 생성자
    constructor(id: Int, name: String, keyword: String, date: Int){
        this.id = id
        this.name = name
        this.date = date
        this.keyword = keyword
        count = 0
        score = 0.0f
        bookmark
        StartDate
        LastDate
        SummaryLong
        Short1
        Short2
        Short3
        State
    }

    constructor(id: Int, name: String, keyword: String, date: Int, count:Int, Score:Float, bookmark:Int,
    startDate:String, lastdate : String, summaryLong:String, Short1: String, Short2:String, Short3:String){
        this.id = id
        this.name = name
        this.date = date
        this.keyword = keyword
        this.count = count
        this.score = score
        this.bookmark = bookmark
        this.StartDate = startDate
        this.LastDate = lastdate
        this.SummaryLong = summaryLong
        this.Short1 = Short1
        if(Short2 ==null){
            this.Short2 = ""
        }else{
            this.Short2 = Short2
        }
        if(Short3 ==null){
            this.Short3 = ""
        }else{
            this.Short3 = Short2
        }
    }
    constructor(id: Int, name: String, keyword: String, date: Int, count:Int, Score:Float, bookmark:Int,
    startDate:String, lastdate : String, summaryLong:String, Short1: String, Short2:String){
        this.id = id
        this.name = name
        this.date = date
        this.keyword = keyword
        this.count = count
        this.score = score
        this.bookmark = bookmark
        this.StartDate = startDate
        this.LastDate = lastdate
        this.SummaryLong = summaryLong
        this.Short1 = Short1
        if(Short2 ==null){
            this.Short2 = ""
        }else{
            this.Short2 = Short2
        }
        State
    }
    constructor(id: Int, name: String, keyword: String, date: Int, count:Int, Score:Float, bookmark:Int,
    startDate:String, lastdate : String, summaryLong:String, Short1: String){
        this.id = id
        this.name = name
        this.date = date
        this.keyword = keyword
        this.count = count
        this.score = score
        this.bookmark = bookmark
        this.StartDate = startDate
        this.LastDate = lastdate
        this.SummaryLong = summaryLong
        this.Short1 = Short1
        State
    }

    constructor(){}

    fun ShortSummary():String{
        var ShortSummary: String = ""

        if(!this.Short1.equals("")&&!this.Short2.equals("")&&!this.Short3.equals(""))
        {
            ShortSummary = "#${Short1} #${Short2} #${Short3}"
        }else if(!this.Short1.equals("")&&!this.Short2.equals("")&&this.Short3.equals(""))
        {
            ShortSummary = "#${Short1} #${Short2}"
        }else{
            ShortSummary = "#${Short1}"
        }
        return ShortSummary
    }

    fun D_Day(LastDate:String) : Long
    {
        var sf = SimpleDateFormat("yyyy년 MM월 dd일")
        var date = sf.parse(LastDate)
        var today = Calendar.getInstance()
        var calcuDate = (today.time.time - date.time) / (60 * 60 * 24 * 1000)
        return calcuDate
    }
}