package com.example.greening

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ChallengeActivityFood : AppCompatActivity() {

    // 변수 선언
    // 뒤로가기
    lateinit var imgBack : ImageView

    // 제목
    lateinit var foodChallenge : TextView
    lateinit var explnFood : TextView

    // 챌린지 목록
    lateinit var challenge1 : CardView
    lateinit var challenge2 : CardView
    lateinit var challenge3 : CardView
    lateinit var challenge4 : CardView
    lateinit var challenge5 : CardView
    lateinit var challenge6 : CardView
    lateinit var challenge7 : CardView
    lateinit var challenge8 : CardView

    // 챌린지 추가
    lateinit var addChallenge : FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge_food_2)

        // 아이디 연결
        imgBack = findViewById(R.id.imgBack)
        foodChallenge = findViewById(R.id.foodChallenge)
        explnFood = findViewById(R.id.explnFood)

        // 챌린지 아이디 연결
        challenge1 = findViewById(R.id.challenge1)
        challenge2 = findViewById(R.id.challenge2)
        challenge3 = findViewById(R.id.challenge3)
        challenge4 = findViewById(R.id.challenge4)
        challenge5 = findViewById(R.id.challenge5)
        challenge6 = findViewById(R.id.challenge6)
        challenge7 = findViewById(R.id.challenge7)
        challenge8 = findViewById(R.id.challenge8)

        // 플러팅 버튼
        addChallenge = findViewById(R.id.addChallenge)

        imgBack.setOnClickListener {
            var intent = Intent(this, ChallengeActivitySelect::class.java)
            startActivity(intent)
        }

        challenge1.setOnClickListener {
            var intent = Intent(this, ChallengeActivityNojoin::class.java)
            startActivity(intent)
        }
    }
}