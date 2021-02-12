package com.example.greening

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog

class ChallengeActivityDone : AppCompatActivity() {

    // 변수 선언
    // 뒤로가기
    lateinit var imgBack : ImageView

    // 제목
    lateinit var nameChallenge : TextView
    lateinit var detailChallenge : TextView

    // 챌린지 정보
    // 인원
    lateinit var numPeople : TextView
    // 기간
    lateinit var period : TextView
    // 평가
    lateinit var rate : TextView

    // 후기쓰기 버튼
    lateinit var btnRate : Button

    //데이터 베이스 변수
    internal lateinit var db:DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge_done_5)

        //DB 객체 연결
        db = DBHelper(this)

        // 아이디 연결
        imgBack = findViewById(R.id.imgBack)
        nameChallenge = findViewById(R.id.nameChallenge)
        detailChallenge = findViewById(R.id.detailChallenge)
        numPeople = findViewById(R.id.numPeople)
        period = findViewById(R.id.period)
        rate = findViewById(R.id.rate)
        btnRate = findViewById(R.id.btnRate)

        var UserId = intent.getStringExtra("id")

        //사용자 유저 객체 생성 - 로그인 했을 떄 DB에 저장된 해당 회원의 정보를 반환
        //var User : Person = Person()

        //Person Table에서 해당 id를 가지고 있는 사람 정보 받아오기
        //User = db.DataIn(UserId.toString())

        var Id: Int = intent.getIntExtra("ChallengeId",0)
        var challenge:Challenge = Challenge()
        challenge = db.Challengereturn(Id!!.toInt())

        nameChallenge.setText(challenge.name)
        numPeople.setText(db.ChallengeJoinCount(challenge))
        period.setText("${challenge.StartDate} - ${challenge.LastDate}")
        detailChallenge.setText("${challenge.SummaryLong}")
        rate.setText(challenge.score.toString())


        //후기 쓰기 버튼
            btnRate.setOnClickListener(object:View.OnClickListener{
                var Score:Float = 0.0f
                override fun onClick(view:View){
                    //다이얼로그 객체 생성
                    var dialog = Dialog(this@ChallengeActivityDone)
                    //인플레이션
                    dialog.setContentView(R.layout.rate_dialog)
                    //객체화
                    var ratingBar = dialog.findViewById<RatingBar>(R.id.dialogRb)
                    var btnCancelRD = dialog.findViewById<Button>(R.id.btnCancelRD)
                    var btnOk = dialog.findViewById<Button>(R.id.button_join_ok)

                    btnOk.setOnClickListener(object: View.OnClickListener {
                        override fun onClick(view:View) {
                            if(Score > 0.0f)
                            {
                                challenge.score = Score
                                db.ChallengeReview(challenge, UserId.toString())
                                rate.setText("${db.ChallengeScoreCalculate(challenge)}")
                                dialog.dismiss()
                            }else{
                                Toast.makeText(applicationContext, "챌린지 점수를 입력해주세요.",
                                    Toast.LENGTH_LONG).show()
                            }
                        }
                    })
                    btnCancelRD.setOnClickListener(object: View.OnClickListener {
                        override fun onClick(view:View) {
                            dialog.dismiss()
                        }
                    })
                    //리스너 설정 - OnRatingBarChangeListener
                    ratingBar.setOnRatingBarChangeListener(object:RatingBar.OnRatingBarChangeListener {
                        override fun onRatingChanged(ratingBar:RatingBar, v:Float, b:Boolean) {
                            //float v(rating) - 별점 반칸 0.5점
                            //별개수에 따른 별 한칸당 평균
                            val st:Float = 5.0f / ratingBar.getNumStars()
                            Score = st
                        }
                    })
                    //다이얼로그 창 주변부분 터치 가능여부
                    dialog.setCanceledOnTouchOutside(false)
                    dialog.show()
                }
            })

        imgBack.setOnClickListener{
            finish()
        }
    }
}