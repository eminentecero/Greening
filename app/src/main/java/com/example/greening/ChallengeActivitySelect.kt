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
        setContentView(R.layout.activity_challenge_select)

        // 아이디와 변수 연결
        // 제목
        titleChallenge = findViewById(R.id.titleChallenge)
        explnChallenge = findViewById(R.id.explnChallenge)

        // 선택 보더
        selectFood = findViewById(R.id.cardFood)
        selectPlastic = findViewById(R.id.cardPlastic)
        selectExercise = findViewById(R.id.cardExercise)
        selectNatural = findViewById(R.id.cardResource)
        selectEtc = findViewById(R.id.cardOthers)


        // 보더 클릭 이벤트 실행(화면 전환)
        selectFood.setOnClickListener {
            // 인텐트 값으로 화면 전환
            var intent = Intent(this, ChallengeActivityFood::class.java)
            startActivity(intent)
        }

        selectPlastic.setOnClickListener {
            //인텐트 값으로 화면 전환
            var intent = Intent(this, ChallengeActivityPlastic::class.java)
            startActivity(intent)
        }

        selectExercise.setOnClickListener {
            //인텐트 값으로 화면 전환
            var intent = Intent(this, ChallengeActivityExercise::class.java)
            startActivity(intent)
        }

        selectNatural.setOnClickListener {
            //인텐트 값으로 화면 전환
            var intent = Intent(this, ChallengeActivityNatural::class.java)
            startActivity(intent)
        }

        selectEtc.setOnClickListener {
            //인텐트 값으로 화면 전환
            var intent = Intent(this, ChallengeActivityEtc::class.java)
            startActivity(intent)
        }
    }
}