package com.example.greening

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    //변수 선언
    lateinit var editID:EditText
    lateinit var editPassWord:EditText
    lateinit var btnLogin: Button

    lateinit var myHelper:myDBHelper
    lateinit var sqlDB : SQLiteDatabase



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        //연결
        editID = findViewById(R.id.editID)
        editPassWord = findViewById(R.id.editPassWord)
        btnLogin = findViewById(R.id.btnLogin)


        //1. 회원가입
        //아이디, 비밀번호 입력 - 변수 선언, 연결 필요
        //아이디 - primary key로 지정

        //회원가입 -> 기본정보 쓰는 거,

        //객체 받아오기 - DB
        myHelper = myDBHelper(this)


        //2. 로그인
        //입력한 아이디, 비밀번호 변수랑 TextView(반갑습니다를 띄울)
        btnLogin.setOnClickListener {
            //입력값이 공백이면 기능이 실행되지 않도록 설정

            //ID와 비밀번호란이 공백으로 되어있지 않다면 조건
            if (!(editID.text.toString().equals("") && editPassWord.text.toString().equals(""))) {
                //get, set 생략 가능 - 아이디와 비밀번호를 조회해서 비교하기 때문에 readable 사용
                sqlDB = myHelper.readableDatabase

                //SQL 조회
                var cursor: Cursor
                cursor = sqlDB.rawQuery("SELECT * FROM groupTBL WHERE ID = '" + editID.text + "';", null)

                //저장할 배열 설정
                var strPassWord = ""
                
                while (cursor.moveToNext()) {
                    //1번째 행에 있는 것은 번호
                    //비밀번호 조회 - 조회하는 변수에 조회된 비밀번호 넣음
                    strPassWord += cursor.getString(1)
                }

                //DB 닫음
                sqlDB.close()

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

    //DB 생성되도록 하기!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! - 완료
    inner class myDBHelper(context: Context): SQLiteOpenHelper(context, "Greener", null, 1){
        override fun onCreate(db: SQLiteDatabase?) {
            //Name을 primary Key로 설정 - 찾아낼때 쓰이는 key
            db!!.execSQL("CREATE TABLE groupTBL (ID CHAR(20) PRIMARY KEY, PassWord CHAR(20), Challenge CHAR(20), Level INT(3));")

            //챌린지 데이터 베이스 생성
            db!!.execSQL("CREATE TABLE Challenge (Name CHAR(20) PRIMARY KEY, KeyWord CHAR(20), Date INT(20), Count INT(18), Score INT(18));")
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            //DB 삭제 후 다시 생성
            db!!.execSQL("DROP TABLE IF EXISTS groupTBL")
            onCreate(db)
        }
    }
}

