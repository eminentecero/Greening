package com.example.greening

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import androidx.core.content.ContextCompat
import com.example.hackathon.ListViewAdapter

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        // 변수 선언
        val listview: ListView
        val adapter: ListViewAdapter

        // Adapter 생성
        adapter = ListViewAdapter()

        // 리스트뷰 참조 및 Adapter달기
        listview = findViewById<View>(R.id.settingList) as ListView
        listview.adapter = adapter

        // addItem 함수가 실행되면서 position: 0부터 순차적으로 삽입된다.
        // 공지사항(position: 0)
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_baseline_error_24)!!, "공지사항")

        // 프로필 수정(position: 1)
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_baseline_person_24)!!, "프로필 수정")

        // 비밀번호 재설정(position: 2)
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_baseline_lock_24)!!, "비밀번호 재설정")

        // 고객센터(position: 3)
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_baseline_headset_mic_24)!!, "고객센터")

        // 탈퇴하기(position: 4)
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_baseline_exit_to_app_24)!!, "탈퇴하기")

        // 리스트 뷰의 아이템 클릭 리스너를 장착한다.
        listview.setOnItemClickListener { parent, view, position, id ->
            when(position){
                // position: 1 -> 프로필 수정
                1-> {
                    val intent = Intent(this, ProfileChangeActivity::class.java)
                    startActivity(intent)
                }

                // position: 2 -> 비밀번호 재설정
                2-> {
                    val intent = Intent(this, PasswordChangeActivity::class.java)
                    startActivity(intent)
                }

                // position: 4 -> 탈퇴하기
                4-> {
                    val intent = Intent(this, LeaveAppActivity::class.java)
                    startActivity(intent)
                }
            }

        }
    }
}