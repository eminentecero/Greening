package com.example.greening

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text

class HomeActivity  : AppCompatActivity() {
    //사용할 변수
    lateinit var UserName:TextView

    //참여중인 챌린지에서 더보기 버튼
    lateinit var ingChallenge1: FrameLayout
    lateinit var ingChallenge2: FrameLayout
    lateinit var ingChallenge3: FrameLayout

    lateinit var ing1_TextView : TextView
    lateinit var ing1_Button : Button
    lateinit var ing1Count_TextView : TextView
    lateinit var ing1Date_TextView : TextView
    lateinit var ingmore1_Button : Button

    lateinit var ing2_TextView : TextView
    lateinit var ing2_Button : Button
    lateinit var ing2Count_TextView : TextView
    lateinit var ing2Date_TextView : TextView
    lateinit var ingmore2_Button : Button

    lateinit var ing3_TextView : TextView
    lateinit var ing3_Button : Button
    lateinit var ing3Count_TextView : TextView
    lateinit var ing3Date_TextView : TextView
    lateinit var ingmore3_Button : Button

    //추천하는 챌린지에서 참여하기 버튼
    lateinit var join1Btn : Button
    lateinit var join2Btn : Button
    lateinit var join3Btn : Button
    lateinit var more1Btn : Button
    lateinit var more2Btn : Button
    lateinit var more3Btn : Button


    //레벨에 따라 다른 image 표시할 ImageView
    lateinit var level : ImageView

    //데이터 베이스 변수
    internal lateinit var db:DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_home)

        //DB 객체 연결
        db = DBHelper(this)

        //아이디로 각각 연결하기
        //사용자 레벨
        level = findViewById(R.id.level)
        UserName = findViewById(R.id.levelText)

        //참여중인 챌린지
        ingChallenge1 = findViewById(R.id.ingChallenge1)
        ingChallenge2 = findViewById(R.id.ingChallenge2)
        ingChallenge3 = findViewById(R.id.ingChallenge3)

        ing1_TextView = findViewById(R.id.ing1_TextView)
        ing1_Button = findViewById(R.id.ing1_Button)
        ing1Count_TextView = findViewById(R.id.ing1Count_TextView)
        ing1Date_TextView = findViewById(R.id.ing1Date_TextView)
        ingmore1_Button = findViewById(R.id.ingmore1_Button)

        ing2_TextView = findViewById(R.id.ing2_TextView)
        ing2_Button = findViewById(R.id.ing2_Button)
        ing2Count_TextView = findViewById(R.id.ing2Count_TextView)
        ing2Date_TextView = findViewById(R.id.ing2Date_TextView)
        ingmore2_Button = findViewById(R.id.ingmore2_Button)

        ing3_TextView = findViewById(R.id.ing3_TextView)
        ing3_Button = findViewById(R.id.ing3_Button)
        ing3Count_TextView = findViewById(R.id.ing3Count_TextView)
        ing3Date_TextView = findViewById(R.id.ing3Date_TextView)
        ingmore3_Button = findViewById(R.id.ingmore3_Button)

        //추천하는 챌린지
        join1Btn = findViewById(R.id.join1)
        join2Btn = findViewById(R.id.join2)
        join3Btn = findViewById(R.id.join3)

        more1Btn = findViewById(R.id.ingmore1_Button)
        more2Btn = findViewById(R.id.ingmore2_Button)
        more3Btn = findViewById(R.id.ingmore3_Button)


        //임시로 챌린지 생성 - 원래는 챌린지 목록에 있어야하는 부분
        var C1 : Challenge = Challenge("텀블러 사용하기", "Plastic",30)
        var C2 : Challenge = Challenge("스테인리스 빨대 사용하기", "Plastic", 20)
        var C3 : Challenge = Challenge("채식하기","Food", 20)

        //데이터 베이스에 챌린지 저장
        //db.addChallenge(C1)
        //db.addChallenge(C2)
        //db.addChallenge(C3)

        //사용자 이름 띄우는 텍스트 뷰를 인텐트로 받은 사용자 이름을 반환
        var name = intent.getStringExtra("id")
        UserName.setText(name + "님")


        //참여중인 챌린지
        //사용자가 참여하고 있는 챌린지 정보를 받아와서 그 갯수 만큼 프레임 레이아웃 생성
        //기본은 setVisibility(View.GONE)으로 설정해두기
        //사용자가 참여하고 있는 챌린지 배열 갯수 불러오기
        //var count = db.ChallengeCount(name.toString())

        var challenge_count = 3

        //count 갯수에 따라 프레임 레이아웃 보이도록 설정
        if(challenge_count>2)
        {
            //챌린지 갯수가 3개일 때
            ingChallenge1.setVisibility(View.VISIBLE)
            ingChallenge2.setVisibility(View.VISIBLE)
            ingChallenge3.setVisibility(View.VISIBLE)

            ing1_TextView.setText(C1.name)
            ing1_Button.setText(C1.keyword)
            ing1Count_TextView.setText("${C1.count}명 참여중")
            ing1Date_TextView.setText("${C1.date}일 남음")

            ing2_TextView.setText(C2.name)
            ing2_Button.setText(C2.keyword)
            ing2Count_TextView.setText("${C2.count}명 참여중")
            ing2Date_TextView.setText("${C2.date}일 남음")

            ing3_TextView.setText(C3.name)
            ing3_Button.setText(C3.keyword)
            ing3Count_TextView.setText("${C3.count}명 참여중")
            ing3Date_TextView.setText("${C3.date}일 남음")

        }else if(challenge_count>1)
        {
            //챌린지 갯수가 2개일 때
            ingChallenge1.setVisibility(View.VISIBLE)
        }else{
            //챌린지 갯수가 1개일 때
            ingChallenge1.setVisibility(View.VISIBLE)
        }

        //더보기 버튼을 클릭하면 해당 챌린지에 대한 설명이 나와 있는 페이지로 이동
        more1Btn.setOnClickListener  {
            var intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("id", name.toString())
        }

        more2Btn.setOnClickListener  {
            var intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("id", name.toString())
        }

        more3Btn.setOnClickListener  {
            var intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("id", name.toString())
        }

        //신청하기 버튼을 누르면->사용자 DB에 진행하는 챌린지의 ID 입력
        join1Btn.setOnClickListener {

        }
    }
}


