package com.example.greening

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity  : AppCompatActivity() {
    //사용할 변수
    lateinit var UserName:TextView

    //참여중인 챌린지에서 더보기 버튼
    lateinit var more1Btn : Button
    lateinit var more2Btn: Button
    lateinit var more3Btn : Button

    //추천하는 챌린지에서 참여하기 버튼
    lateinit var join1Btn : Button
    lateinit var join2Btn : Button
    lateinit var join3Btn : Button

    //레벨에 따라 다른 image 표시할 ImageView
    lateinit var level : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_home)

        //아이디로 각각 연결하기
        //사용자 레벨
        level = findViewById(R.id.level)
        UserName = findViewById(R.id.levelText)

        //참여중인 챌린지
        more1Btn = findViewById(R.id.more1)
        more2Btn = findViewById(R.id.more2)
        more3Btn = findViewById(R.id.more3)

        //추천하는 챌린지
        join1Btn = findViewById(R.id.join1)
        join2Btn = findViewById(R.id.join2)
        join3Btn = findViewById(R.id.join3)

        //챌린지 객체 생성!!!!!!!!!!!!!!!!해야함.

        //사용자 이름 띄우는 텍스트 뷰를 인텐트로 받은 사용자 이름을 반환
        var name = intent.getStringExtra("id")
        UserName.setText(name + "님")

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