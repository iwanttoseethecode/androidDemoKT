package com.example.androiddemokt.ui.shadow.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * Created by luoling on 2019/11/28.
 * description:
 */
class ShaderView(context: Context, attrs:AttributeSet?,defStyleAttrs:Int) : View(context, attrs,defStyleAttrs) {

    constructor(context: Context,attrs: AttributeSet?):this(context,attrs,0){}

    constructor(context: Context):this(context,null){}

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.textSize = 75f
        setLayerType(View.LAYER_TYPE_SOFTWARE,paint)
        paint.setShadowLayer(10f,1f,1f, Color.RED)
        canvas?.drawText("Android 开发",100f,100f,paint)
        paint.setShadowLayer(6f,40f,40f,Color.BLUE)
        canvas?.drawText("Android绘图技术",100f,550f,paint)
    }
}