package com.example.androiddemokt.ui.doubleCache

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import com.example.androiddemokt.R
import kotlinx.android.synthetic.main.activity_tablet.*

class TabletActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tablet)
        setListener()
    }

    fun setListener(){
        radioGroup.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                when(checkedId){
                    R.id.btn1 -> {
                        tabletView.visibility = View.VISIBLE
                        tablet2View.visibility = View.GONE
                        tablet3View.visibility = View.GONE
                    }
                    R.id.btn2 -> {
                        tabletView.visibility = View.GONE
                        tablet2View.visibility = View.VISIBLE
                        tablet3View.visibility = View.GONE
                    }
                    R.id.btn3 -> {
                        tabletView.visibility = View.GONE
                        tablet2View.visibility = View.GONE
                        tablet3View.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

}
