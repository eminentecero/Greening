package com.example.greening

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.nio.file.Files.size

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

        db!!.execSQL("CREATE TABLE Person (nickname CHAR(18) PRIMARY KEY, ID CHAR(20) PRIMARY KEY, PassWord CHAR(20),Level INT(3));")

        db!!.execSQL("CREATE TABLE Challenge (ID INT(18) PRIMARY KEY,Name CHAR(20) PRIMARY KEY, KeyWord CHAR(20), Date INT(20), Count INT(18), Score INT(18), BookMark INT(8),StartDate CHAR(18), LastDate CHAR(18), SummaryLong CHAR(18), Short1 CHAR(18), Short2 CHAR(18), Short3 CHAR(18), State INT(8));")


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
            db.close()
        }

    }

    fun checkChallengeName(id:String):String
    {
        var db = this.readableDatabase

        var cursor: Cursor
        cursor =db.rawQuery("SELECT * FROM Challenge WHERE ChallengeID = '" + id + "' AND WHERE State = 0;", null)

        // 저장할 배열 설정
        var challenge = ""

        while (cursor.moveToNext()) {
            challenge += cursor.getString(1)
        }
        // 디비 닫기
        db.close()
        return challenge
    }

    fun ChallengeTotalCount():Int
    {
        var db = this.readableDatabase

        var cursor:Cursor
        cursor =db.rawQuery("SELECT COUNT(ID) FROM Challenge;", null)

        // 저장할 배열 설정
        var challenge = 0

        while (cursor.moveToNext()) {
            challenge = cursor.getInt(0)
        }
        // 디비 닫기
        db.close()
        return challenge
    }

    fun CategoryCount(KeyWord:String):Int
    {
        var db = this.readableDatabase

        var cursor:Cursor
        cursor =db.rawQuery("SELECT * FROM Challenge WHERE KeyWord = '${KeyWord}';", null)

        // 저장할 배열 설정
        var challenge = 0


        while (cursor.moveToNext()) {
            challenge ++
        }
        // 디비 닫기
        db.close()
        return challenge
    }

    fun CategoryDoneCount(KeyWord: String, User:Person):Int
    {
        var db = this.readableDatabase

        var cursor:Cursor
        cursor =db.rawQuery("SELECT * FROM "+User.id+" WHERE KeyWord = '${KeyWord}' AND State = 1;", null)

        // 저장할 배열 설정
        var challenge = 0


        while (cursor.moveToNext()) {
            challenge ++
        }
        // 디비 닫기
        db.close()
        return challenge
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
    fun checkReview(Id: String, challenge: Challenge) :String{
        var db = this.readableDatabase

        var cursor: Cursor
        cursor =db.rawQuery("SELECT * FROM REVIEW"+challenge.id+" WHERE UserId = '" + Id + "';", null)

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

    //레벨 계산
    fun Level(UserID: String):Int
    {
        //해당 회원의 ID를 전체 회원 테이블에 검색
        var db = this.readableDatabase
        var cursor: Cursor

        var EXP = 0
        var Count = 0
        //개인 유저의 Table에서 챌린지 갯수 세어서 반환
        cursor = db.rawQuery("SELECT * FROM "+UserID+" WHERE State = 1;", null)

        while (cursor.moveToNext()) {
            Count ++
        }

        EXP = Count*25
        var Level = EXP/100

        return Level
    }

    //다음 레벨 남은 거 계산
    fun leftLevel(UserID: String):Int
    {
        //해당 회원의 ID를 전체 회원 테이블에 검색
        var db = this.readableDatabase
        var cursor: Cursor

        var EXP:Int
        var Count = 0
        //개인 유저의 Table에서 챌린지 갯수 세어서 반환
        cursor = db.rawQuery("SELECT * FROM "+UserID+" WHERE State = 1;", null)

        while (cursor.moveToNext()) {
            Count ++
        }

        EXP = Count*25
        var leftLevel = EXP%100


        return leftLevel
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
        cursor = db.rawQuery("SELECT * FROM " + person.id + " WHERE State = 0;", null)

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

    // 회원 탈퇴하는 함 수: 매개변수로 아이디를 전달받아 탈퇴함.
    fun deleteUser(UserID: String){
        var db = this.writableDatabase

        db.execSQL("DELETE FROM Person WHERE ID = '" + UserID +"';")
        db.execSQL("DROP TABLE CHALLENGE" + UserID + ";")

        db.close()
    }

    // 비밀번호를 변경하는 함수: 매개변수로 유저의 아이디와 새로운 비밀번호를 입력받음.
    fun changePassword(UserID: String, UserNewPassWord:String){
        var db = this.writableDatabase
        var cursor: Cursor
        var User : Person = Person()

        db.execSQL("UPDATE Person SET PassWord = " + UserNewPassWord +" WHERE ID = '" + UserID + "';")

        db.close()
    }

    // 닉네임을 변경하는 함수: 매개변수로 유저의 아이디와 새로운 닉네임를 입력받음.
    fun changeNickname(UserID: String, UserNewNickname:String){
        var db = this.writableDatabase

        db.execSQL("UPDATE Person SET nickname = '" + UserNewNickname + "' WHERE ID = '" + UserID + "';")

        db.close()
    }

    //각 카테고리별 챌린지 가져오기 - 추천 챌린지
    fun Challengecategory(User:Person) : Array<Challenge>
    {
        //추천 챌린지를 넣을 챌린지 배열 생성

        var anyArray = arrayOf<Challenge>()

        var db = this.readableDatabase
        var cursor: Cursor

        //추천 챌린지 분류할 각각 키워드
        var KeyWord = arrayOf("플라스틱", "자원", "운동", "음식", "기타")
        for (i in 0..4 step 1)
        {
            //각 카테고리의 첫번쨰 데이터 받아옴 - SQL설명 : 유저 테이블에 아이디가 존재하는 챌린지를 제외한 모든 챌린지
            cursor = db.rawQuery("SELECT * FROM  Challenge WHERE KeyWord = '${KeyWord[i]}' AND ID NOT IN (SELECT ID FROM ${User.id} WHERE ChallengeID IN (ID));", null)
            if(cursor.count >0) {
                while(cursor.moveToNext())
                {
                    var id = cursor.getString(0).toInt()
                    var name = cursor.getString(1)
                    var keyword = cursor.getString(2)
                    var date = cursor.getString(3).toInt()
                    var count = cursor.getString(4).toInt()
                    var score = cursor.getString(5).toFloat()
                    var bookmark = cursor.getString(6).toInt()
                    var startdate = cursor.getString(7)
                    var lastdate = cursor.getString(8)
                    var summarylong = cursor.getString(9)
                    var short1 = cursor.getString(10)
                    var short2 = cursor.getString(11)
                    var short3 = cursor.getString(12)
                    var state = cursor.getInt(13)

                    var challenge = Challenge()
                    challenge.id = id
                    challenge.name = name
                    challenge.keyword = keyword
                    challenge.count = count
                    challenge.score = score
                    challenge.bookmark = bookmark
                    challenge.StartDate = startdate
                    challenge.LastDate = lastdate
                    //challenge.date = challenge.D_Day(lastdate).toInt()
                    challenge.date = date
                    challenge.SummaryLong = summarylong
                    challenge.Short1 = short1
                    challenge.Short2 = short2
                    challenge.Short3 = short3
                    challenge.State = state
                    anyArray+=challenge
                    Log.d("내용", challenge.id.toString())
                }
            }
        }
        Log.d("내용", anyArray.size.toString())

        // 디비 닫기
        db.close()

        return anyArray
    }

    //챌린지 수행완료 설정하기
    fun ChallengeCompelete(challenge: Challenge, id: String)
    {
        var db = this.writableDatabase
        //유저 DB의 상태 완료(1)로 변경
        db.execSQL("UPDATE "+id+" SET State = 1 WHERE ChallengeID = '"+challenge.id.toString()+"';")
        db.execSQL("UPDATE Challenge SET State = 1 WHERE ID = '"+challenge.id.toString()+"';")
        db.execSQL("DROP TABLE CHALLENGE" + challenge.id.toString() + ";")
        db.execSQL("CREATE TABLE REVIEW" + challenge.id + " (UserID CHAR(20), Score FLOAT(8));")


        db.close()
    }

    //챌린지 수행한 날짜 DB에 저장하기
    fun ChallengeRecord(challenge: Challenge, date: Array<Dates>, User: Person)
    {
        var db = this.writableDatabase
        //sql문 입력
        //전체 챌린지 목록에 해당 챌린지 정보들 입력

        //해당 챌린지 데이터 다 삭제하기
        db.execSQL("delete from CHALLENGE" + challenge.id +";")

        //받은 date 배열 만큼 반복 - 순서대로 해당 챌린지 DB에 저장
        for(i in 0..(date.size-1))
        {
            db.execSQL("INSERT INTO CHALLENGE"+challenge.id+" VALUES("+date.get(i).year+","+date.get(i).month+","+date.get(i).day+");")
        }

        db.execSQL("UPDATE "+User.id+" SET Count = "+date.size+" WHERE ChallengeID = "+challenge.id+";")

        db.close()
    }

    fun UserDay(challengeId: Int, User: Person):Int
    {
        var db = this.readableDatabase
        var cursor: Cursor

        var count = 0
        //각 카테고리의 첫번쨰 데이터 받아옴
        cursor = db.rawQuery("SELECT * FROM "+User.id+" WHERE ChallengeID = "+challengeId+";", null)

        while(cursor.moveToNext())
        {
            count = cursor.getString(3).toInt()
        }
       return count
    }


    //챌린지 수행한 날 받아오기
    fun ChallengeDay(challengeId: Int):Array<Dates>
    {
        var db = this.readableDatabase
        var cursor: Cursor

        var dates = arrayOf<Dates>()
        //각 카테고리의 첫번쨰 데이터 받아옴
        cursor = db.rawQuery("SELECT * FROM CHALLENGE" + challengeId +";", null)

        while (cursor.moveToNext()) {
            //해당 행의 row의 정보를 string으로 받아 저장
            var year = cursor.getString(0).toInt()
            var month = cursor.getString(1).toInt()
            var day = cursor.getString(2).toInt()
            var date = Dates(year, month, day)

            dates += date
        }
        return dates
    }

    //전체 챌린지 리스트에서 객체 가져오기
    fun Challengereturn(id: Int):Challenge
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
            var startdate = cursor.getString(7)
            var lastdate = cursor.getString(8)
            var summarylong = cursor.getString(9)
            var short1 = cursor.getString(10)
            var short2 = cursor.getString(11)
            var short3 = cursor.getString(12)

            challenge.id = id
            challenge.name = name
            challenge.keyword = keyword
            challenge.count = count
            challenge.score = score
            challenge.bookmark = bookmark
            challenge.StartDate = startdate
            challenge.LastDate = lastdate
            //challenge.date = challenge.D_Day(lastdate).toInt()
            challenge.date = date
            challenge.SummaryLong = summarylong
            challenge.Short1 = short1
            if(short2 ==null){
                challenge.Short2 = ""
            }else{
                challenge.Short2 = short2
            }
            if(short3 ==null){
                challenge.Short3 = ""
            }else{
                challenge.Short3 = short3
            }
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
            cursor = db.rawQuery("SELECT * FROM Challenge WHERE State = 1;", null)
        }else {
            cursor = db.rawQuery("SELECT * FROM Challenge WHERE Keyword = '${keyword}' AND State = 1 ;", null)
        }


        while (cursor.moveToNext()) {
            var id = cursor.getString(0).toInt()
            var name = cursor.getString(1)
            var keyword = cursor.getString(2)
            var date = cursor.getString(3).toInt()
            var count = cursor.getString(4).toInt()
            var score = cursor.getString(5).toFloat()
            var bookmark = cursor.getString(6).toInt()
            var startdate = cursor.getString(7)
            var lastdate = cursor.getString(8)
            var longSummary = cursor.getString(9)
            var short1 = cursor.getString(10)
            var short2 = cursor.getString(11)
            var short3 = cursor.getString(12)

            var challenge =  Challenge(id, name, keyword, date, count, score, bookmark, startdate, lastdate, longSummary, short1, short2, short3)
            anyArray+=challenge
        }

        db.close()
        return anyArray
    }

    //키워드 별로 - 북마크 표시된 것과 표시 안된거 분류해서 받아오기
    fun ChallengeList(keyword:String, id:String):Array<Challenge>
    {
        var db = this.readableDatabase
        var cursor: Cursor

        var anyArray = arrayOf<Challenge>()

        cursor = db.rawQuery("SELECT * FROM Challenge WHERE Keyword = '${keyword}' AND BookMark = 1 AND State = 0;", null)

        while(cursor.moveToNext()){
            var id = cursor.getString(0).toInt()
            var name = cursor.getString(1)
            var keyword = cursor.getString(2)
            var date = cursor.getString(3).toInt()
            var count = cursor.getString(4).toInt()
            var score = cursor.getString(5).toFloat()
            var bookmark = cursor.getString(6).toInt()
            var startdate = cursor.getString(7)
            var lastdate = cursor.getString(8)
            var longSummary = cursor.getString(9)
            var short1 = cursor.getString(10)
            var short2 = cursor.getString(11)
            var short3 = cursor.getString(12)

            var challenge =  Challenge(id, name, keyword, date, count, score, bookmark, startdate, lastdate, longSummary, short1, short2, short3)
            //challenge.date = challenge.D_Day(lastdate).toInt()
            anyArray+=challenge
        }

        cursor = db.rawQuery("SELECT * FROM Challenge WHERE Keyword = '${keyword}' AND BookMark = 0 AND State = 0;", null)

        while (cursor.moveToNext()) {
            var id = cursor.getString(0).toInt()
            var name = cursor.getString(1)
            var keyword = cursor.getString(2)
            var date = cursor.getString(3).toInt()
            var count = cursor.getString(4).toInt()
            var score = cursor.getString(5).toFloat()
            var bookmark = cursor.getString(6).toInt()
            var startdate = cursor.getString(7)
            var lastdate = cursor.getString(8)
            var longSummary = cursor.getString(9)
            var short1 = cursor.getString(10)
            var short2 = cursor.getString(11)
            var short3 = cursor.getString(12)

            var challenge =  Challenge(id, name, keyword, date, count, score, bookmark, startdate, lastdate, longSummary, short1, short2, short3)
            //challenge.date = challenge.D_Day(lastdate).toInt()
            anyArray+=challenge
        }

        cursor = db.rawQuery("SELECT * FROM Challenge WHERE Keyword = '${keyword}'  AND State = 1;", null)

        while (cursor.moveToNext()) {
            var id = cursor.getString(0).toInt()
            var name = cursor.getString(1)
            var keyword = cursor.getString(2)
            var date = cursor.getString(3).toInt()
            var count = cursor.getString(4).toInt()
            var score = cursor.getString(5).toFloat()
            var bookmark = cursor.getString(6).toInt()
            var startdate = cursor.getString(7)
            var lastdate = cursor.getString(8)
            var longSummary = cursor.getString(9)
            var short1 = cursor.getString(10)
            var short2 = cursor.getString(11)
            var short3 = cursor.getString(12)

            var challenge =  Challenge(id, name, keyword, date, count, score, bookmark, startdate, lastdate, longSummary, short1, short2, short3)
            //challenge.date = challenge.D_Day(lastdate).toInt()
            anyArray+=challenge
        }

        for(i in 0..anyArray.size-1)
        {
            cursor = db.rawQuery("SELECT * FROM "+id+" WHERE ChallengeID = '${anyArray[i].id}';", null)

            if(cursor.count >0){
                cursor.moveToFirst()
                //해당 행의 row의 정보를 string으로 받아 저장
                var state = cursor.getInt(4)

                anyArray[i].State = state
            }
        }

        db.close()
        return anyArray
    }

    fun ChallengeReview(challenge: Challenge, UserId: String)
    {
        var db = this.writableDatabase

        db.execSQL("INSERT INTO REVIEW"+challenge.id+" VALUES('"+UserId+"',"+challenge.score+");")


        db.close()
    }

    fun ChallengeScoreCalculate(challenge: Challenge):Float
    {
        var db = this.readableDatabase
        var cursor: Cursor

        var score:Float = 0.0f
        var count:Int = 0
        //각 카테고리의 첫번째 데이터 받아옴
        cursor = db.rawQuery("SELECT * FROM Challenge"+challenge.id+";", null)

        while(cursor.moveToNext())
        {
            score += cursor.getFloat(5)
            count++
        }
        //사용자들 총 합산 / 사용자들 몇명
        var TotalScore = score/count
        return TotalScore

        challenge.score = TotalScore

        db = this.writableDatabase

        db.execSQL("UPDATE Challenge SET Score = "+TotalScore+" WHERE ID = '"+challenge.id+"';")

    }

    fun ChallengeMark(challenge:Challenge)
    {
        var db = this.writableDatabase

        if(challenge.bookmark == 0)
        {
            //전체 챌린지 관리에 참가중인
            db.execSQL("UPDATE Challenge SET BookMark = 1 WHERE ID = '"+challenge.id+"';")
        }else{
            //전체 챌린지 관리에 참가중인
            db.execSQL("UPDATE Challenge SET BookMark = 0 WHERE ID = '"+challenge.id+"';")
        }
        db.close()
    }

    fun ChallengeCategoryMark(Array: Array<Challenge>): Array<Challenge>
    {
        var db = this.readableDatabase
        var cursor: Cursor

        var anyArray = arrayOf<Challenge>()

        for (i in 0..4 step 1)
        {
            //각 카테고리의 첫번째 데이터 받아옴
            cursor = db.rawQuery("SELECT * FROM Challenge WHERE ID = '${Array[i].id}' AND BookMark = 1;", null)

            if(cursor.count >0){
                cursor.moveToFirst()
                var id = cursor.getString(0).toInt()
                var name = cursor.getString(1)
                var keyword = cursor.getString(2)
                var date = cursor.getString(3).toInt()
                var count = cursor.getString(4).toInt()
                var score = cursor.getString(5).toFloat()
                var bookmark = cursor.getString(6).toInt()
                var startdate = cursor.getString(7)
                var lastdate = cursor.getString(8)
                var summarylong = cursor.getString(9)
                var short1 = cursor.getString(10)
                var short2 = cursor.getString(11)
                var short3 = cursor.getString(12)

                var challenge = Challenge()
                challenge.id = id
                challenge.name = name
                challenge.keyword = keyword
                challenge.count = count
                challenge.score = score
                challenge.bookmark = bookmark
                challenge.StartDate = startdate
                challenge.LastDate = lastdate
                //challenge.date = challenge.D_Day(lastdate).toInt()
                challenge.date = date
                challenge.SummaryLong = summarylong
                challenge.Short1 = short1
                challenge.Short2 = short2
                challenge.Short3 = short3
                anyArray += challenge
            } else {
                continue
            }
        }

        for (i in 0..4 step 1)
        {
            //각 카테고리의 첫번쨰 데이터 받아옴
            cursor = db.rawQuery("SELECT * FROM Challenge WHERE ID = '${Array[i].id}' AND BookMark = 0;", null)

            if(cursor.count >0){
                cursor.moveToFirst()
                var id = cursor.getString(0).toInt()
                var name = cursor.getString(1)
                var keyword = cursor.getString(2)
                var date = cursor.getString(3).toInt()
                var count = cursor.getString(4).toInt()
                var score = cursor.getString(5).toFloat()
                var bookmark = cursor.getString(6).toInt()
                var startdate = cursor.getString(7)
                var lastdate = cursor.getString(8)
                var summarylong = cursor.getString(9)
                var short1 = cursor.getString(10)
                var short2 = cursor.getString(11)
                var short3 = cursor.getString(12)

                var challenge = Challenge()
                challenge.id = id
                challenge.name = name
                challenge.keyword = keyword
                challenge.count = count
                challenge.score = score
                challenge.bookmark = bookmark
                challenge.StartDate = startdate
                challenge.LastDate = lastdate
                //challenge.date = challenge.D_Day(lastdate).toInt()
                challenge.date = date
                challenge.SummaryLong = summarylong
                challenge.Short1 = short1
                challenge.Short2 = short2
                challenge.Short3 = short3
                anyArray += challenge
            } else {
                continue
            }
        }
        return anyArray
    }

    //기간이 지난 챌린지 자동으로 기간 갱신
    fun cleanChallenge(person: String){
        var db = this.readableDatabase

        var cursor: Cursor
        cursor =db.rawQuery("SELECT * FROM Challenge;", null)

        var anyArray = arrayOf<Challenge>()

        if(cursor.count >0) {
            cursor.moveToFirst()
            var id = cursor.getString(0).toInt()
            var name = cursor.getString(1)
            var keyword = cursor.getString(2)
            var date = cursor.getString(3).toInt()
            var count = cursor.getString(4).toInt()
            var score = cursor.getString(5).toFloat()
            var bookmark = cursor.getString(6).toInt()
            var startdate = cursor.getString(7)
            var lastdate = cursor.getString(8)
            var summarylong = cursor.getString(9)
            var short1 = cursor.getString(10)
            var short2 = cursor.getString(11)
            var short3 = cursor.getString(12)
            var state = cursor.getInt(13)

            var challenge = Challenge()
            challenge.id = id
            challenge.name = name
            challenge.keyword = keyword
            challenge.count = count
            challenge.score = score
            challenge.bookmark = bookmark
            challenge.StartDate = startdate
            challenge.LastDate = lastdate
            //challenge.date = challenge.D_Day(lastdate).toInt()
            challenge.date = date
            challenge.SummaryLong = summarylong
            challenge.Short1 = short1
            challenge.Short2 = short2
            challenge.Short3 = short3
            challenge.State = state
            anyArray += challenge
        }
        // 디비 닫기
        db.close()

        for(i in 0..anyArray.size-1)
        {
            if(anyArray[i].date<0 && anyArray[i].State==0)
            {
                anyArray[i].State = 1
                ChallengeCompelete(anyArray[i], person)
            }
        }
    }

    //마이 페이지 -  관심 챌린지(즐겨찾기 + 신청 안 한 챌린지여야 함)
    fun MyMarkChallenge(User:Person):Array<Challenge>
    {
        var anyArray = arrayOf<Challenge>()

        var db = this.readableDatabase
        var cursor: Cursor
        //유저가 참여중이지 않고, 전체 챌린지 테이블에서 북마크 표시되어있는 걸 찾아옴
        cursor =db.rawQuery("SELECT * FROM  Challenge WHERE BookMark = 1 AND ID NOT IN (SELECT ID FROM "+User.id+" WHERE ChallengeID IN (ID));", null)

        while(cursor.moveToNext()) {
            var id = cursor.getString(0).toInt()
            var name = cursor.getString(1)
            var keyword = cursor.getString(2)
            var date = cursor.getString(3).toInt()
            var count = cursor.getString(4).toInt()
            var score = cursor.getString(5).toFloat()
            var bookmark = cursor.getString(6).toInt()
            var startdate = cursor.getString(7)
            var lastdate = cursor.getString(8)
            var summarylong = cursor.getString(9)
            var short1 = cursor.getString(10)
            var short2 = cursor.getString(11)
            var short3 = cursor.getString(12)
            var state = cursor.getInt(13)

            var challenge = Challenge()
            challenge.id = id
            challenge.name = name
            challenge.keyword = keyword
            challenge.count = count
            challenge.score = score
            challenge.bookmark = bookmark
            challenge.StartDate = startdate
            challenge.LastDate = lastdate
            challenge.date = date
            challenge.SummaryLong = summarylong
            challenge.Short1 = short1
            challenge.Short2 = short2
            challenge.Short3 = short3
            challenge.State = state

            anyArray += challenge
        }
        // 디비 닫기
        db.close()

        return anyArray
    }
}