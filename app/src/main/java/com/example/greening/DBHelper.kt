package com.example.greening

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

public class DBHelper(context: Context): SQLiteOpenHelper(context, "Greener", null, 1){
    override fun onCreate(db: SQLiteDatabase?) {
        
        //DB 전체, 개별 두개로 나눠서
        
        //Name을 primary Key로 설정 - 찾아낼때 쓰이는 key
        //db!!.execSQL("CREATE TABLE Person (ID CHAR(20) PRIMARY KEY, PassWord CHAR(20), Challenge CHAR(20), Level INT(3),ChallengeCount INT(3));")

         //챌린지 데이터 베이스 생성
         //db!!.execSQL("CREATE TABLE Challenge (Name CHAR(20) PRIMARY KEY, KeyWord CHAR(20), Date INT(20), Count INT(18), Score INT(18));")

        
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
        //전체 유저에서 개인정보 입력
        //개별 유저의 테이블을 생성(테이블 이름은 아이디...)
        //CREATE TABLE + Person.ID + (ChID, ChName, StartDate, executeDate);
        db.execSQL("INSERT INTO Person VALUES ('"+person.id+"',"
                +person.password +", NULL, 0, 0);")
        db.close()
    }

    //회원가입 : 챌린지 디폴트로 하고 싶으면 - 원래 테이블에서 그대로

    //수정하기 - 챌린지 참여
    //개별 유저의 테이블에다가 참여중인 챌린지 이름, 시작한 날짜, 참여한 날짜(생성될 때 NULL)
    fun join(challegeID : String, person: Person)
    {
        person.join(challegeID)

        var db = this.writableDatabase
        //sql문 입력
        //참여한 사람 명단에다가 올리는 거 -> 전체 챌린지 테이블의 참여중인 사람 한명 늘여서 수정하고********************
        db.execSQL("UPDATE Person SET Challenge('"+person.challenge[0]+", NULL, 0);")
        //개별 유저 테이블에다가 행 추가-> 입력 INSERT**************************
        db.close()
    }

    //중복확인하기
    //전체 유저 DB에서 확인
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
    //전체 유저에서 확인
    fun checkPassWord(id : String) : String{
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

    //개별 유저가 참여한 챌린지 갯수
    //개별 유저 - 로우 갯수
    fun ChallengeCount(id : String):Int
    {
            var db = this.readableDatabase
            var cursor: Cursor
            //테이블 만든 후 칼럼 추가해서 동기화 안돼서 발생할수도.....
            cursor = db.rawQuery("SELECT * FROM Challenge WHERE ID = '" + id + "';", null)

            var count = cursor.getString(5)
            return count.toInt()
    }

    //사용자가 참여하는 개별 챌린지 DB - 챌린지 이름, 챌린지 수행한 날들 갯수....

    //********************챌린지

    //챌린지 목록 생성
    //전체 챌린지 목록에다가 입력 - 개인챌린지, 전체챌린지를 구분하는게 필요하고,
    //속성 - Boolean
    fun addChallenge(challenge: Challenge)
    {
        var db = this.writableDatabase
        //sql문 입력
        db.execSQL("INSERT INTO Challenge VALUES ('"+challenge.name+"','"
                +challenge.keyword +"'," + challenge.date+","+ challenge.count + ","+ challenge.score +");")
        db.close()
    }

}