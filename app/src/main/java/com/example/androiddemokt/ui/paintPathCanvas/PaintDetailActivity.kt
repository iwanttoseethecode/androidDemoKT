package com.example.androiddemokt.ui.paintPathCanvas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.example.androiddemokt.R
import kotlinx.android.synthetic.main.activity_paint_detail.*

class PaintDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paint_detail)

        selectSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                var name= parent?.getItemAtPosition(position).toString()
                if (name.equals("StrokeCap示例")){
                    myView.showId = 0
                }else if (name.equals("StrokeJoin示例")){
                    myView.showId = 1
                }else if(name.equals("CornerPathEffect示例")){
                    myView.showId = 2
                }else if (name == "CornerPathEffect DEMO曲线"){
                    myView.showId = 3
                }else if (name == "DashPathEffect DEMO 效果"){
                    myView.showId = 4
                }else if (name == "DiscretePathEffect DEMO效果"){
                    myView.showId = 5
                }else if (name == "PathDashPathEffect效果"){
                    myView.showId = 6
                }else if (name == "PathDashPathEffect DEMO效果"){
                    myView.showId = 7
                }else if (name == "ComposePathEffect与SumPathEffect"){
                    myView.showId = 8
                }else if (name == "SubpixelText Demo"){
                    myView.showId = 9
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

    }

}


