package com.example.greening

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import org.w3c.dom.Text

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
    lateinit var calendarChallenge : CalendarView

    // 확인 버튼
    lateinit var btnDoneChallenge : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge_join_4)

        // 아이디 연결
        imgBack = findViewById(R.id.imgBack)
        nameChallenge = findViewById(R.id.nameChallenge)
        numPeopleChallenge = findViewById(R.id.numPeopleChallenge)
        periodChallenge = findViewById(R.id.periodChallenge)
        progressChallenge = findViewById(R.id.progressChallenge)
        calendarChallenge = findViewById(R.id.calendarChallenge)
        btnDoneChallenge = findViewById(R.id.btnDoneChallenge)

        btnDoneChallenge.setOnClickListener {
            var intent = Intent(this, ChallengeActivityDone::class.java)
            startActivity(intent)
        }

        imgBack.setOnClickListener {
            var intent = Intent(this, ChallengeActivityNojoin::class.java)
            startActivity(intent)
        }

    }
}