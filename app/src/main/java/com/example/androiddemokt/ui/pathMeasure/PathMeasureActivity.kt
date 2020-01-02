package com.example.androiddemokt.ui.pathMeasure

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseArray
import android.view.View
import android.widget.AdapterView
import com.example.androiddemokt.R
import kotlinx.android.synthetic.main.activity_path_measure.*

class PathMeasureActivity : AppCompatActivity() {

    lateinit var sparseArray: SparseArray<View>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_path_measure)

        init()

    }

    fun init(){
        sparseArray = SparseArray()
        sparseArray.put(0, grabageView)
        sparseArray.put(1, pathMeasureView)
        sparseArray.put(2, smileLoadingView)
        sparseArray.put(3, loadingView)
        sparseArray.put(4, shipView)
        sparseArray.put(5, headView)

        selectSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                var name = parent?.getItemAtPosition(position).toString()
                if (name == "垃圾桶") {
                    setVisible(0)
                } else if (name == "PathMeasure基础") {
                    setVisible(1)
                } else if (name == "加载动画") {
                    setVisible(2)
                } else if (name == "加载圈") {
                    setVisible(3)
                } else if (name == "小船飘飘") {
                    setVisible(4)
                } else if (name == "头部空间") {
                    setVisible(5)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }

    private fun setVisible(num: Int) {
        for (i in 0 until sparseArray.size()) {
            if (i == num) {
                sparseArray.get(i).visibility = View.VISIBLE
                continue
            }
            sparseArray.get(i).visibility = View.GONE
        }
    }

}
