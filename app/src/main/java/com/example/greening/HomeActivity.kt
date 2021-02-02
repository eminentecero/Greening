package com.example.greening

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity  : AppCompatActivity() {
    //사용할 변수
    lateinit var levelText:TextView

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
        levelText


    }
}