package com.example.greening

import android.content.Intent
import android.os.Bundle
import android.text.Selection.setSelection
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import sun.bob.mcalendarview.utils.CalendarUtil.date


class AddChallenge : AppCompatActivity(){
    lateinit var button : Button

    lateinit var editTitle : EditText
    lateinit var editDuration : TextView
    lateinit var editSummary : EditText
    lateinit var editKey1 : EditText
    lateinit var editKey2 : EditText
    lateinit var editKey3 : EditText
    lateinit var btnCancel : Button
    lateinit var btnDone : Button
    lateinit var btnPlus1 : Button
    lateinit var btnPlus2 : Button

    internal lateinit var db:DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_challenge)
        //아이디 연결
        //입력 항목 연결
        editTitle = findViewById(R.id.editTitle)
        editDuration = findViewById(R.id.editDuration)
        editSummary = findViewById(R.id.editSummary)
        editKey1 = findViewById(R.id.editKey1)
        editKey2 = findViewById(R.id.editKey2)
        editKey3 = findViewById(R.id.editKey3)

        //버튼 연결
        btnCancel = findViewById(R.id.btnCancel)
        btnDone = findViewById(R.id.btnDone)
        btnPlus1 = findViewById(R.id.btnPlus1)
        btnPlus2 = findViewById(R.id.btnPlus2)

        button = findViewById(R.id.button)

        //마지막에 저장한 값 받아오기
        loadData()

        //DB 객체 받아오기
        db = DBHelper(this)

        var data1 = arrayOf("음식", "운동", "플라스틱", "자원", "기타")

        //스피너(선택 항목) 연결
        var items = resources.getStringArray(R.array.type_challenges)
        var spinner: Spinner = findViewById(R.id.typeSpinner)

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.type_challenges,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }


        var category:String = ""
        var adapter1 = ArrayAdapter(this, android.R.layout.simple_spinner_item, data1)

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapter1

        var listener = SpinnerListener()

        spinner.onItemSelectedListener = listener

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                category = data1[p2].toString()
                Log.d("내용", "확인하기 : "+category)
            }
        }

        var startdate = intent.getStringExtra("dayFirst").toString()
        var lastdate = intent.getStringExtra("dayLast").toString()
        var whileDate = intent.getIntExtra("date", 0)
        Log.d("내용", "기간 : ${whileDate}")

        if(startdate.equals("null"))
        {
            editDuration.text = ""
        }else{
            editDuration.text = "${startdate} - ${lastdate}"
        }

        var title = editTitle.text.toString()
        var summary1 = editSummary.text.toString()
        var summary2 = "#${editKey1.text} #${editKey2.text} #${editKey3.text}"

        //키워드 항목 기본 설정
        editKey1.setVisibility(View.VISIBLE)
        editKey2.setVisibility(View.INVISIBLE)
        editKey3.setVisibility(View.GONE)
        btnPlus2.setVisibility(View.INVISIBLE)
        //키워드 항목 추가할 버튼 액션
        btnPlus1.setOnClickListener{
            editKey1.setVisibility(View.VISIBLE)
            editKey2.setVisibility(View.VISIBLE)
            editKey3.setVisibility(View.GONE)
            btnPlus2.setVisibility(View.VISIBLE)
        }
        btnPlus2.setOnClickListener {
            editKey1.setVisibility(View.VISIBLE)
            editKey2.setVisibility(View.VISIBLE)
            editKey3.setVisibility(View.VISIBLE)
        }

        //기간 설정하는 달력으로 가는 버튼
        button.setOnClickListener {
            saveData(editTitle.text.toString(), spinner.adapter, editSummary.text.toString(),
                editKey1.text.toString(), editKey2.text.toString(), editKey3.text.toString())
            var intent = Intent(this, DatePicker::class.java)
            startActivity(intent)
        }


        //챌린지 저장
        btnDone.setOnClickListener {
            if(editTitle.text.equals("")||category.equals("")||whileDate == null||editSummary.text.equals("")||editKey1.text.equals(""))
            {
                Toast.makeText(applicationContext, "모든 정보를 입력해주세요.", Toast.LENGTH_LONG).show()
            }else if(db.checkChallengeName(editTitle.text.toString()).equals(editTitle.text.toString())){
                Toast.makeText(applicationContext, "동일한 제목의 챌린지가 있습니다.\n다시 입력해주세요.", Toast.LENGTH_LONG).show()
            }
            else{

                var challenge = Challenge(db.ChallengeCount(), editTitle.text.toString(), category,
                    whileDate, 0, 0.0f,0,startdate ,lastdate, editSummary.text.toString(),
                    editKey1.text.toString(), editKey2.text.toString(), editKey3.text.toString())

                db.addChallenge(challenge)

                editTitle.setText("")
                editDuration.setText("")
                editSummary.setText("")
                editKey1.setText("")
                editKey2.setText("")
                editKey3.setText("")

                saveData(editTitle.text.toString(), spinner.adapter, editSummary.text.toString(),
                    editKey1.text.toString(), editKey2.text.toString(), editKey3.text.toString())
            }
        }
    }

    //받은 입력값을 저장하는 함수 생성
    private fun saveData(name:String, category: SpinnerAdapter, summary1:String, key1:String, key2:String, key3:String){
        Log.d("내용 ", "데이터 저장")
        //입력한 값 불러오기 - getPreferences이용(공유환경설정 파일 제공하는 메소드)
        var pref = this.getPreferences(0)
        var editor = pref.edit()

        //editor에 키, 몸무게 값을 입력하기 - apply()이용
        //apply() - editor에 해당 값을 저장하는 기능
        editor.putString("KEY_NAME",
           name).apply()
        editor.putString("KEY_CATEGORY",
            category.toString()).apply()
        editor.putString("KEY_SUMMARYLONG",
            summary1).apply()
        editor.putString("KEY1",
            key1).apply()
        editor.putString("KEY2",
            key2).apply()
        editor.putString("KEY3",
            key3).apply()
    }

    //데이터 불러오기
    private fun loadData(){
        Log.d("내용 ", "데이터 불러옴")
        var pref = this.getPreferences(0)
        //KEY가 각각 HEIGHT, WEIGHT인 값을 불러오기
        //defValue에는 해당 키가 존재하지 않을때 기본적으로 설정 할 값
        var name = pref.getString("KEY_NAME", "")
        var category = pref.getString("KEY_CATEGORY", "")
        var summary1 = pref.getString("KEY_SUMMARYLONG", "")
        var key1 = pref.getString("KEY1", "")
        var key2 = pref.getString("KEY2", "")
        var key3 = pref.getString("KEY3", "")

        //height와 weight 값이 각각 0이 아니라면(null 값이 아니라면) 불러오기
        editTitle.setText(name)
        editSummary.setText(summary1)
        editKey1.setText(key1)
        editKey2.setText(key2)
        editKey3.setText(key3)
    }
    inner class SpinnerListener : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(p0: AdapterView<*>?) {

        }

        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) { // p2가 사용자가 선택한 곳의 인덱스
            //var category = data1[p2].toString()
        }
    }
}

