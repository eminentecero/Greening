package com.example.greening

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ChallengeActivityNatural : AppCompatActivity() {
    // 변수 선언
    // 뒤로가기
    lateinit var imgBack : ImageView

    // 제목
    lateinit var naturalChallenge : TextView
    lateinit var expln : TextView

    // 챌린지 목록
    lateinit var challenge1 : CardView
    lateinit var challenge2 : CardView
    lateinit var challenge3 : CardView
    lateinit var challenge4 : CardView
    lateinit var challenge5 : CardView
    lateinit var challenge6 : CardView
    lateinit var challenge7 : CardView
    lateinit var challenge8 : CardView

    lateinit var NameTextView1 : TextView
    lateinit var NameTextView2 : TextView
    lateinit var NameTextView3 : TextView
    lateinit var NameTextView4 : TextView
    lateinit var NameTextView5 : TextView
    lateinit var NameTextView6 : TextView
    lateinit var NameTextView7 : TextView
    lateinit var NameTextView8 : TextView

    lateinit var KeyWordTextView1 : TextView
    lateinit var KeyWordTextView2 : TextView
    lateinit var KeyWordTextView3 : TextView
    lateinit var KeyWordTextView4 : TextView
    lateinit var KeyWordTextView5 : TextView
    lateinit var KeyWordTextView6 : TextView
    lateinit var KeyWordTextView7 : TextView
    lateinit var KeyWordTextView8 : TextView

    lateinit var JoinTextView1 : TextView
    lateinit var JoinTextView2 : TextView
    lateinit var JoinTextView3 : TextView
    lateinit var JoinTextView4 : TextView
    lateinit var JoinTextView5 : TextView
    lateinit var JoinTextView6 : TextView
    lateinit var JoinTextView7 : TextView
    lateinit var JoinTextView8 : TextView

    lateinit var DateTextView1:TextView
    lateinit var DateTextView2:TextView
    lateinit var DateTextView3:TextView
    lateinit var DateTextView4:TextView
    lateinit var DateTextView5:TextView
    lateinit var DateTextView6:TextView
    lateinit var DateTextView7:TextView
    lateinit var DateTextView8:TextView



    //데이터 베이스 변수
    internal lateinit var db:DBHelper

    // 챌린지 추가
    lateinit var addChallenge : FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge_natural_8)

        //DB 객체 연결
        db = DBHelper(this)


        var id = "swim"

        // 아이디 연결
        imgBack = findViewById(R.id.imgBack)
        naturalChallenge = findViewById(R.id.naturalChallenge)
        expln = findViewById(R.id.explnNatural)

        // 챌린지 아이디 연결
        challenge1 = findViewById(R.id.challenge1)
        challenge2 = findViewById(R.id.challenge2)
        challenge3 = findViewById(R.id.challenge3)
        challenge4 = findViewById(R.id.challenge4)
        challenge5 = findViewById(R.id.challenge5)
        challenge6 = findViewById(R.id.challenge6)
        challenge7 = findViewById(R.id.challenge7)
        challenge8 = findViewById(R.id.challenge8)

        NameTextView1 = findViewById(R.id.NameTextView1)
        NameTextView2 = findViewById(R.id.NameTextView2)
        NameTextView3 = findViewById(R.id.NameTextView3)
        NameTextView4 = findViewById(R.id.NameTextView4)
        NameTextView5 = findViewById(R.id.NameTextView5)
        NameTextView6 = findViewById(R.id.NameTextView6)
        NameTextView7 = findViewById(R.id.NameTextView7)
        NameTextView8 = findViewById(R.id.NameTextView8)

        KeyWordTextView1= findViewById(R.id.KeyWordTextView1)
        KeyWordTextView2= findViewById(R.id.KeyWordTextView2)
        KeyWordTextView3= findViewById(R.id.KeyWordTextView3)
        KeyWordTextView4= findViewById(R.id.KeyWordTextView4)
        KeyWordTextView5= findViewById(R.id.KeyWordTextView5)
        KeyWordTextView6= findViewById(R.id.KeyWordTextView6)
        KeyWordTextView7= findViewById(R.id.KeyWordTextView7)
        KeyWordTextView8= findViewById(R.id.KeyWordTextView8)

        JoinTextView1 = findViewById(R.id.JoinTextView1)
        JoinTextView2 = findViewById(R.id.JoinTextView2)
        JoinTextView3 = findViewById(R.id.JoinTextView3)
        JoinTextView4 = findViewById(R.id.JoinTextView4)
        JoinTextView5 = findViewById(R.id.JoinTextView5)
        JoinTextView6 = findViewById(R.id.JoinTextView6)
        JoinTextView7 = findViewById(R.id.JoinTextView7)
        JoinTextView8 = findViewById(R.id.JoinTextView8)

        DateTextView1 = findViewById(R.id.DateTextView1)
        DateTextView2 = findViewById(R.id.DateTextView2)
        DateTextView3 = findViewById(R.id.DateTextView3)
        DateTextView4 = findViewById(R.id.DateTextView4)
        DateTextView5 = findViewById(R.id.DateTextView5)
        DateTextView6 = findViewById(R.id.DateTextView6)
        DateTextView7 = findViewById(R.id.DateTextView7)
        DateTextView8 = findViewById(R.id.DateTextView8)

        // 플러팅 버튼 - 챌린지 작성하기
        addChallenge = findViewById(R.id.addChallenge)

        var keyword = "자원"

        //var keyword = intent.getStringExtra("keyword").toString()
        //var id = intent.getStringExtra("id").toString()

        var Array: Array<Challenge>
        Array = db.ChallengeList(keyword, id)
        Log.d("챌린지 ","${Array[0].name}+${Array[0].StartDate}+${Array[0].LastDate}")
        isVisible(Array)

        imgBack.setOnClickListener {
            var intent = Intent(this, ChallengeActivitySelect::class.java)
            startActivity(intent)
        }

        challenge1.setOnClickListener {
            //var intent = Intent(this, ChallengeActivityNojoin::class.java)
            //startActivity(intent)
        }
    }

    fun isVisible(Array:Array<Challenge>)
    {
        if(Array.size>7)
        {
            //챌린지 갯수가 8개일 때
            challenge1.setVisibility(View.VISIBLE)
            challenge2.setVisibility(View.VISIBLE)
            challenge3.setVisibility(View.VISIBLE)
            challenge4.setVisibility(View.VISIBLE)
            challenge5.setVisibility(View.VISIBLE)
            challenge6.setVisibility(View.VISIBLE)
            challenge7.setVisibility(View.VISIBLE)
            challenge8.setVisibility(View.VISIBLE)

            if(Array[0].State == 0){
                NameTextView1.setText(Array[0].name+" - 참여중")
            }else if(Array[0].State == 1){
                NameTextView1.setText(Array[0].name+" - 참여완료")
            }else{
                NameTextView1.setText(Array[0].name)
            }
            KeyWordTextView1.setText(Array[0].ShortSummary())
            JoinTextView1.setText("${db.ChallengeJoinCount(Array[0])}")
            DateTextView1.setText("${Array[0].StartDate} - ${Array[0].LastDate}")

            if(Array[1].State == 0){
                NameTextView2.setText(Array[1].name+" - 참여중")
            }else if(Array[1].State == 1){
                NameTextView2.setText(Array[1].name+" - 참여완료")
            }else{
                NameTextView2.setText(Array[1].name)
            }
            KeyWordTextView2.setText(Array[1].ShortSummary())
            JoinTextView2.setText("${db.ChallengeJoinCount(Array[1])}")
            DateTextView2.setText("${Array[1].StartDate} - ${Array[1].LastDate}")

            if(Array[2].State == 0){
                NameTextView3.setText(Array[2].name+" - 참여중")
            }else if(Array[2].State == 1){
                NameTextView3.setText(Array[2].name+" - 참여완료")
            }else{
                NameTextView3.setText(Array[2].name)
            }
            KeyWordTextView3.setText(Array[2].ShortSummary())
            JoinTextView3.setText("${db.ChallengeJoinCount(Array[2])}")
            DateTextView3.setText("${Array[2].StartDate} - ${Array[2].LastDate}")

            if(Array[3].State == 0){
                NameTextView4.setText(Array[3].name+" - 참여중")
            }else if(Array[3].State == 1){
                NameTextView4.setText(Array[3].name+" - 참여완료")
            }else{
                NameTextView4.setText(Array[3].name)
            }
            KeyWordTextView4.setText(Array[3].ShortSummary())
            JoinTextView4.setText("${db.ChallengeJoinCount(Array[3])}")
            DateTextView4.setText("${Array[3].StartDate} - ${Array[3].LastDate}")

            if(Array[4].State == 0){
                NameTextView5.setText(Array[4].name+" - 참여중")
            }else if(Array[4].State == 1){
                NameTextView5.setText(Array[4].name+" - 참여완료")
            }else{
                NameTextView5.setText(Array[4].name)
            }
            KeyWordTextView5.setText(Array[4].ShortSummary())
            JoinTextView5.setText("${db.ChallengeJoinCount(Array[4])}")
            DateTextView5.setText("${Array[4].StartDate} - ${Array[4].LastDate}")


            if(Array[5].State == 0){
                NameTextView6.setText(Array[0].name+" - 참여중")
            }else if(Array[5].State == 1){
                NameTextView6.setText(Array[5].name+" - 참여완료")
            }else{
                NameTextView6.setText(Array[5].name)
            }
            KeyWordTextView6.setText(Array[5].ShortSummary())
            JoinTextView6.setText("${db.ChallengeJoinCount(Array[5])}")
            DateTextView6.setText("${Array[5].StartDate} - ${Array[5].LastDate}")

            if(Array[6].State == 0){
                NameTextView7.setText(Array[6].name+" - 참여중")
            }else if(Array[6].State == 1){
                NameTextView7.setText(Array[6].name+" - 참여완료")
            }else{
                NameTextView7.setText(Array[6].name)
            }
            KeyWordTextView7.setText(Array[6].ShortSummary())
            JoinTextView7.setText("${db.ChallengeJoinCount(Array[6])}")
            DateTextView7.setText("${Array[6].StartDate} - ${Array[6].LastDate}")

            if(Array[7].State == 0){
                NameTextView8.setText(Array[7].name+" - 참여중")
            }else if(Array[7].State == 1){
                NameTextView8.setText(Array[7].name+" - 참여완료")
            }else{
                NameTextView8.setText(Array[7].name)
            }
            KeyWordTextView8.setText(Array[7].ShortSummary())
            JoinTextView8.setText("${db.ChallengeJoinCount(Array[7])}")
            DateTextView8.setText("${Array[7].StartDate} - ${Array[7].LastDate}")
        }else if(Array.size>6){
            //챌린지 갯수가 7개일 때
            challenge1.setVisibility(View.VISIBLE)
            challenge2.setVisibility(View.VISIBLE)
            challenge3.setVisibility(View.VISIBLE)
            challenge4.setVisibility(View.VISIBLE)
            challenge5.setVisibility(View.VISIBLE)
            challenge6.setVisibility(View.VISIBLE)
            challenge7.setVisibility(View.VISIBLE)
            challenge8.setVisibility(View.GONE)

            if(Array[0].State == 0){
                NameTextView1.setText(Array[0].name+" - 참여중")
            }else if(Array[0].State == 1){
                NameTextView1.setText(Array[0].name+" - 참여완료")
            }else{
                NameTextView1.setText(Array[0].name)
            }
            KeyWordTextView1.setText(Array[0].ShortSummary())
            JoinTextView1.setText("${db.ChallengeJoinCount(Array[0])}")
            DateTextView1.setText("${Array[0].StartDate} - ${Array[0].LastDate}")

            if(Array[1].State == 0){
                NameTextView2.setText(Array[1].name+" - 참여중")
            }else if(Array[1].State == 1){
                NameTextView2.setText(Array[1].name+" - 참여완료")
            }else{
                NameTextView2.setText(Array[1].name)
            }
            KeyWordTextView2.setText(Array[1].ShortSummary())
            JoinTextView2.setText("${db.ChallengeJoinCount(Array[1])}")
            DateTextView2.setText("${Array[1].StartDate} - ${Array[1].LastDate}")

            if(Array[2].State == 0){
                NameTextView3.setText(Array[2].name+" - 참여중")
            }else if(Array[2].State == 1){
                NameTextView3.setText(Array[2].name+" - 참여완료")
            }else{
                NameTextView3.setText(Array[2].name)
            }
            KeyWordTextView3.setText(Array[2].ShortSummary())
            JoinTextView3.setText("${db.ChallengeJoinCount(Array[2])}")
            DateTextView3.setText("${Array[2].StartDate} - ${Array[2].LastDate}")

            if(Array[3].State == 0){
                NameTextView4.setText(Array[3].name+" - 참여중")
            }else if(Array[3].State == 1){
                NameTextView4.setText(Array[3].name+" - 참여완료")
            }else{
                NameTextView4.setText(Array[3].name)
            }
            KeyWordTextView4.setText(Array[3].ShortSummary())
            JoinTextView4.setText("${db.ChallengeJoinCount(Array[3])}")
            DateTextView4.setText("${Array[3].StartDate} - ${Array[3].LastDate}")

            if(Array[4].State == 0){
                NameTextView5.setText(Array[4].name+" - 참여중")
            }else if(Array[4].State == 1){
                NameTextView5.setText(Array[4].name+" - 참여완료")
            }else{
                NameTextView5.setText(Array[4].name)
            }
            KeyWordTextView5.setText(Array[4].ShortSummary())
            JoinTextView5.setText("${db.ChallengeJoinCount(Array[4])}")
            DateTextView5.setText("${Array[4].StartDate} - ${Array[4].LastDate}")


            if(Array[5].State == 0){
                NameTextView6.setText(Array[0].name+" - 참여중")
            }else if(Array[5].State == 1){
                NameTextView6.setText(Array[5].name+" - 참여완료")
            }else{
                NameTextView6.setText(Array[5].name)
            }
            KeyWordTextView6.setText(Array[5].ShortSummary())
            JoinTextView6.setText("${db.ChallengeJoinCount(Array[5])}")
            DateTextView6.setText("${Array[5].StartDate} - ${Array[5].LastDate}")

            if(Array[6].State == 0){
                NameTextView7.setText(Array[6].name+" - 참여중")
            }else if(Array[6].State == 1){
                NameTextView7.setText(Array[6].name+" - 참여완료")
            }else{
                NameTextView7.setText(Array[6].name)
            }
            KeyWordTextView7.setText(Array[6].ShortSummary())
            JoinTextView7.setText("${db.ChallengeJoinCount(Array[6])}")
            DateTextView7.setText("${Array[6].StartDate} - ${Array[6].LastDate}")

        }else if(Array.size>5){
            //챌린지 갯수가 6개일 때
            challenge1.setVisibility(View.VISIBLE)
            challenge2.setVisibility(View.VISIBLE)
            challenge3.setVisibility(View.VISIBLE)
            challenge4.setVisibility(View.VISIBLE)
            challenge5.setVisibility(View.VISIBLE)
            challenge6.setVisibility(View.VISIBLE)
            challenge7.setVisibility(View.GONE)
            challenge8.setVisibility(View.GONE)

            if(Array[0].State == 0){
                NameTextView1.setText(Array[0].name+" - 참여중")
            }else if(Array[0].State == 1){
                NameTextView1.setText(Array[0].name+" - 참여완료")
            }else{
                NameTextView1.setText(Array[0].name)
            }
            KeyWordTextView1.setText(Array[0].ShortSummary())
            JoinTextView1.setText("${db.ChallengeJoinCount(Array[0])}")
            DateTextView1.setText("${Array[0].StartDate} - ${Array[0].LastDate}")

            if(Array[1].State == 0){
                NameTextView2.setText(Array[1].name+" - 참여중")
            }else if(Array[1].State == 1){
                NameTextView2.setText(Array[1].name+" - 참여완료")
            }else{
                NameTextView2.setText(Array[1].name)
            }
            KeyWordTextView2.setText(Array[1].ShortSummary())
            JoinTextView2.setText("${db.ChallengeJoinCount(Array[1])}")
            DateTextView2.setText("${Array[1].StartDate} - ${Array[1].LastDate}")

            if(Array[2].State == 0){
                NameTextView3.setText(Array[2].name+" - 참여중")
            }else if(Array[2].State == 1){
                NameTextView3.setText(Array[2].name+" - 참여완료")
            }else{
                NameTextView3.setText(Array[2].name)
            }
            KeyWordTextView3.setText(Array[2].ShortSummary())
            JoinTextView3.setText("${db.ChallengeJoinCount(Array[2])}")
            DateTextView3.setText("${Array[2].StartDate} - ${Array[2].LastDate}")

            if(Array[3].State == 0){
                NameTextView4.setText(Array[3].name+" - 참여중")
            }else if(Array[3].State == 1){
                NameTextView4.setText(Array[3].name+" - 참여완료")
            }else{
                NameTextView4.setText(Array[3].name)
            }
            KeyWordTextView4.setText(Array[3].ShortSummary())
            JoinTextView4.setText("${db.ChallengeJoinCount(Array[3])}")
            DateTextView4.setText("${Array[3].StartDate} - ${Array[3].LastDate}")

            if(Array[4].State == 0){
                NameTextView5.setText(Array[4].name+" - 참여중")
            }else if(Array[4].State == 1){
                NameTextView5.setText(Array[4].name+" - 참여완료")
            }else{
                NameTextView5.setText(Array[4].name)
            }
            KeyWordTextView5.setText(Array[4].ShortSummary())
            JoinTextView5.setText("${db.ChallengeJoinCount(Array[4])}")
            DateTextView5.setText("${Array[4].StartDate} - ${Array[4].LastDate}")


            if(Array[5].State == 0){
                NameTextView6.setText(Array[0].name+" - 참여중")
            }else if(Array[5].State == 1){
                NameTextView6.setText(Array[5].name+" - 참여완료")
            }else{
                NameTextView6.setText(Array[5].name)
            }
            KeyWordTextView6.setText(Array[5].ShortSummary())
            JoinTextView6.setText("${db.ChallengeJoinCount(Array[5])}")
            DateTextView6.setText("${Array[5].StartDate} - ${Array[5].LastDate}")

        }else if(Array.size>4){
            //챌린지 갯수가 5개일 때
            challenge1.setVisibility(View.VISIBLE)
            challenge2.setVisibility(View.VISIBLE)
            challenge3.setVisibility(View.VISIBLE)
            challenge4.setVisibility(View.VISIBLE)
            challenge5.setVisibility(View.VISIBLE)
            challenge6.setVisibility(View.GONE)
            challenge7.setVisibility(View.GONE)
            challenge8.setVisibility(View.GONE)

            if(Array[0].State == 0){
                NameTextView1.setText(Array[0].name+" - 참여중")
            }else if(Array[0].State == 1){
                NameTextView1.setText(Array[0].name+" - 참여완료")
            }else{
                NameTextView1.setText(Array[0].name)
            }
            KeyWordTextView1.setText(Array[0].ShortSummary())
            JoinTextView1.setText("${db.ChallengeJoinCount(Array[0])}")
            DateTextView1.setText("${Array[0].StartDate} - ${Array[0].LastDate}")

            if(Array[1].State == 0){
                NameTextView2.setText(Array[1].name+" - 참여중")
            }else if(Array[1].State == 1){
                NameTextView2.setText(Array[1].name+" - 참여완료")
            }else{
                NameTextView2.setText(Array[1].name)
            }
            KeyWordTextView2.setText(Array[1].ShortSummary())
            JoinTextView2.setText("${db.ChallengeJoinCount(Array[1])}")
            DateTextView2.setText("${Array[1].StartDate} - ${Array[1].LastDate}")

            if(Array[2].State == 0){
                NameTextView3.setText(Array[2].name+" - 참여중")
            }else if(Array[2].State == 1){
                NameTextView3.setText(Array[2].name+" - 참여완료")
            }else{
                NameTextView3.setText(Array[2].name)
            }
            KeyWordTextView3.setText(Array[2].ShortSummary())
            JoinTextView3.setText("${db.ChallengeJoinCount(Array[2])}")
            DateTextView3.setText("${Array[2].StartDate} - ${Array[2].LastDate}")

            if(Array[3].State == 0){
                NameTextView4.setText(Array[3].name+" - 참여중")
            }else if(Array[3].State == 1){
                NameTextView4.setText(Array[3].name+" - 참여완료")
            }else{
                NameTextView4.setText(Array[3].name)
            }
            KeyWordTextView4.setText(Array[3].ShortSummary())
            JoinTextView4.setText("${db.ChallengeJoinCount(Array[3])}")
            DateTextView4.setText("${Array[3].StartDate} - ${Array[3].LastDate}")

            if(Array[4].State == 0){
                NameTextView5.setText(Array[4].name+" - 참여중")
            }else if(Array[4].State == 1){
                NameTextView5.setText(Array[4].name+" - 참여완료")
            }else{
                NameTextView5.setText(Array[4].name)
            }
            KeyWordTextView5.setText(Array[4].ShortSummary())
            JoinTextView5.setText("${db.ChallengeJoinCount(Array[4])}")
            DateTextView5.setText("${Array[4].StartDate} - ${Array[4].LastDate}")

        }else if(Array.size>3){
            //챌린지 갯수가 4개일 때
            challenge1.setVisibility(View.VISIBLE)
            challenge2.setVisibility(View.VISIBLE)
            challenge3.setVisibility(View.VISIBLE)
            challenge4.setVisibility(View.VISIBLE)
            challenge5.setVisibility(View.GONE)
            challenge6.setVisibility(View.GONE)
            challenge7.setVisibility(View.GONE)
            challenge8.setVisibility(View.GONE)

            if (Array[0].State == 0) {
                NameTextView1.setText(Array[0].name + " - 참여중")
            }else if(Array[0].State == 1){
                NameTextView1.setText(Array[0].name+" - 참여완료")
            }else{
                NameTextView1.setText(Array[0].name)
            }
            KeyWordTextView1.setText(Array[0].ShortSummary())
            JoinTextView1.setText("${db.ChallengeJoinCount(Array[0])}")
            DateTextView1.setText("${Array[0].StartDate} - ${Array[0].LastDate}")

            if(Array[1].State == 0){
                NameTextView2.setText(Array[1].name+" - 참여중")
            }else if(Array[1].State == 1){
                NameTextView2.setText(Array[1].name+" - 참여완료")
            }else{
                NameTextView2.setText(Array[1].name)
            }
            KeyWordTextView2.setText(Array[1].ShortSummary())
            JoinTextView2.setText("${db.ChallengeJoinCount(Array[1])}")
            DateTextView2.setText("${Array[1].StartDate} - ${Array[1].LastDate}")

            if(Array[2].State == 0){
                NameTextView3.setText(Array[2].name+" - 참여중")
            }else if(Array[2].State == 1){
                NameTextView3.setText(Array[2].name+" - 참여완료")
            }else{
                NameTextView3.setText(Array[2].name)
            }
            KeyWordTextView3.setText(Array[2].ShortSummary())
            JoinTextView3.setText("${db.ChallengeJoinCount(Array[2])}")
            DateTextView3.setText("${Array[2].StartDate} - ${Array[2].LastDate}")

            if(Array[3].State == 0){
                NameTextView4.setText(Array[3].name+" - 참여중")
            }else if(Array[3].State == 1){
                NameTextView4.setText(Array[3].name+" - 참여완료")
            }else{
                NameTextView4.setText(Array[3].name)
            }
            KeyWordTextView4.setText(Array[3].ShortSummary())
            JoinTextView4.setText("${db.ChallengeJoinCount(Array[3])}")
            DateTextView4.setText("${Array[3].StartDate} - ${Array[3].LastDate}")

        }else if(Array.size>2){
            //챌린지 갯수가 3개일 때
            challenge1.setVisibility(View.VISIBLE)
            challenge2.setVisibility(View.VISIBLE)
            challenge3.setVisibility(View.VISIBLE)
            challenge4.setVisibility(View.GONE)
            challenge5.setVisibility(View.GONE)
            challenge6.setVisibility(View.GONE)
            challenge7.setVisibility(View.GONE)
            challenge8.setVisibility(View.GONE)

            if (Array[0].State == 0) {
                NameTextView1.setText(Array[0].name + " - 참여중")
            }else if(Array[0].State == 1){
                NameTextView1.setText(Array[0].name+" - 참여완료")
            }else{
                NameTextView1.setText(Array[0].name)
            }
            KeyWordTextView1.setText(Array[0].ShortSummary())
            JoinTextView1.setText("${db.ChallengeJoinCount(Array[0])}")
            DateTextView1.setText("${Array[0].StartDate} - ${Array[0].LastDate}")

            if(Array[1].State == 0){
                NameTextView2.setText(Array[1].name+" - 참여중")
            }else if(Array[1].State == 1){
                NameTextView2.setText(Array[1].name+" - 참여완료")
            }else{
                NameTextView2.setText(Array[1].name)
            }
            KeyWordTextView2.setText(Array[1].ShortSummary())
            JoinTextView2.setText("${db.ChallengeJoinCount(Array[1])}")
            DateTextView2.setText("${Array[1].StartDate} - ${Array[1].LastDate}")

            if(Array[2].State == 0){
                NameTextView3.setText(Array[2].name+" - 참여중")
            }else if(Array[2].State == 1){
                NameTextView3.setText(Array[2].name+" - 참여완료")
            }else{
                NameTextView3.setText(Array[2].name)
            }
            KeyWordTextView3.setText(Array[2].ShortSummary())
            JoinTextView3.setText("${db.ChallengeJoinCount(Array[2])}")
            DateTextView3.setText("${Array[2].StartDate} - ${Array[2].LastDate}")

        }else if(Array.size>1){
            //챌린지 갯수가 2개일 때
            challenge1.setVisibility(View.VISIBLE)
            challenge2.setVisibility(View.VISIBLE)
            challenge3.setVisibility(View.GONE)
            challenge4.setVisibility(View.GONE)
            challenge5.setVisibility(View.GONE)
            challenge6.setVisibility(View.GONE)
            challenge7.setVisibility(View.GONE)
            challenge8.setVisibility(View.GONE)

            if (Array[0].State == 0) {
                NameTextView1.setText(Array[0].name + " - 참여중")
            }else if(Array[0].State == 1){
                NameTextView1.setText(Array[0].name+" - 참여완료")
            }else{
                NameTextView1.setText(Array[0].name)
            }
            KeyWordTextView1.setText(Array[0].ShortSummary())
            JoinTextView1.setText("${db.ChallengeJoinCount(Array[0])}")
            DateTextView1.setText("${Array[0].StartDate} - ${Array[0].LastDate}")

            if(Array[1].State == 0){
                NameTextView2.setText(Array[1].name+" - 참여중")
            }else if(Array[1].State == 1){
                NameTextView2.setText(Array[1].name+" - 참여완료")
            }else{
                NameTextView2.setText(Array[1].name)
            }
            KeyWordTextView2.setText(Array[1].ShortSummary())
            JoinTextView2.setText("${db.ChallengeJoinCount(Array[1])}")
            DateTextView2.setText("${Array[1].StartDate} - ${Array[1].LastDate}")


        }else if(Array.size>0)
        {
            //챌린지 갯수가 1개일 때
            challenge1.setVisibility(View.VISIBLE)
            challenge2.setVisibility(View.GONE)
            challenge3.setVisibility(View.GONE)
            challenge4.setVisibility(View.GONE)
            challenge5.setVisibility(View.GONE)
            challenge6.setVisibility(View.GONE)
            challenge7.setVisibility(View.GONE)
            challenge8.setVisibility(View.GONE)

            if (Array[0].State == 0) {
                NameTextView1.setText(Array[0].name + " - 참여중")
            }else if(Array[0].State == 1){
                NameTextView1.setText(Array[0].name+" - 참여완료")
            }else{
                NameTextView1.setText(Array[0].name)
            }
            KeyWordTextView1.setText(Array[0].ShortSummary())
            JoinTextView1.setText("${db.ChallengeJoinCount(Array[0])}")
            DateTextView1.setText("${Array[0].StartDate} - ${Array[0].LastDate}")
        }else{
            //챌린지 갯수가 0개일 때
            challenge1.setVisibility(View.GONE)
            challenge2.setVisibility(View.GONE)
            challenge3.setVisibility(View.GONE)
            challenge4.setVisibility(View.GONE)
            challenge5.setVisibility(View.GONE)
            challenge6.setVisibility(View.GONE)
            challenge7.setVisibility(View.GONE)
            challenge8.setVisibility(View.GONE)
        }
    }
}