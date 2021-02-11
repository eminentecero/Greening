package com.example.greening

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.applikeysolutions.cosmocalendar.selection.OnDaySelectedListener
import com.applikeysolutions.cosmocalendar.selection.RangeSelectionManager
import java.text.SimpleDateFormat
import java.util.*

class DatePicker : AppCompatActivity() {
    lateinit var calendar_view: com.applikeysolutions.cosmocalendar.view.CalendarView
    lateinit var imgBack: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date_picker)

        calendar_view = findViewById(R.id.calendar_view)
        imgBack = findViewById(R.id.imgBack)

        var day_fist: String = ""
        var day_last: String = ""
        var Date:Int = -1

//            calendar_view.isShowDaysOfWeekTitle = false
        calendar_view.selectionManager = RangeSelectionManager(OnDaySelectedListener {
            Log.e(" CALENDAR ", "========== setSelectionManager ==========")
            Log.e(" CALENDAR ", "Selected Dates : " + calendar_view.selectedDates.size)
            if (calendar_view.selectedDates.size <= 0) return@OnDaySelectedListener
            Log.e(" CALENDAR ", "Selected Days : " + calendar_view.selectedDays)
            Log.e(" CALENDAR ", "First Days : " + calendar_view.selectedDays.first())
            Log.e(" CALENDAR ", "Last Days : " + calendar_view.selectedDays.last())
            var days = calendar_view.getSelectedDates()
            var result = ""

            for(i in 0..calendar_view.selectedDates.size-1)
            {
                Date++
            }

            var firstcalendar = days.get(0)
            var day = firstcalendar.get(Calendar.DAY_OF_MONTH)
            var month = firstcalendar.get(Calendar.MONTH)
            var year = firstcalendar.get(Calendar.YEAR)
            day_fist = "${year}년 ${month + 1}월 ${day}일"
            var StartDays:Dates = Dates(year, month+1, day)

            var lastcalendar = days.get(calendar_view.selectedDates.size-1)
            day = lastcalendar.get(Calendar.DAY_OF_MONTH)
            month = lastcalendar.get(Calendar.MONTH)
            year = lastcalendar.get(Calendar.YEAR)
            day_last = "${year}년 ${month + 1}월 ${day}일"
            var LastDays:Dates = Dates(year, month+1, day)
        })


        imgBack.setOnClickListener{
            var intent = Intent(this, AddChallenge::class.java)
            intent.putExtra("dayFirst", day_fist)
            intent.putExtra("dayLast", day_last)
            intent.putExtra("date", Date)

            startActivity(intent)
        }

    }

}

