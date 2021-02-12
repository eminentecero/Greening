package com.example.greening

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

class ProfileChangeActivity : AppCompatActivity() {

    // 변수 선언
    lateinit var nicknameChange : EditText
    lateinit var btnComplete2 : Button
    lateinit var btnCancel2 : Button
    lateinit var btnBack2 : ImageView

    // db 선언
    internal lateinit var db:DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_change)

        //db 연결
        db = DBHelper(this)

        // 아이디 연결
        nicknameChange = findViewById(R.id.nicknameChange)
        btnComplete2 = findViewById(R.id.btnComplete2)
        btnCancel2 = findViewById(R.id.btnCancel2)
        btnBack2 = findViewById(R.id.btnBack2)

        // 유저 객체 생성
        var User : Person = Person()
        // 우선 하드 코딩.. 아이디 swim 유저로 지정
        var id ="swim"
        //intent.getStringExtra("id")
        // swim의 유저정보를 받아오기
        User = db.DataIn(id)

        // 닉네임 변경 칸의 Hint 값을 현재 닉네임으로 지정한다.
        nicknameChange.setHint(User.nickname)

        // 닉네임 변경 완료 버튼의 클릭 이벤트
        btnComplete2.setOnClickListener {
            // 1) 닉네임 변경 함수를 실행한다.
            db.changeNickname(User.id, nicknameChange.text.toString())

            // 2) 닉네임 변경이 완료되었다는 토스트 메시지를 띄운다.(변경된 닉네임도 함께)
            Toast.makeText(applicationContext, "닉네임 변경이 완료되었습니다 : "+"${nicknameChange.text.toString()}", Toast.LENGTH_SHORT).show()

            // 3) intent값으로 설정 화면으로 이동한다.
            var intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }

        // 닉네임 변경 취소 버튼의 클릭 이벤트
        btnCancel2.setOnClickListener {
            // 설정 화면으로 이동한다.
            var intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }

        // 뒤로가기 버튼의 클릭 이벤트
        btnBack2.setOnClickListener {
            // 설정 화면으로 이동한다.
            var intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }


    }
}