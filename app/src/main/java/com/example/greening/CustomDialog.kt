package com.example.greening

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.Button
import android.widget.RatingBar

class CustomDialog(context: Context) {

    //액티비티와 데이터 주고 받기
    interface ButtonClickListener{
        fun onClicked(rate: Float) {
        }
    }

    private val dialog = Dialog(context)

    fun myDig(){
        dialog.setContentView(R.layout.rate_dialog)
        dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT)
        //다이얼로그 영역 밖을 터치하면 사라지도록 하는 부분
        dialog.setCanceledOnTouchOutside(true)
        //취소가 가능하도록
        dialog.setCancelable(true)

        dialog.show()
    }

    fun MyDig(){
        dialog.setContentView(R.layout.rate_dialog)

        dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
        WindowManager.LayoutParams.WRAP_CONTENT)
        //다이얼로그 영역 밖을 터치하면 사라지도록 하는 부분
        dialog.setCanceledOnTouchOutside(true)
        //취소가 가능하도록
        dialog.setCancelable(true)

        dialog.show()

        var ratingBar = dialog.findViewById<RatingBar>(R.id.dialogRb)
        var btnCancelRD = dialog.findViewById<Button>(R.id.btnCancelRD)
        var btnOk = dialog.findViewById<Button>(R.id.button_join_ok)

        btnCancelRD.setOnClickListener {
            onClickedListener.onClicked(ratingBar.rating)
            dialog.dismiss()
        }
        btnOk.setOnClickListener {

            dialog.dismiss()
        }
    }
    private lateinit var onClickedListener: ButtonClickListener

    fun setOnClickedListener(listener : ButtonClickListener){
        onClickedListener = listener
    }
}
