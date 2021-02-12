package com.example.greening

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

class PasswordChangeActivity : AppCompatActivity() {

    // 변수 선언
    lateinit var pwchange : EditText
    lateinit var pwchangecheck : EditText
    lateinit var btnComplete : Button
    lateinit var btnCancel : Button
    lateinit var btnBack : ImageView

    internal lateinit var db:DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_change)

        //db 연결
        db = DBHelper(this)

        // 아이디 연결
        pwchange = findViewById(R.id.pwchange)
        pwchangecheck = findViewById(R.id.pwchangecheck)
        btnComplete = findViewById(R.id.btnComplete)
        btnCancel = findViewById(R.id.btnCancel)
        btnBack = findViewById(R.id.btnBack)

        // 유저 객체 생성
        var User : Person = Person()
        // 우선 하드 코딩.. 아이디 swim 유저로 지정
        var id ="swim"
        //intent.getStringExtra("id")
        // swim의 유저정보를 받아오기
        User = db.DataIn(id)

        // 비밀번호 변경 완료 버튼의 클릭 이벤트
        btnComplete.setOnClickListener {
            // 비밀번호 입력과 비밀번호 확인의 입력값이 같다면,
            if(pwchange.text.toString().equals(pwchangecheck.text.toString())){

                // 1) 비밀번호 변경 함수를 실행한다.
                db.changePassword(User.id, pwchange.text.toString())

                // 2) 비밀번호 변경이 완료되었다는 토스트 메시지를 띄운다.(변경된 비밀번호도 함께 띄운다.)
                Toast.makeText(applicationContext, "비밀번호 변경이 완료되었습니다 : "+"${pwchange.text.toString()}", Toast.LENGTH_SHORT).show()

                // 3) 변경이 완료되었기 때문에 이전 화면(설정 화면)으로 되돌아가기 위한 intent값을 설정한다.
                var intent = Intent(this, SettingActivity::class.java)

                // 4) intent를 실행한다.
                startActivity(intent)

            } else{
                // 비밀번호 입력과 비밀번호 확인의 입력값이 같지 않다면,

                // 1) 입력창을 초기화한다.
                pwchange.setText("")
                pwchangecheck.setText("")

                // 2)토스트 메시지를 띄운다.
                Toast.makeText(applicationContext, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        // 비밀번호 변경 취소 버튼의 클릭 이벤트
        btnCancel.setOnClickListener {
            var intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }

        // 뒤로가기 버튼의 클릭 이벤트
        btnBack.setOnClickListener {
            var intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }
    }
}