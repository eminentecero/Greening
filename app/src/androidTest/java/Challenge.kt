import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.greening.MainActivity


//챌린지 객체
public class Challenge{

    //public static Context mContext

    //챌린지 이름
    var name : String = ""

    //챌린지 실행 기간 - 며칠 동안 할 건지 표시
    var date : Int = 0

    //참여한 사람들이 남긴 후기 - String 배열로 저장 - 나중에
    //var review : Array<String> = arrayOf("")

    //분류하는 - 음식, 플라스틱, 운동, 자원, 기타
    var keyword : String = ""

    //공적 메소드
    companion object{
        //인원 설정하는 메소드
        var count : Int = 0

        //최대 인원, 최소 인원 설정
        const val MAXPER : Int = 200
        const val MINPER : Int = 0

        //사람들의 후기 - 후기를 총 더해서 최종 별점 표시할 예정
        var score : Float = 0.0F

        //최대 점수, 최소 점수 설정
        const val MAXSCORE : Float = 5.0F
        const val MINSCORE : Float = 0.0F

        fun currentPersonCount() : Int{
            return count
        }
    }

    //챌린지에 참여 - 챌린지에 참여하는 인원 늘이기
    fun accept(){
        count ++
    }

    //기본 생성자 - 이름, 기간, 분류하는 키워드 설정해서 생성 - 나중에 하기
    constructor(name: String, keyword: String, date: Int){
        this.name = name
        this.date = date
        this.keyword = keyword
        count = 0
        score = 0.0f
    }

    inner class myDBHelper(context: Context): SQLiteOpenHelper(context, "Greener", null, 1){
        override fun onCreate(db: SQLiteDatabase?) {
            //Name을 primary Key로 설정 - 찾아낼때 쓰이는 key
            db!!.execSQL("CREATE TABLE Challenge (Name CHAR(20) PRIMARY KEY, KeyWord CHAR(20), Date INT(20), Count INT(18), Score INT(18));")
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            //DB 삭제 후 다시 생성
            db!!.execSQL("DROP TABLE IF EXISTS groupTBL")
            onCreate(db)
        }
    }
}
fun challenge_start(myHelper: MainActivity.myDBHelper) {

    //챌린지 객체 생성!!!!!!!!!!!!!!!!해야함.
    var C1 : Challenge = Challenge("텀블러 사용하기", "Plastic",30)
    var C2 : Challenge = Challenge("스테인리스 빨대 사용하기", "Plastic", 20)
    var C3 : Challenge = Challenge("채식하기","Food", 20)


    var sqlDB = myHelper.writableDatabase

    sqlDB.execSQL("INSERT INTO groupTBL VALUES ("+C1.name+","
            +C1.keyword +","+C1.date+",0, NULL);")

}