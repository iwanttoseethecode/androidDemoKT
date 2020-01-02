package com.example.androiddemokt.ui.flowLayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.androiddemokt.R
import kotlinx.android.synthetic.main.activity_flow_layout.*

class FlowLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow_layout)

        myFlowlayout.setOnItemClickListener(object : FlowLayout.OnItemClickListener {
            override fun ItemClick(v: View, index: Int) {
                Toast.makeText(this@FlowLayoutActivity, "第" + index + "个按钮", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
}
