package com.example.greening

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class ChallengeActivityDone : AppCompatActivity() {

    // 변수 선언
    // 뒤로가기
    lateinit var imgBack : ImageView

    // 제목
    lateinit var nameChallenge : TextView
    lateinit var detailChallenge : TextView

    // 챌린지 정보
    // 인원
    lateinit var numPeople : TextView
    // 기간
    lateinit var period : TextView
    // 평가
    lateinit var rate : TextView

    // 다음 기간에도 참여하기 버튼
    lateinit var btnReJoinChallenge : Button

    // 후기쓰기 버튼
    lateinit var btnRate : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge_done_5)

        // 아이디 연결
        imgBack = findViewById(R.id.imgBack)
        nameChallenge = findViewById(R.id.nameChallenge)
        detailChallenge = findViewById(R.id.detailChallenge)
        numPeople = findViewById(R.id.numPeople)
        period = findViewById(R.id.period)
        rate = findViewById(R.id.rate)
        btnReJoinChallenge = findViewById(R.id.btnReJoinChallenge)
        btnRate = findViewById(R.id.btnRate)

        btnRate.setOnClickListener {

        }

        btnRate.setOnClickListener {

        }

        imgBack.setOnClickListener{
            var intent = Intent(this, ChallengeActivityJoin::class.java)
            startActivity(intent)
        }
    }
}