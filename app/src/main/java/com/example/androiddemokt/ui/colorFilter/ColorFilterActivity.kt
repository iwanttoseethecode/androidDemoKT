package com.example.androiddemokt.ui.colorFilter

import android.graphics.ColorFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.example.androiddemokt.R
import kotlinx.android.synthetic.main.activity_color_filter.*

class ColorFilterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_filter)

        setSpinnerListener()
    }

    fun setViewShowMode(type : ColorFilterView.ViewType,mode:Int){
        colorFilterView.setViewType(type,mode)
    }

    fun setSpinnerListener(){

        selectSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?,position: Int,id: Long
            ) {
                var name = parent?.getItemAtPosition(position).toString()
                if (name == "模糊-normal") {
                    setViewShowMode(ColorFilterView.ViewType.BlurMaskFilter, 0)
                } else if (name == "模糊-solid") {
                    setViewShowMode(ColorFilterView.ViewType.BlurMaskFilter, 1)
                } else if (name == "模糊-outer") {
                    setViewShowMode(ColorFilterView.ViewType.BlurMaskFilter, 2)
                } else if (name == "模糊-inner") {
                    setViewShowMode(ColorFilterView.ViewType.BlurMaskFilter, 3)
                } else if (name == "浮雕效果") {
                    setViewShowMode(ColorFilterView.ViewType.EmbossMaskFilter, 0)
                } else if (name == "滤镜-颜色通道加法") {
                    setViewShowMode(ColorFilterView.ViewType.ColorMatrixColorFilter, 0)
                } else if (name == "滤镜-底片效果") {
                    setViewShowMode(ColorFilterView.ViewType.ColorMatrixColorFilter, 1)
                } else if (name == "滤镜-颜色增强") {
                    setViewShowMode(ColorFilterView.ViewType.ColorMatrixColorFilter, 2)
                } else if (name == "滤镜-黑白照片") {
                    setViewShowMode(ColorFilterView.ViewType.ColorMatrixColorFilter, 3)
                } else if (name == "滤镜-发色效果") {
                    setViewShowMode(ColorFilterView.ViewType.ColorMatrixColorFilter, 4)
                } else if (name == "滤镜-复古效果") {
                    setViewShowMode(ColorFilterView.ViewType.ColorMatrixColorFilter, 5)
                } else if (name == "滤镜-过滤颜色通道") {
                    setViewShowMode(ColorFilterView.ViewType.ColorMatrixColorFilter, 6)
                } else if (name == "滤镜-颜色系数缩放") {
                    setViewShowMode(ColorFilterView.ViewType.ColorMatrixColorFilter, 7)
                } else if (name == "滤镜-颜色饱和度") {
                    setViewShowMode(ColorFilterView.ViewType.ColorMatrixColorFilter, 8)
                } else if (name == "滤镜-某个颜色通道角度旋转") {
                    setViewShowMode(ColorFilterView.ViewType.ColorMatrixColorFilter, 9)
                } else if (name == "亮度-红色亮度修改") {
                    setViewShowMode(ColorFilterView.ViewType.LightingColorFilter, 0)
                } else if (name == "亮度-绿色亮度修改") {
                    setViewShowMode(ColorFilterView.ViewType.LightingColorFilter, 1)
                } else if (name == "亮度-蓝色亮度修改") {
                    setViewShowMode(ColorFilterView.ViewType.LightingColorFilter, 2)
                } else if (name == "亮度-rgb亮度修改") {
                    setViewShowMode(ColorFilterView.ViewType.LightingColorFilter, 3)
                } else if (name == "PorterDuff-蓝色dst_in") {
                    setViewShowMode(ColorFilterView.ViewType.PorterDuffColorFilter, 0)
                } else if (name == "PorterDuff-绿色src_atop") {
                    setViewShowMode(ColorFilterView.ViewType.PorterDuffColorFilter, 1)
                } else if (name == "PorterDuff-红色src_in") {
                    setViewShowMode(ColorFilterView.ViewType.PorterDuffColorFilter, 2)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

    }

}
