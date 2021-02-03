package com.example.greening

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

public class DBHelper(context: Context): SQLiteOpenHelper(context, "Greener", null, 1){
    override fun onCreate(db: SQLiteDatabase?) {
        //Name을 primary Key로 설정 - 찾아낼때 쓰이는 key
        db!!.execSQL("CREATE TABLE Person (ID CHAR(20) PRIMARY KEY, PassWord CHAR(20), Challenge CHAR(20), Level INT(3),ChallengeCount INT(3));")

         //챌린지 데이터 베이스 생성
         db!!.execSQL("CREATE TABLE Challenge (Name CHAR(20) PRIMARY KEY, KeyWord CHAR(20), Date INT(20), Count INT(18), Score INT(18));")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //DB 삭제 후 다시 생성
        db!!.execSQL("DROP TABLE IF EXISTS groupTBL")
        onCreate(db)
    }

    //*******************유저
    //회원가입 - Person 객체 입력
    fun addPerson(person: Person)
    {
        var db = this.writableDatabase
        //sql문 입력
        db.execSQL("INSERT INTO Person VALUES ('"+person.id+"',"
                +person.password +", NULL, 0, 0);")
        db.close()
    }

    //수정하기 - 챌린지 참여
    fun join(challegeID : String, person: Person)
    {
        person.join(challegeID)

        var db = this.writableDatabase
        //sql문 입력
        db.execSQL("UPDATE Person SET Challenge('"+person.challenge[0]+", NULL, 0);")
        db.close()
    }

    //중복확인하기
    fun checkID (editID : String) :String{
        var db = this.readableDatabase


        var cursor: Cursor
        cursor =db.rawQuery("SELECT * FROM Person WHERE ID = '" + editID + "';", null)

        // 저장할 배열 설정
        var strId = ""

        while (cursor.moveToNext()) {
            strId += cursor.getString(0)
        }
        // 디비 닫기
        db.close()
        return strId


    }

    //비밀번호 확인하기
    fun checkPassWord(id : String) : String
    {
        //get, set 생략 가능 - 아이디와 비밀번호를 조회해서 비교하기 때문에 readable 사용
        var db = this.readableDatabase

        //SQL 조회
        var cursor: Cursor
        cursor = db.rawQuery("SELECT * FROM Person WHERE ID = '" + id + "';", null)

        //저장할 배열 설정
        var strPassWord = ""

        while (cursor.moveToNext()) {
            //1번째 행에 있는 것은 번호
            //비밀번호 조회 - 조회하는 변수에 조회된 비밀번호 넣음
            strPassWord += cursor.getString(1)
        }

        //DB 닫음
        db.close()

        return strPassWord
    }

    fun ChallengeCount(id : String):Int
    {
        var db = this.readableDatabase
        var cursor: Cursor
        cursor = db.rawQuery("SELECT * FROM Challenge WHERE ID = '" + id + "';", null)

        var count = cursor.getString(5)
        return count.toInt()
    }


    //********************챌린지

    //챌린지 목록 생성
    fun addChallenge(challenge: Challenge)
    {
        var db = this.writableDatabase
        //sql문 입력
        db.execSQL("INSERT INTO Person VALUES ('"+challenge.name+"',"
                +challenge.keyword +"," + challenge.date+","+ challenge.count + ","+ challenge.score +");")
        db.close()
    }

}