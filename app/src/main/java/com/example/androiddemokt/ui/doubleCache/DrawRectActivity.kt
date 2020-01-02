package com.example.androiddemokt.ui.doubleCache

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.androiddemokt.R
import kotlinx.android.synthetic.main.activity_draw_rect.*

class DrawRectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draw_rect)

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.radioBtn1 -> {
                    drawRect1View.visibility = View.VISIBLE
                    drawRect2View.visibility = View.GONE
                }
                R.id.radioBtn2 -> {
                    drawRect1View.visibility = View.GONE
                    drawRect2View.visibility = View.VISIBLE
                }
            }
        }

    }
}
