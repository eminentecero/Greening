package com.example.greening

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.cardview.widget.CardView
import org.w3c.dom.Text

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

    lateinit var foodCount : TextView
    lateinit var plasticCount :TextView
    lateinit var exerciseCount : TextView
    lateinit var naturalCount : TextView
    lateinit var etcCount:TextView

    //데이터 베이스 변수
    internal lateinit var db:DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge_select)

        //DB 객체 연결
        db = DBHelper(this)

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

        foodCount = findViewById(R.id.foodCount)
        plasticCount = findViewById(R.id.plasticCount)
        exerciseCount = findViewById(R.id.exerciseCount)
        naturalCount = findViewById(R.id.naturalCount)
        etcCount = findViewById(R.id.etcCount)


        naturalCount.setText("${db.CategoryCount("자원")}개")
        foodCount.setText("${db.CategoryCount("음식")}개")
        exerciseCount.setText("${db.CategoryCount("운동")}개")
        plasticCount.setText("${db.CategoryCount("플라스틱")}개")
        etcCount.setText("${db.CategoryCount("기타")}개")


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