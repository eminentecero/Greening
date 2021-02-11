package com.example.greening

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class ChallengeActivityNojoin : AppCompatActivity() {

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

    //챌린지 참여하기 버튼
    lateinit var btnJoinChallenge : Button


    //데이터 베이스 변수
    internal lateinit var db:DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge_done_5)

        //DB 객체 연결
        db = DBHelper(this)

        // 아이디 연결
        imgBack = findViewById(R.id.imgBack)
        nameChallenge = findViewById(R.id.nameChallenge)
        detailChallenge = findViewById(R.id.detailChallenge)
        numPeople = findViewById(R.id.numPeople)
        period = findViewById(R.id.period)
        rate = findViewById(R.id.rate)
        btnJoinChallenge = findViewById(R.id.btnJoinChallenge)

        //유저 데이터 들고 오기
        var UserId = intent.getStringExtra("id")

        //사용자 유저 객체 생성 - 로그인 했을 떄 DB에 저장된 해당 회원의 정보를 반환
        var User : Person = Person()
        //Person Table에서 해당 id를 가지고 있는 사람 정보 받아오기
        User = db.DataIn(UserId.toString())

        var ChallengeId = intent.getStringExtra("challengeid")

        var challenge:Challenge = Challenge()
        challenge = db.Challengereturn(ChallengeId!!.toInt())

        nameChallenge.setText(challenge.name)
        numPeople.setText(db.ChallengeJoinCount(challenge))
        period.setText("${challenge.StartDate} - ${challenge.LastDate}")
        rate.setText(challenge.score.toString())


        imgBack.setOnClickListener{
            var intent = Intent(this, ChallengeActivityJoin::class.java)
            startActivity(intent)
        }

        btnJoinChallenge.setOnClickListener {
            //챌린지 인원 높이고, 챌린지 테이블에 저장 + 유저 테이블에 해당 테이블 저장
            challenge.joinUP()
            db.join(challenge, User)
        }
    }
}