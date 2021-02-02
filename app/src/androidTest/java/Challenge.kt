import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

//챌린지 객체
class Challenge {

    //챌린지 이름
    var name : String = ""
    
    //챌린지 실행 기간 - 며칠 동안 할 건지 표시
    var date : Int = 0

    //참여한 사람들이 남긴 후기 - String 배열로 저장 - 나중에
    var review : Array<String> = arrayOf("")

    //분류하는 - 음식, 플라스틱, 운동, 자원, 기타
    var keyword : String = ""

    //공적 메소드
    companion object{
        //인원 설정하는 메소드
        var person_count : Int = 0

        //최대 인원, 최소 인원 설정
        const val MAXPER : Int = 200
        const val MINPER : Int = 0

        //사람들의 후기 - 후기를 총 더해서 최종 별점 표시할 예정
        var Person_score : Float = 0.0F

        //최대 점수, 최소 점수 설정
        const val MAXSCORE : Float = 5.0F
        const val MINSCORE : Float = 0.0F

        fun currentPersonCount() : Int{
            return person_count
        }
    }

    //챌린지에 참여 - 챌린지에 참여하는 인원 늘이기
    fun accept(){
        person_count ++
    }

    //기본 생성자 - 이름, 기간, 분류하는 키워드 설정해서 생성 - 나중에 하기
    constructor(name: String, date: Int, keyword: String){
        this.name = name
        this.date = date
        this.keyword = keyword
    }

    fun write_review(msg:String){
        //리뷰를 입력하면 리뷰 배열해서 하나씩 출력 - 나중에 하기
        review.set(review.count(), msg)
    }
}
