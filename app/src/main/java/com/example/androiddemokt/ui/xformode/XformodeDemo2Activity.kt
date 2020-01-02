package com.example.androiddemokt.ui.xformode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseArray
import android.view.View
import android.widget.AdapterView
import com.example.androiddemokt.R
import kotlinx.android.synthetic.main.activity_shadow.*
import kotlinx.android.synthetic.main.activity_shadow.selectSpinner
import kotlinx.android.synthetic.main.activity_xformode_demo.*
import kotlinx.android.synthetic.main.activity_xformode_demo2.*

class XformodeDemo2Activity : AppCompatActivity() {

    lateinit var sparseArray:SparseArray<View>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xformode_demo2)

        init()

    }

    fun init(){
        sparseArray = SparseArray<View>()
        sparseArray.put(0,circlePhotoSpannableView)
        sparseArray.put(1,anomalousPhotoView)
        sparseArray.put(2,guagualeView)

        selectSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                var name = parent?.getItemAtPosition(position).toString()
                if (name == "圆形头像") {
                    setVisiblity(0)
                } else if (name == "机器人边界头像") {
                    setVisiblity(1)
                } else if (name == "刮刮乐") {
                    setVisiblity(2)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }

    fun setVisiblity(n :Int){
        for( i in 0 until sparseArray.size()){
            if (i==n){
                sparseArray.get(i).visibility = View.VISIBLE
            }else{
                sparseArray.get(i).visibility = View.GONE
            }
        }
    }

}
