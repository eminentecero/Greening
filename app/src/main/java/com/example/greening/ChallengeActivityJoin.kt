package com.example.greening

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import sun.bob.mcalendarview.MarkStyle
import sun.bob.mcalendarview.listeners.OnDateClickListener
import sun.bob.mcalendarview.vo.DateData



class ChallengeActivityJoin : AppCompatActivity() {

    // 변수 선언
    lateinit var imgBack : ImageView

    // 챌린지 정보
    lateinit var nameChallenge : TextView
    lateinit var numPeopleChallenge : TextView
    lateinit var periodChallenge : TextView

    // 프로그레스 바
    lateinit var progressChallenge : com.dinuscxj.progressbar.CircleProgressBar

    // 달력
    lateinit var calendar_view : sun.bob.mcalendarview.MCalendarView

    // 확인 버튼
    lateinit var btnDoneChallenge : Button

    //데이터 베이스 변수
    internal lateinit var db:DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge_join_4)

        //DB 객체 연결
        db = DBHelper(this)

        // 아이디 연결
        imgBack = findViewById(R.id.imgBack)
        nameChallenge = findViewById(R.id.nameChallenge)
        numPeopleChallenge = findViewById(R.id.numPeopleChallenge)
        periodChallenge = findViewById(R.id.periodChallenge)
        progressChallenge = findViewById(R.id.progressChallenge)
        calendar_view = findViewById(R.id.calendarChallenge)
        btnDoneChallenge = findViewById(R.id.btnDoneChallenge)

        var UserId: String? = intent.getStringExtra("id")

        Toast.makeText(applicationContext, "${UserId}님 반갑습니다!", Toast.LENGTH_LONG).show()

        //사용자 유저 객체 생성 - 로그인 했을 떄 DB에 저장된 해당 회원의 정보를 반환
        var User : Person = Person()

        //Person Table에서 해당 id를 가지고 있는 사람 정보 받아오기
        User = db.DataIn(UserId.toString())

        var Id = intent.getIntExtra("ChallengeId",0)

        var challenge : Challenge = db.Challengereturn(Id)
        nameChallenge.setText(challenge.name)
        numPeopleChallenge.setText(db.ChallengeJoinCount(challenge))
        periodChallenge.setText("${challenge.StartDate} - ${challenge.LastDate}")

        var IngPercent = db.UserDay(challenge.id, User)


        progressChallenge.max = challenge.date
        progressChallenge.progress = IngPercent

        progressChallenge.setProgressFormatter { progress, max ->
            var DEFAULT_PATTERN = "%d%%"
            String.format(DEFAULT_PATTERN, (progress.toFloat() / max.toFloat() * 100).toInt())
        }


        //캘린더에 넣을 날짜 받아오기
        var array = arrayOf<Dates>()
        array = db.ChallengeDay(Id)

        if(array.size != 0)
        {
            for(i in 0..(array.size-1))
            {
                calendar_view.markDate(
                    DateData(array.get(i).year, array.get(i).month, array.get(i).day).setMarkStyle(MarkStyle(
                        MarkStyle.BACKGROUND, Color.GREEN)))
            }
        }

        //캘린더

        MarkStyle.BACKGROUND
        calendar_view.setOnDateClickListener(object  : OnDateClickListener(){
            override fun onDateClick(view: View, date:DateData){
                calendar_view.markDate(
                    DateData(date.year, date.month, date.day).setMarkStyle(MarkStyle(
                    MarkStyle.BACKGROUND, Color.GREEN)))
                var dates = Dates(date.year, date.month, date.day)
                array += dates
            }
        })


        btnDoneChallenge.setOnClickListener {
            if(challenge.date == array.size)
            {

                Toast.makeText(applicationContext, "챌린지를 완료했습니다!!!\n    축하드립니다", Toast.LENGTH_LONG).show()
                db.ChallengeCompelete(challenge, UserId.toString())
                var intent = Intent(this, ChallengeActivityDone::class.java)
                intent.putExtra("id", UserId.toString())
                intent.putExtra("ChallengeId", challenge.id)
                startActivity(intent)
            }else{
                db.ChallengeRecord(challenge, array, User)
                var intent = Intent(this, ChallengeActivityJoin::class.java)
                intent.putExtra("id", UserId.toString())
                intent.putExtra("ChallengeId", challenge.id)
                startActivity(intent)
            }
        }

        imgBack.setOnClickListener {
            array = db.ChallengeDay(Id)
            finish()
        }

    }
}