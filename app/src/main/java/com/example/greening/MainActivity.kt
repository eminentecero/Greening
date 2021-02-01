package com.example.greening

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var editID:EditText
    lateinit var editPassWord:EditText
    lateinit var btnLogin: Button
    lateinit var ResulttextView:TextView

    lateinit var myHelper:myDBHelper
    lateinit var sqlDB : SQLiteDatabase
    lateinit var result : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editID = findViewById(R.id.editID)
        editPassWord = findViewById(R.id.editPassWord)
        btnLogin = findViewById(R.id.btnLogin)
        ResulttextView = findViewById(R.id.ResulttextView)


        //1. 회원가입
        //아이디, 비밀번호 입력 - 변수 선언, 연결 필요
        //아이디 - primary key로 지정

        //회원가입 -> 기본정보 쓰는 거,

        //객체 받아오기 - DB
        myHelper = myDBHelper(this)



        //2. 로그인
        //입력한 아이디, 비밀번호 변수랑 TextView(반갑습니다를 띄울)
        btnLogin.setOnClickListener {
            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!공백값이 무엇인지 미확인 - 확인해서 if 문에 걸리도록 해야함.
            //입력값이 공백이면 기능이 실행되지 않도록 설정
            if(editID.toString().equals("") && editPassWord.toString().equals("")){
                //get, set 생략 가능 - 아이디와 비밀번호를 조회해서 비교하기 때문에 readable 사용
                sqlDB = myHelper.readableDatabase

                var cursor: Cursor
                cursor = sqlDB.rawQuery("SELECT * FROM groupTBL WHERE ID = '"+editID+ "';", null)

                //저장할 배열 설정
                var strPassWord = "비밀번호"+ "\r\n" + "---------" + "\r\n"

                while(cursor.moveToNext()) {
                    //1번째 행에 있는 것은 번호
                    //비밀번호 조회
                    strPassWord += cursor.getString(1) + "\r\n"
                }

                sqlDB.close()

                if(strPassWord == editPassWord.toString()){
                    result = "${editID} + 님 반갑습니다!"
                    ResulttextView.setText(result)
                }
                else{
                    ResulttextView.setText("잘못된 비밀번호 입니다.")
                }
            }
            else{
                //메세지를 띄우도록 하는 것이 좋음! - 보안성 주의해서, (아이디 또는 비밀번호가 잘못되었습니다.)
                Toast.makeText(applicationContext, "아이디와 비밀번호를 모두 입력해주세요.", Toast.LENGTH_LONG).show()
            }
        }
    }

    //DB 생성되도록 하기!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    inner class myDBHelper(context: Context): SQLiteOpenHelper(context, "Greener", null, 1){
        override fun onCreate(db: SQLiteDatabase?) {
            //Name을 primary Key로 설정 - 찾아낼때 쓰이는 key
            db!!.execSQL("CREATE TABLE groupTBL (ID CHAR(20) PRIMARY KEY, PassWord Integer);")
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            //DB 삭제 후 다시 생성
            db!!.execSQL("DROP TABLE IF EXISTS groupTBL")
            onCreate(db)
        }
    }
}

