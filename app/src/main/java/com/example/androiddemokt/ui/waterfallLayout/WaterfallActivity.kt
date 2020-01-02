package com.example.androiddemokt.ui.waterfallLayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.example.androiddemokt.R
import kotlinx.android.synthetic.main.activity_waterfall.*
import java.util.*

class WaterfallActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_waterfall)

        waterfallLayout.setOnItemClickListener(object: WaterfallLayout.OnItemClickListener{
            override fun itemClick(v: View, index: Int) {
                Toast.makeText(this@WaterfallActivity, "第" + index + "个按钮", Toast.LENGTH_SHORT)
                    .show()
            }
        })

        myBtn.setOnClickListener {
            addImageView()
        }

    }

    fun addImageView(){
        var imgView = ImageView(this)
        var wp = WaterfallLayout.WaterfallLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)

        var random = Random()
        var num = random.nextInt(5)
        if (num == 0){
            imgView.setImageResource(R.mipmap.p1)
        }else if (num == 1){
            imgView.setImageResource(R.mipmap.p2)
        }else if (num == 2){
            imgView.setImageResource(R.mipmap.p3)
        }else if (num == 3){
            imgView.setImageResource(R.mipmap.p4)
        }else if (num == 4){
            imgView.setImageResource(R.mipmap.p5)
        }

        imgView.scaleType = ImageView.ScaleType.FIT_XY

        waterfallLayout.addView(imgView,wp)

    }

}
