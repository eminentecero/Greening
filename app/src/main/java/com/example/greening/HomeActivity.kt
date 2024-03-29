package com.example.greening

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class HomeActivity  : AppCompatActivity() {
    //사용할 변수
    lateinit var UserName:TextView

    //참여중인 챌린지에서 더보기 버튼
    lateinit var ingChallenge1: androidx.cardview.widget.CardView
    lateinit var ingChallenge2: androidx.cardview.widget.CardView
    lateinit var ingChallenge3: androidx.cardview.widget.CardView

    lateinit var ing1_TextView : TextView
    lateinit var ing1_Button : Button
    lateinit var ing1Count_TextView : TextView
    lateinit var ing1Date_TextView : TextView
    lateinit var ingmore1_Button : Button

    lateinit var ing2_TextView : TextView
    lateinit var ing2_Button : Button
    lateinit var ing2Count_TextView : TextView
    lateinit var ing2Date_TextView : TextView
    lateinit var ingmore2_Button : Button

    lateinit var ing3_TextView : TextView
    lateinit var ing3_Button : Button
    lateinit var ing3Count_TextView : TextView
    lateinit var ing3Date_TextView : TextView
    lateinit var ingmore3_Button : Button

    //추천하는 챌린지
    lateinit var recomText : TextView

    lateinit var name1 : TextView
    lateinit var keyword1 : Button
    lateinit var count1 : TextView
    lateinit var date1 : TextView

    lateinit var name2 : TextView
    lateinit var keyword2 : Button
    lateinit var count2 : TextView
    lateinit var date2 : TextView

    lateinit var name3 : TextView
    lateinit var keyword3 : Button
    lateinit var count3 : TextView
    lateinit var date3 : TextView

    lateinit var name4 : TextView
    lateinit var keyword4 : Button
    lateinit var count4 : TextView
    lateinit var date4 : TextView

    lateinit var name5 : TextView
    lateinit var keyword5 : Button
    lateinit var count5 : TextView
    lateinit var date5 : TextView

    lateinit var recom1 : androidx.cardview.widget.CardView
    lateinit var recom2 : androidx.cardview.widget.CardView
    lateinit var recom3 : androidx.cardview.widget.CardView
    lateinit var recom4 : androidx.cardview.widget.CardView
    lateinit var recom5 : androidx.cardview.widget.CardView


    lateinit var join1Btn : Button
    lateinit var join2Btn : Button
    lateinit var join3Btn : Button
    lateinit var join4Btn : Button
    lateinit var join5Btn : Button

    lateinit var clgText:TextView

    //레벨에 따라 다른 image 표시할 ImageView
    lateinit var level : ImageView

    //데이터 베이스 변수
    internal lateinit var db:DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //DB 객체 연결
        db = DBHelper(this)

        //아이디로 각각 연결하기
        //사용자 레벨
        level = findViewById(R.id.level)
        UserName = findViewById(R.id.levelText)

        //사용자 이름 띄우는 텍스트 뷰를 인텐트로 받은 사용자 이름을 반환
        var name = intent.getStringExtra("id")

        //id를 인텐트로 받아서 DB에 검색해서 그 정보로 객체 생성
        //사용자 유저 객체 생성 - 로그인 했을 떄 DB에 저장된 해당 회원의 정보를 반환
        var User : Person = Person()

        //Person Table에서 해당 id를 가지고 있는 사람 정보 받아오기
        User = db.DataIn(name.toString())


        UserName.setText(User.nickname + "님")


        //참여중인 챌린지
        ingChallenge1 = findViewById(R.id.challenge1)
        ingChallenge2 = findViewById(R.id.challenge2)
        ingChallenge3 = findViewById(R.id.challenge3)

        ing1_TextView = findViewById(R.id.ing1_TextView)
        ing1_Button = findViewById(R.id.ing1_Button)
        ing1Count_TextView = findViewById(R.id.ing1Count_TextView)
        ing1Date_TextView = findViewById(R.id.ing1Date_TextView)
        ingmore1_Button = findViewById(R.id.ingmore1_Button)

        ing2_TextView = findViewById(R.id.ing2_TextView)
        ing2_Button = findViewById(R.id.ing2_Button)
        ing2Count_TextView = findViewById(R.id.ing2Count_TextView)
        ing2Date_TextView = findViewById(R.id.ing2Date_TextView)
        ingmore2_Button = findViewById(R.id.ingmore2_Button)

        ing3_TextView = findViewById(R.id.ing3_TextView)
        ing3_Button = findViewById(R.id.ing3_Button)
        ing3Count_TextView = findViewById(R.id.ing3Count_TextView)
        ing3Date_TextView = findViewById(R.id.ing3Date_TextView)
        ingmore3_Button = findViewById(R.id.ingmore3_Button)

        clgText = findViewById(R.id.clgText)

        //추천하는 챌린지
        recomText = findViewById(R.id.recomText)

        join1Btn = findViewById(R.id.join1)
        join2Btn = findViewById(R.id.join2)
        join3Btn = findViewById(R.id.join3)
        join4Btn = findViewById(R.id.join4)
        join5Btn = findViewById(R.id.join5)

        recom1 = findViewById(R.id.recommend1)
        recom2 = findViewById(R.id.recommend1)
        recom3 = findViewById(R.id.recommend1)
        recom4 = findViewById(R.id.recommend4)
        recom5 = findViewById(R.id.recommend5)

        name1 = findViewById(R.id.name1)
        keyword1 = findViewById(R.id.keyword1)
        count1 = findViewById(R.id.count1)
        date1 = findViewById(R.id.Date1)

        name2 = findViewById(R.id.name2)
        keyword2 = findViewById(R.id.keyword2)
        count2 = findViewById(R.id.count2)
        date2 = findViewById(R.id.Date2)

        name3 = findViewById(R.id.name3)
        keyword3 = findViewById(R.id.keyword3)
        count3 = findViewById(R.id.count3)
        date3 = findViewById(R.id.Date3)

        name4 = findViewById(R.id.name4)
        keyword4 = findViewById(R.id.keyword4)
        count4 = findViewById(R.id.count4)
        date4 = findViewById(R.id.Date4)

        name5 = findViewById(R.id.name5)
        keyword5 = findViewById(R.id.keyword5)
        count5 = findViewById(R.id.count5)
        date5 = findViewById(R.id.Date5)



        //User가 가지고 참여하고 있는 챌린지 목록 불러오기
        var array = Array<Challenge>(4,{Challenge()})
        array = db.ChallengeIn(User)

        ingVisible(array, User)
        /*
        MarkButton1.setOnClickListener{
            db.ChallengeMark(Array[0])
            Array = db.ChallengeList(keyword, id)
            isVisible(Array)
        }
        MarkButton1.setOnClickListener{
            db.ChallengeMark(Array[0])
            Array = db.ChallengeList(keyword, id)
            isVisible(Array)
        }
        MarkButton3.setOnClickListener{
            db.ChallengeMark(Array[0])
            Array = db.ChallengeList(keyword, id)
            isVisible(Array)
        }
        MarkButton4.setOnClickListener{
            db.ChallengeMark(Array[0])
            Array = db.ChallengeList(keyword, id)
            isVisible(Array)
        }
        MarkButton5.setOnClickListener{
            db.ChallengeMark(Array[0])
            Array = db.ChallengeList(keyword, id)
            isVisible(Array)
        }
         */


        //참여중인 챌린지 - 더보기 버튼을 클릭하면 해당 챌린지에 대한 설명이 나와 있는 페이지로 이동
        ingmore1_Button.setOnClickListener  {
            var intent = Intent(this, ChallengeActivityJoin::class.java)
            intent.putExtra("id", User.id)
            intent.putExtra("ChallengeId", array[0].id.toString())
            intent.putExtra("ChallengeName", array[0].name)
            intent.putExtra("ChallengeKeyWord", array[0].keyword)
            intent.putExtra("ChallengeDate", array[0].date.toString())
            startActivity(intent)
        }

        ingmore2_Button.setOnClickListener  {
            var intent = Intent(this, ChallengeActivityJoin::class.java)
            intent.putExtra("id", User.id)
            intent.putExtra("ChallengeId", array[1].id.toString())
            intent.putExtra("ChallengeName", array[1].name)
            intent.putExtra("ChallengeKeyWord", array[1].keyword)
            intent.putExtra("ChallengeDate", array[1].date.toString())
            startActivity(intent)
        }

        ingmore3_Button.setOnClickListener  {
            var intent = Intent(this, ChallengeActivityJoin::class.java)
            intent.putExtra("id", User.id)
            intent.putExtra("ChallengeId", array[2].id.toString())
            intent.putExtra("ChallengeName", array[2].name)
            intent.putExtra("ChallengeKeyWord", array[2].keyword)
            intent.putExtra("ChallengeDate", array[2].date.toString())
            startActivity(intent)
        }

        //추천하는 챌린지 - 각 카테고리 챌린지에서 가장 먼저 등록된 요소 가지고 오기
        var categoryArray: Array<Challenge>
        categoryArray = db.Challengecategory(User)

        recomVisible(categoryArray)


        //신청하기 버튼을 누르면->사용자 DB에 진행하는 챌린지의 ID 입력
        join1Btn.setOnClickListener {
            if(db.UserjoinCount(User)>=3){
                Toast.makeText(applicationContext, "참여 가능한 챌린지 갯수를 초과하였습니다.", Toast.LENGTH_LONG).show()
            }else{
                categoryArray[0].joinUP()
                db.join(categoryArray[0], User)
                array = db.ChallengeIn(User)
                ingVisible(array, User)
            }
        }
        //신청하기 버튼을 누르면->사용자 DB에 진행하는 챌린지의 ID 입력
        join2Btn.setOnClickListener {
            if(db.UserjoinCount(User)>=3){
                Toast.makeText(applicationContext, "참여 가능한 챌린지 갯수를 초과하였습니다.", Toast.LENGTH_LONG).show()
            }else {
                categoryArray[1].joinUP()
                db.join(categoryArray[1], User)
                array = db.ChallengeIn(User)
                ingVisible(array, User)
            }
        }
        //신청하기 버튼을 누르면->사용자 DB에 진행하는 챌린지의 ID 입력
        join3Btn.setOnClickListener {
            if(db.UserjoinCount(User)>=3){
                Toast.makeText(applicationContext, "참여 가능한 챌린지 갯수를 초과하였습니다.", Toast.LENGTH_LONG).show()
            }else {
                categoryArray[2].joinUP()
                db.join(categoryArray[2], User)
                array = db.ChallengeIn(User)
                ingVisible(array, User)
            }
        }
        //신청하기 버튼을 누르면->사용자 DB에 진행하는 챌린지의 ID 입력
        join4Btn.setOnClickListener {
            if(db.UserjoinCount(User)>=3){
                Toast.makeText(applicationContext, "참여 가능한 챌린지 갯수를 초과하였습니다.", Toast.LENGTH_LONG).show()
            }else {
                categoryArray[3].joinUP()
                db.join(categoryArray[3], User)
                array = db.ChallengeIn(User)
                ingVisible(array, User)
            }
        }
        //신청하기 버튼을 누르면->사용자 DB에 진행하는 챌린지의 ID 입력
        join5Btn.setOnClickListener {
            if(db.UserjoinCount(User)>=3){
                Toast.makeText(applicationContext, "참여 가능한 챌린지 갯수를 초과하였습니다.", Toast.LENGTH_LONG).show()
            }else {
                categoryArray[4].joinUP()
                db.join(categoryArray[4], User)
                array = db.ChallengeIn(User)
                ingVisible(array, User)
            }
        }
    }

    //추천 챌린지
    private fun recomVisible(categoryArray: Array<Challenge>) {
        if(categoryArray.count() == 5)
        {
            //챌린지 갯수가 5개일 때
            recom1.setVisibility(View.VISIBLE)
            recom2.setVisibility(View.VISIBLE)
            recom3.setVisibility(View.VISIBLE)
            recom4.setVisibility(View.VISIBLE)
            recom5.setVisibility(View.VISIBLE)

            name1.setText(categoryArray[0].name)
            keyword1.setText(categoryArray[0].keyword)
            count1.setText("${db.ChallengeJoinCount(categoryArray[0])}명 참여중")
            date1.setText("${categoryArray[0].date}일 남음")

            name2.setText(categoryArray[1].name)
            keyword2.setText(categoryArray[1].keyword)
            count2.setText("${db.ChallengeJoinCount(categoryArray[1])}명 참여중")
            date2.setText("${categoryArray[1].date}일 남음")

            name3.setText(categoryArray[2].name)
            keyword3.setText(categoryArray[2].keyword)
            count3.setText("${db.ChallengeJoinCount(categoryArray[2])}명 참여중")
            date3.setText("${categoryArray[2].date}일 남음")

            name4.setText(categoryArray[3].name)
            keyword4.setText(categoryArray[3].keyword)
            count4.setText("${db.ChallengeJoinCount(categoryArray[3])}명 참여중")
            date4.setText("${categoryArray[3].date}일 남음")

            name5.setText(categoryArray[4].name)
            keyword5.setText(categoryArray[4].keyword)
            count5.setText("${db.ChallengeJoinCount(categoryArray[4])}명 참여중")
            date5.setText("${categoryArray[4].date}일 남음")
        }else if(categoryArray.count() == 4){
            //챌린지 갯수가 4개일 때
            recom1.setVisibility(View.VISIBLE)
            recom2.setVisibility(View.VISIBLE)
            recom3.setVisibility(View.VISIBLE)
            recom4.setVisibility(View.VISIBLE)
            recom5.setVisibility(View.GONE)

            name1.setText(categoryArray[0].name)
            keyword1.setText(categoryArray[0].keyword)
            count1.setText("${db.ChallengeJoinCount(categoryArray[0])}명 참여중")
            date1.setText("${categoryArray[0].date}일 남음")

            name2.setText(categoryArray[1].name)
            keyword2.setText(categoryArray[1].keyword)
            count2.setText("${db.ChallengeJoinCount(categoryArray[1])}명 참여중")
            date2.setText("${categoryArray[1].date}일 남음")

            name3.setText(categoryArray[2].name)
            keyword3.setText(categoryArray[2].keyword)
            count3.setText("${db.ChallengeJoinCount(categoryArray[2])}명 참여중")
            date3.setText("${categoryArray[2].date}일 남음")

            name4.setText(categoryArray[3].name)
            keyword4.setText(categoryArray[3].keyword)
            count4.setText("${db.ChallengeJoinCount(categoryArray[3])}명 참여중")
            date4.setText("${categoryArray[3].date}일 남음")

        }else if(categoryArray.count() == 3){
            //챌린지 갯수가 3개일 때
            recom1.setVisibility(View.VISIBLE)
            recom2.setVisibility(View.VISIBLE)
            recom3.setVisibility(View.VISIBLE)
            recom4.setVisibility(View.GONE)
            recom5.setVisibility(View.GONE)

            name1.setText(categoryArray[0].name)
            keyword1.setText(categoryArray[0].keyword)
            count1.setText("${db.ChallengeJoinCount(categoryArray[0])}명 참여중")
            date1.setText("${categoryArray[0].date}일 남음")

            name2.setText(categoryArray[1].name)
            keyword2.setText(categoryArray[1].keyword)
            count2.setText("${db.ChallengeJoinCount(categoryArray[1])}명 참여중")
            date2.setText("${categoryArray[1].date}일 남음")

            name3.setText(categoryArray[2].name)
            keyword3.setText(categoryArray[2].keyword)
            count3.setText("${db.ChallengeJoinCount(categoryArray[2])}명 참여중")
            date3.setText("${categoryArray[2].date}일 남음")

        }else if(categoryArray.count() == 2){
            //챌린지 갯수가 2개일 때
            recom1.setVisibility(View.VISIBLE)
            recom2.setVisibility(View.VISIBLE)
            recom3.setVisibility(View.GONE)
            recom4.setVisibility(View.GONE)
            recom5.setVisibility(View.GONE)

            name1.setText(categoryArray[0].name)
            keyword1.setText(categoryArray[0].keyword)
            count1.setText("${db.ChallengeJoinCount(categoryArray[0])}명 참여중")
            date1.setText("${categoryArray[0].date}일 남음")

            name2.setText(categoryArray[1].name)
            keyword2.setText(categoryArray[1].keyword)
            count2.setText("${db.ChallengeJoinCount(categoryArray[1])}명 참여중")
            date2.setText("${categoryArray[1].date}일 남음")
        }else if(categoryArray.count() == 1){
            //챌린지 갯수가 1개일 때
            recom1.setVisibility(View.VISIBLE)
            recom2.setVisibility(View.GONE)
            recom3.setVisibility(View.GONE)
            recom4.setVisibility(View.GONE)
            recom5.setVisibility(View.GONE)

            name1.setText(categoryArray[0].name)
            keyword1.setText(categoryArray[0].keyword)
            count1.setText("${db.ChallengeJoinCount(categoryArray[0])}명 참여중")
            date1.setText("${categoryArray[0].date}일 남음")
        }else{
            //총 챌린지 갯수가 0개일 때
            recomText.setVisibility(View.GONE)
            recom1.setVisibility(View.GONE)
            recom2.setVisibility(View.GONE)
            recom3.setVisibility(View.GONE)
            recom4.setVisibility(View.GONE)
            recom5.setVisibility(View.GONE)
        }
    }
    private fun ingVisible(array: Array<Challenge>, User: Person) {

        //참여중인 챌린지
        //사용자가 참여하고 있는 챌린지 정보를 받아와서 그 갯수 만큼 프레임 레이아웃 생성
        //기본은 setVisibility(View.GONE)으로 설정해두기
        //사용자가 참여하고 있는 챌린지 배열 갯수 불러오기
        var UserjoinCount = db.UserjoinCount(User)

        //count 갯수에 따라 프레임 레이아웃 보이도록 설정
        if(UserjoinCount>2)
        {
            var C1 : Challenge = array[0]
            var C2 : Challenge = array[1]
            var C3 : Challenge = array[2]

            //챌린지 갯수가 3개일 때
            clgText.setVisibility(View.VISIBLE)
            ingChallenge1.setVisibility(View.VISIBLE)
            ingChallenge2.setVisibility(View.VISIBLE)
            ingChallenge3.setVisibility(View.VISIBLE)

            ing1_TextView.setText(C1.name)
            ing1_Button.setText(C1.keyword)
            ing1Count_TextView.setText("${db.ChallengeJoinCount(C1)}명 참여중")
            ing1Date_TextView.setText("${C1.date}일 남음")

            ing2_TextView.setText(C2.name)
            ing2_Button.setText(C2.keyword)
            ing2Count_TextView.setText("${db.ChallengeJoinCount(C2)}명 참여중")
            ing2Date_TextView.setText("${C2.date}일 남음")

            ing3_TextView.setText(C3.name)
            ing3_Button.setText(C3.keyword)
            ing3Count_TextView.setText("${db.ChallengeJoinCount(C3)}명 참여중")
            ing3Date_TextView.setText("${C3.date}일 남음")

        }else if(UserjoinCount>1)
        {
            var C1 : Challenge = array[0]
            var C2 : Challenge = array[1]
            //챌린지 갯수가 2개일 때
            clgText.setVisibility(View.VISIBLE)
            ingChallenge1.setVisibility(View.VISIBLE)
            ingChallenge2.setVisibility(View.VISIBLE)
            ingChallenge3.setVisibility(View.GONE)

            ing1_TextView.setText(C1.name)
            ing1_Button.setText(C1.keyword)
            ing1Count_TextView.setText("${db.ChallengeJoinCount(C1)}명 참여중")
            ing1Date_TextView.setText("${C1.date}일 남음")

            ing2_TextView.setText(C2.name)
            ing2_Button.setText(C2.keyword)
            ing2Count_TextView.setText("${db.ChallengeJoinCount(C2)}명 참여중")
            ing2Date_TextView.setText("${C2.date}일 남음")

        }else if(UserjoinCount ==1){
            var C1 : Challenge = array[0]
            //챌린지 갯수가 1개일 때
            clgText.setVisibility(View.VISIBLE)
            ingChallenge1.setVisibility(View.VISIBLE)
            ingChallenge2.setVisibility(View.GONE)
            ingChallenge3.setVisibility(View.GONE)

            ing1_TextView.setText(C1.name)
            ing1_Button.setText(C1.keyword)
            ing1Count_TextView.setText("${db.ChallengeJoinCount(C1)}명 참여중")
            ing1Date_TextView.setText("${C1.date}일 남음")
        }
        else{
            clgText.setVisibility(View.GONE)
            ingChallenge1.setVisibility(View.GONE)
            ingChallenge2.setVisibility(View.GONE)
            ingChallenge3.setVisibility(View.GONE)
        }
    }

}


