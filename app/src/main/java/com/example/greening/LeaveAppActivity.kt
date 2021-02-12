package com.example.greening

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

class LeaveAppActivity : AppCompatActivity() {

    // 변수 선언
    lateinit var pwcheck : EditText
    lateinit var appcontinue : Button
    lateinit var appleave : Button
    lateinit var btnBack : ImageView

    // db 선언
    internal lateinit var db:DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leave_app)

        //db 연결
        db = DBHelper(this)

        // 유저 객체 생성
        var User : Person = Person()
        // 우선 하드 코딩.. 아이디 swim 유저로 지정
        var id ="swim"
        //intent.getStringExtra("id")
        // swim의 유저정보를 받아오기
        User = db.DataIn(id)

        // 아이디 연결
        pwcheck = findViewById(R.id.pwcheck)
        appcontinue = findViewById(R.id.appcontinue)
        appleave = findViewById(R.id.appleave)
        btnBack = findViewById(R.id.btnBack)

        // 탈퇴하기 버튼의 클릭 이벤트
        appleave.setOnClickListener {

            // 만약 비밀번호 확인란이 입력받아온 유저의 비밀번호와 일치하다면
            if(pwcheck.text.toString().equals(db.checkPassWord(id))){

                // 1) 회원 삭제 함수를 실행한다.
                db.deleteUser(User.id)

                // 2) 탈퇴가 완료되었다는 토스트 메시지를 띄운다.
                Toast.makeText(applicationContext, "회원 탈퇴가 완료되었습니다.", Toast.LENGTH_SHORT).show()

                // 3) intent값으로 비회원 초기 화면인 loginActivity로 화면을 이동한다.
                var intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }

        // 탈퇴하기 취소 버튼의 클릭 이벤트
        appcontinue.setOnClickListener {
            // 설정 화면으로 되돌아간다.
            var intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }

        // 뒤로가기 버튼의 클릭 이벤트
        btnBack.setOnClickListener {
            // 설정 화면으로 되돌아간다.
            var intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }

    }
}