package com.example.greening

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.greening.MainActivity
import com.example.greening.R

class SignupActivity : AppCompatActivity() {

    // 변수 선언
    lateinit var idEdit: EditText
    lateinit var button_idCheck: Button
    lateinit var passwordEdit: EditText
    lateinit var passwordOkEdit: EditText
    lateinit var button_join_cancel: Button
    lateinit var button_join_ok: Button

    internal lateinit var db:DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        // 아이디와 변수 연결
        idEdit = findViewById(R.id.idEdit)
        button_idCheck = findViewById(R.id.button_idCheck)
        passwordEdit = findViewById(R.id.passwordEdit)
        passwordOkEdit = findViewById(R.id.passwordOkEdit)
        button_join_cancel = findViewById(R.id.button_join_cancel)
        button_join_ok = findViewById(R.id.button_join_ok)

        db = DBHelper(this)

        // 회원가입 버튼 실행
        button_join_ok.setOnClickListener {

            // 변수에 입력값 담음
            //비밀번호 동일한지 확인하기
            val passwordOk= passwordOkEdit.text.toString()
            val password= passwordEdit.text.toString()

            if (idEdit.text.toString().equals("") || passwordEdit.text.toString().equals("") || passwordOkEdit.text.toString().equals("")) {
                //"아이디와 비밀번호를 모두 입력해주세요."라는 토스트 메시지를 띄우기
                Toast.makeText(applicationContext, "아이디와 비밀번호를 모두 입력해주세요.", Toast.LENGTH_LONG)
                        .show()
            } else
            // 비밀번호 입력과 비밀번호 확인이 일치하면
            //수영 : String끼리는 equals로 동일한 지 확인해야해요
                if (password.equals(passwordOk)) {
                    var person1 = Person(idEdit.text.toString(), passwordEdit.text.toString())
                    db.addPerson(person1)

                    // 2) 회원가입이 완료되었다는 토스트 띄우기
                    Toast.makeText(
                            applicationContext,
                            "회원가입이 완료되었습니다. 로그인을 해주세요!",
                            Toast.LENGTH_LONG
                    ).show()

                    //3) 로그인 화면으로 이동하기
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(applicationContext, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT)
                            .show()
                }

        }

        //아이디 중복 확인하기
        button_idCheck.setOnClickListener {

            //공백 확인
            if(!(idEdit.text.toString().equals(""))) {
                var strId : String
                strId = db.checkID(idEdit.text.toString())

                if (!(idEdit.text.toString().equals(strId))) {
                    Toast.makeText(applicationContext, "입력하신 id는 사용이 가능합니다.", Toast.LENGTH_LONG)
                            .show()
                } else {
                    Toast.makeText(applicationContext, "다른 id를 입력해주세요.", Toast.LENGTH_LONG).show()
                    idEdit.setText("")
                }
            }

        }

        button_join_cancel.setOnClickListener {
            // 인텐트 값으로 화면 전환
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
