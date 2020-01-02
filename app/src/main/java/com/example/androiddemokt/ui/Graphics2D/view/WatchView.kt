package com.example.androiddemokt.ui.Graphics2D.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import java.util.*

/**
 * Created by luoling on 2019/11/27.
 * description:
 */
class WatchView(context: Context,attributeSet: AttributeSet?,defStyleAttr:Int) :
    View(context,attributeSet,defStyleAttr){

    constructor(context: Context,attributeSet: AttributeSet?):this(context,attributeSet,0){}

    constructor(context: Context):this(context,null){}

    lateinit var paint:Paint
    var r = 0
    //控件的长和宽
    var newWidth = 0
    var newHeight = 0
    lateinit var calendar: Calendar
    lateinit var path:Path

    init {
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        path = Path()
        calendar = Calendar.getInstance()
        run()
    }

    fun run(){
        Timer().schedule(object : TimerTask() {
            override fun run() {
                postInvalidate()
            }
        }, 0, 1000)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        //获取组件宽高度
        newWidth = measuredWidth -8
        newHeight = measuredHeight -8
        //获取表盘的半径
        r = Math.min(newWidth,newHeight)/2
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.color = Color.RED
        paint.strokeWidth = 4f
        paint.style = Paint.Style.STROKE
        //绘制表盘
        drawPlate(canvas)
        //绘制指针
        drawPoints(canvas)
        paint.color = Color.RED
        paint.style = Paint.Style.FILL
        canvas?.drawCircle(width.toFloat()/2,height.toFloat()/2,8f,paint)
    }

    fun drawPlate(canvas: Canvas?){
        canvas?.save()
        //画圆
        canvas?.drawCircle(width.toFloat()/2,height.toFloat()/2,r.toFloat(),paint)
        //画刻度
        for (i in 0 until 60){
            if (i%5 == 0){
                //长刻度占圆盘半径的十分之一
                paint.strokeWidth = 4f;
                paint.color = Color.RED
                canvas?.drawLine(
                    (width / 2).toFloat(),
                    (height / 2 - r).toFloat(),
                    (width / 2).toFloat(),
                    (height / 2 - 9 * r / 10).toFloat(),
                    paint
                )

                //通过二阶贝塞尔曲线画数字
                path.moveTo((width / 2 - 60).toFloat(), (height / 2 - 4 * r / 5).toFloat())
                path.quadTo(
                    (width / 2 ).toFloat(),
                    (height / 2 - 9 * r / 10).toFloat(),
                    (width / 2 + 60).toFloat(),
                    (height / 2 - 4 * r / 5).toFloat()
                )
                paint.strokeWidth = 2f
                paint.textSize = 30f
                if (i == 0){
                    canvas?.drawTextOnPath("12", path, 45f, 10f, paint)
                }else if (i == 50 || i == 55){
                    canvas?.drawTextOnPath((i / 5).toString(), path, 45f, 10f, paint)
                }else{
                    canvas?.drawTextOnPath((i / 5).toString(), path, 50f, 10f, paint)
                }
            }else{
                paint.strokeWidth = 2f
                paint.color = Color.GRAY
                canvas?.drawLine(
                    (width / 2).toFloat(),
                    (height / 2 - r).toFloat(),
                    (width / 2).toFloat(),
                    (height / 2 - 14 * r / 15).toFloat(),
                    paint
                )
            }
            canvas?.rotate(6f, (width / 2).toFloat(), (height / 2).toFloat())
        }
        canvas?.restore()
    }

    fun drawPoints(canvas: Canvas?){
        calendar.timeInMillis = System.currentTimeMillis()
        var hour = calendar.get(Calendar.HOUR)
        var minute = calendar.get(Calendar.MINUTE)
        var secend = calendar.get(Calendar.SECOND)

        var degree = 360/12 * hour
        var radians = Math.toRadians(degree.toDouble())
        var endX = width/2 + r*0.5*Math.cos(radians)
        var endY = height/2 + r*0.5*Math.sin(radians)
        canvas?.save()
        paint.color = Color.RED
        paint.strokeWidth = 6f
        canvas?.rotate(-90f,(width/2).toFloat(),(height/2).toFloat())
        canvas?.drawLine((width / 2).toFloat(), (height / 2).toFloat(), endX.toFloat(), endY.toFloat(), paint)
        radians = Math.toRadians((degree - 180).toDouble())
        endX = width / 2 + r.toDouble() * 0.07 * Math.cos(radians)
        endY = height / 2 + r.toDouble() * 0.07 * Math.sin(radians)
        canvas?.drawLine(
            (width / 2).toFloat(),
            (height / 2).toFloat(),
            endX.toFloat(),
            endY.toFloat(),
            paint
        )
        canvas?.restore()

        //画分针
        degree = 360 / 60 * minute
        radians = Math.toRadians(degree.toDouble())
        endX = width / 2 + r.toDouble() * 0.65 * Math.cos(radians)
        endY = height / 2 + r.toDouble() * 0.65 * Math.sin(radians)
        canvas?.save()
        paint.color = Color.BLACK
        paint.strokeWidth = 4f
        canvas?.rotate(-90f, (width / 2).toFloat(), (height / 2).toFloat())
        canvas?.drawLine(
            (width / 2).toFloat(),
            (height / 2).toFloat(),
            endX.toFloat(),
            endY.toFloat(),
            paint
        )
        radians = Math.toRadians((degree - 180).toDouble())
        endX = width / 2 + r.toDouble() * 0.10 * Math.cos(radians)
        endY = height / 2 + r.toDouble() * 0.10 * Math.sin(radians)
        canvas?.drawLine(
            (width / 2).toFloat(),
            (height / 2).toFloat(),
            endX.toFloat(),
            endY.toFloat(),
            paint
        )
        canvas?.restore()

        //画秒针
        degree = 360 / 60 * secend
        radians = Math.toRadians(degree.toDouble())
        endX = width / 2 + r.toDouble() * 0.8 * Math.cos(radians)
        endY = height / 2 + r.toDouble() * 0.8 * Math.sin(radians)
        canvas?.save()
        paint.color = Color.BLACK
        paint.strokeWidth = 2f
        canvas?.rotate(-90f, (width / 2).toFloat(), (height / 2).toFloat())
        canvas?.drawLine(
            (width / 2).toFloat(),
            (height / 2).toFloat(),
            endX.toFloat(),
            endY.toFloat(),
            paint
        )
        //给秒针加个尾巴
        radians = Math.toRadians((degree - 180).toDouble())
        endX = width / 2 + r.toDouble() * 0.15 * Math.cos(radians)
        endY = height / 2 + r.toDouble() * 0.15 * Math.sin(radians)
        canvas?.drawLine(
            (width / 2).toFloat(),
            (height / 2).toFloat(),
            endX.toFloat(),
            endY.toFloat(),
            paint
        )
        canvas?.restore()
    }

}