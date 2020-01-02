package com.example.androiddemokt.ui.shadow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseArray
import android.view.View
import android.widget.AdapterView
import com.example.androiddemokt.R
import kotlinx.android.synthetic.main.activity_shadow.*

class ShadowActivity : AppCompatActivity() {

    lateinit var sparseArray: SparseArray<View>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shadow)
        init()
    }

    fun init(){
        sparseArray = SparseArray<View>()
        sparseArray.put(0, shaderView)
        sparseArray.put(1, linearGradientView)
        sparseArray.put(2, radialGradientView)
        sparseArray.put(3, fiveChessView)
        sparseArray.put(4, sweepGradientView)
        sparseArray.put(5, bitmapShaderView)
        sparseArray.put(6, composeShaderView)
        sparseArray.put(7, gradientAndMatrixView)
        sparseArray.put(8, highLightLinearGradientTextView)
        sparseArray.put(9, zoomImageView)

        selectSpinner.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                var name = parent?.getItemAtPosition(position).toString()
                if (name == "阴影显示") {
                    setVisibility(0)
                } else if (name == "线性渐变") {
                    setVisibility(1)
                } else if (name == "径向渐变") {
                    setVisibility(2)
                } else if (name == "五子棋盘") {
                    setVisibility(3)
                } else if (name == "扫描渐变") {
                    setVisibility(4)
                } else if (name == "位图渐变") {
                    setVisibility(5)
                } else if (name == "混合渐变") {
                    setVisibility(6)
                } else if (name == "渐变和矩阵") {
                    setVisibility(7)
                } else if (name == "文字高亮") {
                    setVisibility(8)
                } else if (name == "图片放大镜") {
                    setVisibility(9)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }

    fun setVisibility(n:Int){
        for (i in 0 until sparseArray.size()){
            if (i == n){
                sparseArray.get(i).visibility = View.VISIBLE
            }else{
                sparseArray.get(i).visibility = View.GONE
            }
        }
    }

}
