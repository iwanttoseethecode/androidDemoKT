package com.example.androiddemokt.ui.shadow.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * Created by luoling on 2019/11/28.
 * description:
 */
class LinearGradientView(context: Context,attrs:AttributeSet?,defStyleAttr :Int ) : View(context, attrs, defStyleAttr) {

    constructor(context: Context,attrs: AttributeSet?):this(context,attrs,0){}

    constructor(context: Context):this(context,null){}

    var OFFSET = 100
    var paint: Paint

    init {
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        var rect1 = Rect(100,100,500,400)
        var linearGradient = LinearGradient(rect1.left.toFloat(),rect1.top.toFloat(),rect1.right.toFloat(),rect1.bottom.toFloat(), Color.RED,Color.BLUE,Shader.TileMode.CLAMP)
        paint.shader = linearGradient
        canvas?.drawRect(rect1,paint)

        //坐标往下移
        canvas?.translate(0f,rect1.height().toFloat()+OFFSET)
        var rect2 = Rect(rect1)
        rect2.inset(-100,-100)
        linearGradient = LinearGradient(rect2.left.toFloat(),rect2.top.toFloat(),rect2.right.toFloat(),rect2.bottom.toFloat(),Color.RED,Color.BLUE,Shader.TileMode.CLAMP)
        paint.shader = linearGradient
        canvas?.drawRect(rect1,paint)

        //坐标往下移
        canvas?.translate(0f,rect1.height().toFloat() + OFFSET)
        //缩小渐变矩形
        var rect3 = Rect(rect1)
        rect3.inset(100,100)
        linearGradient = LinearGradient(rect3.left.toFloat(),rect3.top.toFloat(),rect3.right.toFloat(),rect3.bottom.toFloat(),Color.RED,Color.BLUE,Shader.TileMode.CLAMP)
        paint.shader = linearGradient
        canvas?.drawRect(rect1,paint)
    }

}