package com.example.androiddemokt.ui.coordinateChange

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

/**
 * Created by luoling on 2019/11/29.
 * description:
 */
class CoordinateView(context: Context,attributeSet: AttributeSet?,defStyleAttr:Int) : View(context,attributeSet,defStyleAttr){

    constructor(context: Context,attributeSet: AttributeSet?):this(context,attributeSet,0){}

    constructor(context: Context):this(context,null){}

    var paint: Paint

    init{
        paint = Paint()
        paint.isAntiAlias = true
        paint.color = Color.RED
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 3f
    }

    override fun onDraw(canvas: Canvas?) {
        //保存现场，保存现在canvas的坐标状态
        canvas?.save()
        for (i in 0 until 10){
            canvas?.drawRect(RectF(0f,0f,200f,200f),paint)
            canvas?.translate(20f,20f)
        }
        //回复上次保存的坐标状态
        canvas?.restore()

        canvas?.translate(0f,500f)
        canvas?.save()
        for (i in 0 until 10){
            canvas?.drawRect(0f,0f,400f,400f,paint)
            canvas?.scale(0.9f,0.9f,200f,200f)
        }
        canvas?.restore()

        canvas?.save()
        canvas?.translate(0f,500f)
        canvas?.drawCircle(200f,200f,200f,paint)
        for (i in 0 until 12){
            canvas?.drawLine(350f,200f,400f,200f,paint)
            canvas?.rotate(30f,200f,200f)
        }
        canvas?.restore()

    }


}