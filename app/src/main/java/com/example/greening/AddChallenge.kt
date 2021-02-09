package com.example.greening

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


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

        //스피너(선택 항목) 연결
        val items = resources.getStringArray(R.array.type_challenges)
        val spinner: Spinner = findViewById(R.id.typeSpinner)
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

        button.setOnClickListener {
            var intent = Intent(this, DatePicker::class.java)
            startActivity(intent)
        }

        var startdate = intent.getStringExtra("dayFirst").toString()
        var lastdate = intent.getStringExtra("dayLast").toString()
        var whileDate = intent.getStringArrayExtra("date")

        editDuration.text = "${startdate} - ${lastdate}"

        var title = editTitle.text
        var category = spinner.adapter
        var date = editDuration.text
        var summary = editSummary.text
        var keyword = "#${editKey1.text} #${editKey2.text} #${editKey3.text}"


        }
    }

