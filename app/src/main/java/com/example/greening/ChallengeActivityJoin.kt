package com.example.greening

import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.applikeysolutions.cosmocalendar.selection.MultipleSelectionManager
import com.applikeysolutions.cosmocalendar.selection.OnDaySelectedListener
import com.applikeysolutions.cosmocalendar.utils.SelectionType
import java.util.*


class ChallengeActivityJoin : AppCompatActivity() {

    // 변수 선언
    lateinit var imgBack : ImageView

    // 챌린지 정보
    lateinit var nameChallenge : TextView
    lateinit var numPeopleChallenge : TextView
    lateinit var periodChallenge : TextView

    // 프로그레스 바
    lateinit var progressChallenge : ProgressBar

    // 달력
    lateinit var calendar_view : com.applikeysolutions.cosmocalendar.view.CalendarView

    // 확인 버튼
    lateinit var btnDoneChallenge : Button

    //데이터 베이스 변수
    internal lateinit var db:DBHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge_join_4)

        //DB 객체 연결
        db = DBHelper(this)

        // 아이디 연결
        imgBack = findViewById(R.id.imgBack)
        nameChallenge = findViewById(R.id.nameChallenge)
        numPeopleChallenge = findViewById(R.id.numPeopleChallenge)
        periodChallenge = findViewById(R.id.periodChallenge)
        progressChallenge = findViewById(R.id.progressChallenge)
        calendar_view = findViewById(R.id.calendarChallenge)
        btnDoneChallenge = findViewById(R.id.btnDoneChallenge)
        var UserId = intent.getStringExtra("id")

        //사용자 유저 객체 생성 - 로그인 했을 떄 DB에 저장된 해당 회원의 정보를 반환
        var User : Person = Person()

        //Person Table에서 해당 id를 가지고 있는 사람 정보 받아오기
        User = db.DataIn(UserId.toString())

        var Id = intent.getStringExtra("ChallengeId")
        var Name = intent.getStringExtra("ChallengeName")
        var KeyWord = intent.getStringExtra("ChallengeKeyWord")
        var Date = intent.getStringExtra("ChallengeDate")

        var challenge : Challenge = Challenge(
            Id.toString().toInt(),
            Name.toString(),
            KeyWord.toString(),
            Date.toString().toInt()
        )

        nameChallenge.setText(challenge.name)
        numPeopleChallenge.setText(db.ChallengeJoinCount(challenge).toString())
        periodChallenge.setText(challenge.date.toString())

        Log.d("태그", "화면 구성")
        calendar_view.setSelectionType(SelectionType.MULTIPLE)
        Log.d("태그", "날짜 선택")
        //array = db.ChallengeCompelete(challenge)
        calendar_view.selectionManager = MultipleSelectionManager(OnDaySelectedListener {
            Log.d("태그", "========== setSelectionManager ==========")
            Log.d("태그", "Selected Dates : " + calendar_view.selectedDates.size)
            if (calendar_view.selectedDates.size <= 0) return@OnDaySelectedListener
            Log.d("태그", "Selected Days : " + calendar_view.selectedDays)
        })

        //array = array

        btnDoneChallenge.setOnClickListener {

            //db.ChallengeRecord(challenge, calendar_view.selectedDays)
        }

        imgBack.setOnClickListener {
            //var intent = Intent(this, ChallengeActivityNojoin::class.java)
            //startActivity(intent)
            finish()
        }

    }
}