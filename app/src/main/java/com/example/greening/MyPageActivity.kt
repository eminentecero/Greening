package com.example.greening

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MyPageActivity  : AppCompatActivity() {
    //사용할 변수
    lateinit var UserName:TextView

    lateinit var ingChallenge1 : androidx.cardview.widget.CardView
    lateinit var ingChallenge2 : androidx.cardview.widget.CardView
    lateinit var ingChallenge3 : androidx.cardview.widget.CardView

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

    //관심 챌린지
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

    lateinit var recom1 : androidx.cardview.widget.CardView
    lateinit var recom2 : androidx.cardview.widget.CardView
    lateinit var recom3 : androidx.cardview.widget.CardView

    lateinit var join1Btn : Button
    lateinit var join2Btn : Button
    lateinit var join3Btn : Button

    lateinit var allCount:TextView
    lateinit var exerciseCount: TextView
    lateinit var naturalCount: TextView
    lateinit var foodCount:TextView
    lateinit var plasticCount : TextView
    lateinit var etcCount:TextView

    lateinit var clgText:TextView

    lateinit var finishAll:androidx.cardview.widget.CardView
    lateinit var finishFood:androidx.cardview.widget.CardView
    lateinit var finishResource:androidx.cardview.widget.CardView
    lateinit var finishPlastic:androidx.cardview.widget.CardView
    lateinit var finishExercise:androidx.cardview.widget.CardView
    lateinit var finishEtc:androidx.cardview.widget.CardView

    //데이터 베이스 변수
    internal lateinit var db:DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)

        //DB 객체 연결
        db = DBHelper(this)

        //아이디로 각각 연결하기
        //사용자 레벨
        UserName = findViewById(R.id.UserName)

        //사용자 이름 띄우는 텍스트 뷰를 인텐트로 받은 사용자 이름을 반환
        //var name = intent.getStringExtra("id")
        var name = "swim"
        UserName.setText(name + "님")

        //id를 인텐트로 받아서 DB에 검색해서 그 정보로 객체 생성
        //사용자 유저 객체 생성 - 로그인 했을 떄 DB에 저장된 해당 회원의 정보를 반환
        var User : Person = Person()

        //Person Table에서 해당 id를 가지고 있는 사람 정보 받아오기
        User = db.DataIn(name.toString())

        //참여중인 챌린지
        ingChallenge1 = findViewById(R.id.ongoing1)
        ingChallenge2 = findViewById(R.id.ongoing2)
        ingChallenge3 = findViewById(R.id.ongoing3)

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

        recom1 = findViewById(R.id.recom1)
        recom2 = findViewById(R.id.recom2)
        recom3 = findViewById(R.id.recom3)

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

        allCount = findViewById(R.id.allCount)
        plasticCount = findViewById(R.id.plasticCount)
        exerciseCount = findViewById(R.id.exerciseCount)
        etcCount = findViewById(R.id.etcCount)
        naturalCount = findViewById(R.id.naturalCount)
        foodCount = findViewById(R.id.foodCount)

        finishAll = findViewById(R.id.finishAll)
        finishEtc= findViewById(R.id.finishEtc)
        finishFood= findViewById(R.id.finishFood)
        finishPlastic= findViewById(R.id.finishPlastic)
        finishResource= findViewById(R.id.finishResource)
        finishExercise= findViewById(R.id.finishExercise)

        //User가 가지고 참여하고 있는 챌린지 목록 불러오기
        var array = Array<Challenge>(4,{Challenge()})
        array = db.ChallengeIn(User)

        ingVisible(array, User)

        allCount.setText("${db.ChallengeTotalCount()}개")
        naturalCount.setText("${db.CategoryCount("자원")}개")
        foodCount.setText("${db.CategoryCount("음식")}개")
        exerciseCount.setText("${db.CategoryCount("운동")}개")
        plasticCount.setText("${db.CategoryCount("플라스틱")}개")
        etcCount.setText("${db.CategoryCount("기타")}개")

        //더보기 버튼을 클릭하면 해당 챌린지에 대한 설명이 나와 있는 페이지로 이동
        ingmore1_Button.setOnClickListener  {
            var intent = Intent(this, ChallengeActivityJoin::class.java)
            intent.putExtra("id", User.id)
            intent.putExtra("ChallengeId", array[0].id)
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
        categoryArray = db.Challengecategory()

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
                categoryArray[2].count += 1
                db.join(categoryArray[2], User)
                array = db.ChallengeIn(User)
                ingVisible(array, User)
            }
        }
    }

    //관심 챌린지
    private fun recomVisible(categoryArray: Array<Challenge>) {
        if(categoryArray.count() == 3)
        {
            //챌린지 갯수가 3개일 때
            recom1.setVisibility(View.VISIBLE)
            recom2.setVisibility(View.VISIBLE)
            recom3.setVisibility(View.VISIBLE)

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

            name1.setText(categoryArray[0].name)
            keyword1.setText(categoryArray[0].keyword)
            count1.setText("${db.ChallengeJoinCount(categoryArray[0])}명 참여중")
            date1.setText("${categoryArray[0].date}일 남음")

        }else
            //챌린지 갯수가 0개일 때
            recom1.setVisibility(View.GONE)
            recom2.setVisibility(View.GONE)
            recom3.setVisibility(View.GONE)
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
            //챌린지 갯수가 3개일 때
            clgText.setVisibility(View.VISIBLE)
            ingChallenge1.setVisibility(View.VISIBLE)
            ingChallenge2.setVisibility(View.VISIBLE)
            ingChallenge3.setVisibility(View.VISIBLE)

            ing1_TextView.setText(array[0].name)
            ing1_Button.setText(array[0].keyword)
            ing1Count_TextView.setText("${db.ChallengeJoinCount(array[0])}명 참여중")
            ing1Date_TextView.setText("${array[0].date}일 남음")

            ing2_TextView.setText(array[1].name)
            ing2_Button.setText(array[1].keyword)
            ing2Count_TextView.setText("${db.ChallengeJoinCount(array[1])}명 참여중")
            ing2Date_TextView.setText("${array[1].date}일 남음")

            ing3_TextView.setText(array[2].name)
            ing3_Button.setText(array[2].keyword)
            ing3Count_TextView.setText("${db.ChallengeJoinCount(array[2])}명 참여중")
            ing3Date_TextView.setText("${array[2].date}일 남음")

        }else if(UserjoinCount>1)
        {
            //챌린지 갯수가 2개일 때
            clgText.setVisibility(View.VISIBLE)
            ingChallenge1.setVisibility(View.VISIBLE)
            ingChallenge2.setVisibility(View.VISIBLE)
            ingChallenge3.setVisibility(View.GONE)

            ing1_TextView.setText(array[0].name)
            ing1_Button.setText(array[0].keyword)
            ing1Count_TextView.setText("${db.ChallengeJoinCount(array[0])}명 참여중")
            ing1Date_TextView.setText("${array[0].date}일 남음")

            ing2_TextView.setText(array[1].name)
            ing2_Button.setText(array[1].keyword)
            ing2Count_TextView.setText("${db.ChallengeJoinCount(array[1])}명 참여중")
            ing2Date_TextView.setText("${array[1].date}일 남음")

        }else if(UserjoinCount ==1){
            //챌린지 갯수가 1개일 때
            clgText.setVisibility(View.VISIBLE)
            ingChallenge1.setVisibility(View.VISIBLE)
            ingChallenge2.setVisibility(View.GONE)
            ingChallenge3.setVisibility(View.GONE)

            ing1_TextView.setText(array[0].name)
            ing1_Button.setText(array[0].keyword)
            ing1Count_TextView.setText("${db.ChallengeJoinCount(array[0])}명 참여중")
            ing1Date_TextView.setText("${array[0].date}일 남음")
        }
        else{
            clgText.setVisibility(View.GONE)
            ingChallenge1.setVisibility(View.GONE)
            ingChallenge2.setVisibility(View.GONE)
            ingChallenge3.setVisibility(View.GONE)
        }

        finishAll.setOnClickListener {
            // 인텐트 값으로 화면 전환
            var intent = Intent(this, ChallengeListDone::class.java)
            intent.putExtra("keyword", "all")
            intent.putExtra("id", User.id)
            startActivity(intent)
        }
        finishResource.setOnClickListener {
            // 인텐트 값으로 화면 전환
            var intent = Intent(this, ChallengeListDone::class.java)
            intent.putExtra("keyword", "자원")
            intent.putExtra("id", User.id)
            startActivity(intent)
        }
        finishFood.setOnClickListener {
            // 인텐트 값으로 화면 전환
            var intent = Intent(this, ChallengeListDone::class.java)
            intent.putExtra("keyword", "음식")
            intent.putExtra("id", User.id)
            startActivity(intent)
        }
        finishExercise.setOnClickListener {
            // 인텐트 값으로 화면 전환
            var intent = Intent(this, ChallengeListDone::class.java)
            intent.putExtra("keyword", "운동")
            intent.putExtra("id", User.id)
            startActivity(intent)
        }
        finishPlastic.setOnClickListener {
            // 인텐트 값으로 화면 전환
            var intent = Intent(this, ChallengeListDone::class.java)
            intent.putExtra("keyword", "플라스틱")
            intent.putExtra("id", User.id)
            startActivity(intent)
        }
        finishEtc.setOnClickListener {
            // 인텐트 값으로 화면 전환
            var intent = Intent(this, ChallengeListDone::class.java)
            intent.putExtra("keyword", "기타")
            intent.putExtra("id", User.id)
            startActivity(intent)
        }
    }

}