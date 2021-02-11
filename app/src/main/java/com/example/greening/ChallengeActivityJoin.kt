package com.example.greening

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import sun.bob.mcalendarview.MarkStyle
import sun.bob.mcalendarview.listeners.OnDateClickListener
import sun.bob.mcalendarview.vo.DateData

//import com.applikeysolutions.cosmocalendar.selection.RangeSelectionManager


class ChallengeActivityJoin : AppCompatActivity() {

    // 변수 선언
    lateinit var imgBack : ImageView

    // 챌린지 정보
    lateinit var nameChallenge : TextView
    lateinit var numPeopleChallenge : TextView
    lateinit var periodChallenge : TextView

    // 프로그레스 바
    lateinit var progressChallenge : ProgressBar

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
        var UserId = intent.getStringExtra("id")

        //사용자 유저 객체 생성 - 로그인 했을 떄 DB에 저장된 해당 회원의 정보를 반환
        var User : Person = Person()

        //Person Table에서 해당 id를 가지고 있는 사람 정보 받아오기
        User = db.DataIn(UserId.toString())

        var Id = intent.getStringExtra("ChallengeId")?.toInt()

        var challenge : Challenge = db.Challengereturn(Id)
        nameChallenge.setText(challenge.name)
        numPeopleChallenge.setText(db.ChallengeJoinCount(challenge))
        periodChallenge.setText("${challenge.StartDate} - ${challenge.LastDate}")

        //캘린더에 넣을 날짜 받아오기
        var array = arrayOf<Dates>()
        array = db.ChallengeDay(Id.toString())

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
            db.ChallengeRecord(challenge, array)


        }

        imgBack.setOnClickListener {
            array = db.ChallengeDay(Id.toString())

            if(array.size != 0)
            {
                for(i in 0..(array.size-1))
                {
                    calendar_view.markDate(
                        DateData(array.get(i).year, array.get(i).month, array.get(i).day).setMarkStyle(MarkStyle(
                            MarkStyle.DEFAULT, Color.WHITE)))
                }
            }
            finish()
        }

    }
}