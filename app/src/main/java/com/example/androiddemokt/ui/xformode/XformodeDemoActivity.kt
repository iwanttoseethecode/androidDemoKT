package com.example.androiddemokt.ui.xformode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.SparseArray
import android.view.View
import android.widget.AdapterView
import androidx.core.util.forEach
import com.example.androiddemokt.R
import kotlinx.android.synthetic.main.activity_xformode_demo.*

class XformodeDemoActivity : AppCompatActivity() {

    lateinit var sparseArray:SparseArray<View>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xformode_demo)

        addViews()
        setListener()
    }

    fun addViews(){
        sparseArray = SparseArray()
        sparseArray.put(0,heartMapView)
        sparseArray.put(1,circleWaveView)
        sparseArray.put(2,eraserView)
        sparseArray.put(3,guaguaCardView)
    }

    fun setVisible(num:Int){
        sparseArray.forEach { key, value ->
            if (key == num){
                value.visibility = View.VISIBLE
            }else{
                value.visibility = View.GONE
            }
        }
    }

    fun setListener(){
        selectSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var selectName = parent?.getItemAtPosition(position).toString()
                if (TextUtils.equals(selectName, "心电图")) {
                    setVisible(0)
                } else if (TextUtils.equals(selectName, "波浪圆球图")) {
                    setVisible(1)
                } else if (TextUtils.equals(selectName, "橡皮檫控件")) {
                    setVisible(2)
                } else if (TextUtils.equals(selectName, "刮刮卡")) {
                    setVisible(3)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }

}
