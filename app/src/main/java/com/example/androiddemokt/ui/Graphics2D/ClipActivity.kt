package com.example.androiddemokt.ui.Graphics2D

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.androiddemokt.R
import kotlinx.android.synthetic.main.activity_clip.*

class ClipActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clip)



    }

    fun clickBtn1(view: View) {
        clipView.setSerialNumber(1)
    }
    fun clickBtn2(view: View) {
        clipView.setSerialNumber(2)
    }
    fun clickBtn3(view: View) {
        clipView.setSerialNumber(3)
    }
    fun clickBtn4(view: View) {
        clipView.setSerialNumber(4)
    }
    fun clickBtn5(view: View) {
        clipView.setSerialNumber(5)
    }
    fun clickBtn6(view: View) {
        clipView.setSerialNumber(6)
    }

}
