package com.example.greening

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

    //공적 메소드
        //인원 설정하는 메소드
        var count : Int = 0

        //최대 인원, 최소 인원 설정
         val MAXPER : Int = 200
         val MINPER : Int = 0

        //사람들의 후기 - 후기를 총 더해서 challenge_count최종 별점 표시할 예정
        var score : Float = 0.0F

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
    }

    constructor(id: Int, name: String, keyword: String, date: Int, count:Int, Score:Float, bookmark:Int){
        this.id = id
        this.name = name
        this.date = date
        this.keyword = keyword
        this.count = count
        this.score = score
        this.bookmark = bookmark
    }

    constructor(){}
}