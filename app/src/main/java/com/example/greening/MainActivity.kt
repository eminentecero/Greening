package com.example.greening

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    //변수 선언
    lateinit var editID:EditText
    lateinit var editPassWord:EditText
    lateinit var btnLogin: Button

    internal lateinit var db:DBHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //연결
        editID = findViewById(R.id.editID)
        editPassWord = findViewById(R.id.editPassWord)
        btnLogin = findViewById(R.id.btnLogin)

        //DB 객체 받아오기
        db = DBHelper(this)
        Toast.makeText(applicationContext, "데베 받아옴.", Toast.LENGTH_LONG).show()

        //1. 회원가입
        //아이디, 비밀번호 입력 - 변수 선언, 연결 필요
        //아이디 - primary key로 지정

        //회원가입 -> 기본정보 쓰는 거,


        //2. 로그인
        //입력한 아이디, 비밀번호 변수랑 TextView(반갑습니다를 띄울)
        btnLogin.setOnClickListener {
            //입력값이 공백이면 기능이 실행되지 않도록 설정
            //ID와 비밀번호란이 공백으로 되어있지 않다면 조건
            if (!(editID.text.toString().equals("") && editPassWord.text.toString().equals(""))) {
                var strPassWord : String
                strPassWord = db.checkPassWord(editID.text.toString())

                if (editPassWord.text.toString().equals(strPassWord)) {
                    //로그인 성공하면 홈 화면으로 넘어가기
                    // 홈 화면에 보낼 정보 - ID(닉네임) intent에 저장
                    var intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("id", editID.text.toString())

                    //화면 넘어가기 전 토스트 메시지 실행하고 나서 -> 다음 화면으로 넘어가기
                    Toast.makeText(applicationContext, "${editID.text}님 반갑습니다!", Toast.LENGTH_LONG).show()
                    startActivity(intent)
                } else {
                    Toast.makeText(applicationContext, "아이디 또는 비밀번호가 잘못되었습니다.", Toast.LENGTH_LONG).show()
                }

            } else {
                //아이디, 비밀번호 변수 중 하나라도 공백일 때
                //메세지를 띄우도록 하는 것이 좋음! - 보안성 주의해서, (아이디 또는 비밀번호가 잘못되었습니다.)
                Toast.makeText(applicationContext, "아이디와 비밀번호를 모두 입력해주세요.", Toast.LENGTH_LONG).show()
            }

            //버튼 실행이 끝나면 빈칸으로 만들기
            editID.setText("")
            editPassWord.setText("")
        }
    }
}

