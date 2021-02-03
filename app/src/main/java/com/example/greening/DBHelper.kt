package com.example.greening

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

public class myDBHelper(context: Context): SQLiteOpenHelper(context, "Greener", null, 1){
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

    fun addPerson(person: Person)
    {

    }
}