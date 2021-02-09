package com.example.greening

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.applikeysolutions.cosmocalendar.model.Day
import java.util.*

//Table 정의
//Person Table -> 전체 유저 관리용
//Challenge Table -> 전체 챌린지 관리용, 개별 챌린지 참여 유저 관리용
//User Table -> 개인 유저 관리용


//챌린지 - 전체 챌린지 하나[챌린지 개인 고유 번호 - 001, 002, 003....이렇게]
//유저 - 전체 유저, 개별 유저(참여중인 챌린지 - 고유 번호를 저장)
//리뷰 - 리뷰 테이블(챌린지 번호, 리뷰 번호(정렬할 때), 유저 아이디, 리뷰 내용, 날짜)
//챌린지 캘린더 - 개별 유저 테이블(시작했던 날짜, 참여했던 날짜 따로따로 저장해서 이거 불러와서 표시....)

//**********************************************개별 챌린지 필요없음 수정하기

//프로그램 시작할 때 필요한 기본으로 필요한 DB - 개인 유저 DB와 전체 챌린지 DB
class DBHelper(context: Context): SQLiteOpenHelper(context, "Greener", null, 1){
    override fun onCreate(db: SQLiteDatabase?) {
        
        //DB 전체, 개별 두개로 나눠서
        
        //Name을 primary Key로 설정 - 찾아낼때 쓰이는 key
        //db!!.execSQL("CREATE TABLE Person (ID CHAR(20) PRIMARY KEY, PassWord CHAR(20),Level INT(3));")

         //챌린지 데이터 베이스 생성
         //db!!.execSQL("CREATE TABLE Challenge (Name CHAR(20) PRIMARY KEY, KeyWord CHAR(20), Date INT(20), Count INT(18), Score INT(18));")

        
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //DB 삭제 후 다시 생성
        db!!.execSQL("DROP TABLE IF EXISTS Greener")
        onCreate(db)
    }

    //유저
    //회원가입 - Person 객체 입력
    fun addPerson(person: Person)
    {
        var db = this.writableDatabase
        //sql문 입력
        //전체 유저에서 개인정보 입력
        //개별 유저의 테이블을 생성(테이블 이름은 아이디...)
        //CREATE TABLE + Person.ID + (ChID, ChName, StartDate, executeDate);
        //*******************Person table 수정 - ID CHAR(20) PRIMARY KEY, PassWord CHAR(20),Level INT(3)
        db.execSQL("INSERT INTO Person VALUES ('" + person.nickname + "','" + person.id + "',"
                + person.password + ", 0);")

        //개인 유저 테이블 생성 - 참여한 챌린지 목록들 저장
        db!!.execSQL("CREATE TABLE " + person.id + " (ChallengeID CHAR(20), Name CHAR(20), Keyword CHAR(20), Count INT(18), State INT(18));")


        db.close()
    }

    //회원가입 : 챌린지 디폴트로 하고 싶으면 - 원래 테이블에서 그대로

    //챌린지 참여
    //개별 유저의 테이블에다가 참여중인 챌린지 이름, 시작한 날짜, 참여한 날짜(생성될 때 NULL)
    fun join(challenge: Challenge, person: Person)
    {
        //중복되거나 유저가 참여한 챌린지의 갯수가 3개면 더이상 참여 안됨
        if(UserjoinCount(person)>=3||checkChallenge(challenge, person).equals(challenge.id.toString())){
            //안된다는 익셉션 처리 해주기
        }else{
            var db = this.writableDatabase
            //개인 유저의 Table에다가 해당 유저가 참여할 챌린지에 대한 정보를 입력
            //챌린지 아이디, 챌린지 이름, 챌린지 유형, 챌린지 수행한 횟수...
            db.execSQL("INSERT INTO "+person.id+" VALUES('"+challenge.id+"','"+challenge.name+"','"+challenge.keyword+"', 0, 0, "+challenge.date+");")

            //전체 챌린지 관리에 참가중인 인원 변경
            db.execSQL("UPDATE Challenge SET Count = "+ challenge.count +" WHERE ID = '"+challenge.id.toString()+"';")

            db!!.execSQL("CREATE TABLE CHALLENGE" + challenge.id + " (Year INT(18), Month INT(18), Date INT(18));")
            //sql문 입력
            //해당 챌린지 개별 테이블에 참여한 사람을 명단에다가 올림
            //****************그날 날짜에 대한 정보 받을 필요있음 - 수정해야함

            //db.execSQL("INSERT INTO Challenge" + challenge.id + " VALUES('" + person.id + "','210207', 0);")
            db.close()
        }

    }

    fun checkChallenge(challenge: Challenge, person: Person):String
    {
        var db = this.readableDatabase


        var cursor: Cursor
        cursor =db.rawQuery("SELECT * FROM "+person.id+" WHERE ChallengeID = '" + challenge.id + "';", null)

        // 저장할 배열 설정
        var strNickname = ""

        while (cursor.moveToNext()) {
            strNickname += cursor.getString(0)
        }
        // 디비 닫기
        db.close()
        return strNickname
    }

    //중복확인하기
    //전체 유저 DB에서 확인
    fun checkNickname(editNickname: String) :String{
        var db = this.readableDatabase


        var cursor: Cursor
        cursor =db.rawQuery("SELECT * FROM Person WHERE NickName = '" + editNickname + "';", null)

        // 저장할 배열 설정
        var strNickname = ""

        while (cursor.moveToNext()) {
            strNickname += cursor.getString(0)
        }
        // 디비 닫기
        db.close()
        return strNickname
    }

    //중복확인하기
    //전체 유저 DB에서 확인
    fun checkID(editID: String) :String{
        var db = this.readableDatabase


        var cursor: Cursor
        cursor =db.rawQuery("SELECT * FROM Person WHERE ID = '" + editID + "';", null)

        // 저장할 배열 설정
        var strId = ""

        while (cursor.moveToNext()) {
            strId += cursor.getString(1)
        }
        // 디비 닫기
        db.close()
        return strId
    }


    //로그인
    //전체 유저에서 확인
    fun checkPassWord(id: String) : String{
        var db = this.readableDatabase

        //SQL 조회
        var cursor: Cursor
        cursor = db.rawQuery("SELECT * FROM Person WHERE ID = '" + id + "';", null)

        //저장할 배열 설정
        var strPassWord = ""

        while (cursor.moveToNext()) {
            //1번째 행에 있는 것은 번호
            //비밀번호 조회 - 조회하는 변수에 조회된 비밀번호 넣음
            strPassWord += cursor.getString(2)
        }

        //DB 닫음
        db.close()

        return strPassWord
    }

    //유저의 정보를 반환하는 함수
    fun DataIn(UserID: String) : Person
    {

        //해당 회원의 ID를 전체 회원 테이블에 검색
        var db = this.readableDatabase
        var cursor: Cursor
        var User : Person = Person()
        //개인 유저의 Table에서 챌린지 갯수 세어서 반환
        cursor = db.rawQuery("SELECT * FROM Person WHERE ID = '" + UserID + "';", null)

        while (cursor.moveToNext()) {
            //해당 행의 row의 정보를 string으로 받아 저장
            var nickname = cursor.getString(0)
            var id = cursor.getString(1)
            var passWord = cursor.getString(2)
            var level = cursor.getString(3)

            User.nickname = nickname
            User.id = id
            User.password = passWord
            User.level = level.toInt()
        }
        return User
    }

    //챌린지에 참여중인 사람 인원 수 return 해주는 함수
    fun ChallengeJoinCount(challenge: Challenge):String
    {
        var db = this.readableDatabase
        var cursor: Cursor
        var count = ""

        //개인 유저의 Table에서 챌린지 갯수 세어서 반환
        cursor = db.rawQuery("SELECT * FROM Challenge where ID = '"+challenge.id+"';", null)

        while (cursor.moveToNext())
        {
            count = cursor.getString(4)
        }
        return count
    }

    //개별 유저가 참여한 챌린지 갯수
    //개별 유저 - 로우 갯수
    fun UserjoinCount(person: Person):Int
    {
            var db = this.readableDatabase
            var cursor: Cursor
            var count:Int = 0

            //개인 유저의 Table에서 챌린지 갯수 세어서 반환
            cursor = db.rawQuery("SELECT * FROM " + person.id + " WHERE State = '0';", null)

        while (cursor.moveToNext())
        {
            count++
        }
            count
            return count
    }

    //사용자가 참여하는 개별 챌린지 DB - 챌린지 이름, 챌린지 수행한 날들 갯수....

    //챌린지

    //챌린지 목록 생성
    //전체 챌린지 목록에다가 입력 - 개인챌린지, 전체챌린지를 구분하는게 필요하고,
    //개별 챌린지 생성
    //속성 - Boolean
    fun addChallenge(challenge: Challenge)
    {
        var db = this.writableDatabase
        //sql문 입력
        //전체 챌린지 목록에 해당 챌린지 정보들 입력
        db.execSQL("INSERT INTO Challenge VALUES ('" + challenge.id + "','" + challenge.name + "','"
                + challenge.keyword + "'," + challenge.date + "," + challenge.count + "," + challenge.score + ", 0);")

        db.close()
    }

    //유저가 참여중인 챌린지 목록 불러오기
    fun ChallengeIn(person: Person): Array<Challenge> {
        var db = this.readableDatabase
        var cursor: Cursor

        var anyArray = arrayOf<Challenge>()

        var date = ""
        var id = ""
        var name = ""
        var keyword = ""


        //개인 유저의 Table에서 챌린지 갯수 세어서 반환
        cursor = db.rawQuery("SELECT * FROM '" + person.id + "';", null)

        while (cursor.moveToNext()) {
            //해당 행의 row의 정보를 string으로 받아 저장
            id = cursor.getString(0)
            name = cursor.getString(1)
            keyword = cursor.getString(2)
            date = cursor.getString(5)

            var ingchallenge : Challenge = Challenge(id.toInt(), name, keyword, date.toInt())
            anyArray+=ingchallenge
        }

        db.close()
        return anyArray
    }

    //각 카테고리별 챌린지 가져오기
    fun Challengecategory() : Array<Challenge>
    {
        var db = this.readableDatabase
        var cursor: Cursor

        var KeyWord = arrayOf("플라스틱", "자원", "운동", "음식", "기타")
        var anyArray = arrayOf<Challenge>()

        for (i in 0..4 step 1)
        {
            //각 카테고리의 첫번쨰 데이터 받아옴
            cursor = db.rawQuery("SELECT * FROM Challenge WHERE KeyWord = '${KeyWord[i]}';", null)

            if(cursor.count >0){
                cursor.moveToFirst()
                //해당 행의 row의 정보를 string으로 받아 저장
                var id = cursor.getString(0)
                var name = cursor.getString(1)
                var keyword = cursor.getString(2)
                var count = cursor.getString(3)
                //ChallengeID CHAR(20), Name CHAR(20), Keyword CHAR(20), Count INT(18)
                var challenge: Challenge = Challenge(id.toInt(), name, keyword, count.toInt())

                anyArray += challenge
            } else {
                continue
            }
        }

        return anyArray
    }

    //챌린지 즐겨찾기

    //챌린지 수행완료한 리스트 불러오기
    fun ChallengeCompelete(challenge: Challenge): Array<Calendar>
    {
        var db = this.readableDatabase
        var cursor: Cursor
        var anyArray = arrayOf<Calendar>()
        //각 카테고리의 첫번쨰 데이터 받아옴
            cursor = db.rawQuery("SELECT * FROM CHALLENGE"+challenge.id+";", null)

        while (cursor.moveToNext()) {
            //해당 행의 row의 정보를 string으로 받아 저장
            var year = cursor.getString(0)
            var month = cursor.getString(1)
            var date = cursor.getString(2)
            //var days:Calendar = Calendar(year, month, date)

            //anyArray+=days
        }
        return anyArray
    }

    //챌린지 수행한 날짜 DB에 저장하기
    fun ChallengeRecord(challenge: Challenge, date: Days)
    {
        var db = this.writableDatabase
        //sql문 입력
        //전체 챌린지 목록에 해당 챌린지 정보들 입력

        //해당 챌린지 데이터 다 삭제하기
        db.execSQL("delete from CHALLENGE" + challenge.id +";")
        var dateArray = arrayOf<Date>()

        //받은 date 배열 만큼 반복 - 순서대로 해당 챌린지 DB에 저장
       for(i in dateArray)
        {
            db.execSQL("INSERT INTO CHALLENGE "+challenge.id+" VALUES("+date.year+", "+date.month+","+date.date+");")
        }

        db.close()
    }

    fun Challengereturn(id:Int):Challenge
    {
        var db = this.readableDatabase
        var cursor: Cursor
        var challenge = Challenge()
        //각 카테고리의 첫번쨰 데이터 받아옴
        cursor = db.rawQuery("SELECT * FROM Challenge WHERE ID = '"+id.toString()+"';", null)

        while (cursor.moveToNext()) {
            //해당 행의 row의 정보를 string으로 받아 저장
            var id = cursor.getString(0).toInt()
            var name = cursor.getString(1)
            var keyword = cursor.getString(2)
            var date = cursor.getString(3).toInt()
            var count = cursor.getString(4).toInt()
            var score = cursor.getString(5).toFloat()
            var bookmark = cursor.getString(6).toInt()

            challenge.id = id
            challenge.name = name
            challenge.keyword = keyword
            challenge.date = date
            challenge.count = count
            challenge.score = score
            challenge.bookmark = bookmark
        }
        return challenge
    }

    //완료한 챌린지 가져오기
    fun ChallengeListDone(keyword:String, id:String):Array<Challenge>
    {
            var db = this.readableDatabase
            var cursor: Cursor

            var anyArray = arrayOf<Challenge>()

            if(keyword.equals("all")) {
                cursor = db.rawQuery("SELECT * FROM ${id} WHERE State = 1;", null)
            }else {
                cursor = db.rawQuery("SELECT * FROM ${id} WHERE Keyword = '${keyword}' AND State = 1 ;", null)
            }

            while (cursor.moveToNext()) {
                var id = cursor.getString(0).toInt()
                var name = cursor.getString(1)
                var keyword = cursor.getString(2)
                var date = cursor.getString(5).toInt()

                var challenge =  Challenge(id, name, keyword, date)
                anyArray+=challenge
            }

            db.close()
            return anyArray
        }
    }
