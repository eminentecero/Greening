package com.example.greening

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.cardview.widget.CardView

class ChallengeActivitySelect : AppCompatActivity() {

    // 사용할 변수 선언
    // 제목
    lateinit var titleChallenge : TextView
    lateinit var explnChallenge : TextView

    // 선택 보더
    lateinit var selectFood : CardView
    lateinit var selectPlastic : CardView
    lateinit var selectExercise : CardView
    lateinit var selectNatural : CardView
    lateinit var selectEtc : CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge_1)

        // 아이디와 변수 연결
        // 제목
        titleChallenge = findViewById(R.id.titleChallenge)
        explnChallenge = findViewById(R.id.explnChallenge)

        // 선택 보더
        selectFood = findViewById(R.id.selectFood)
        selectPlastic = findViewById(R.id.selectPlastic)
        selectExercise = findViewById(R.id.selectExercise)
        selectNatural = findViewById(R.id.selectNatural)
        selectEtc = findViewById(R.id.selectEtc)


        // 보더 클릭 이벤트 실행(화면 전환)
        selectFood.setOnClickListener {
            // 인텐트 값으로 화면 전환
            var intent = Intent(this, ChallengeActivityFood::class.java)
            startActivity(intent)
        }

        selectPlastic.setOnClickListener {
            // 인텐트 값으로 화면 전환
            //var intent = Intent(this, SignupActivity::class.java)
            //tartActivity(intent)
        }

        selectExercise.setOnClickListener {
            // 인텐트 값으로 화면 전환
            //var intent = Intent(this, SignupActivity::class.java)
            //tartActivity(intent)
        }

        selectNatural.setOnClickListener {
            // 인텐트 값으로 화면 전환
            //var intent = Intent(this, SignupActivity::class.java)
            //tartActivity(intent)
        }

        selectEtc.setOnClickListener {
            // 인텐트 값으로 화면 전환
            //var intent = Intent(this, SignupActivity::class.java)
            //tartActivity(intent)
        }
    }
}