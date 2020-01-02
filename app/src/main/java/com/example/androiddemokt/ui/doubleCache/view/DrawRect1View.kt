package com.example.androiddemokt.ui.doubleCache.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * Created by luoling on 2019/11/27.
 * description:
 */
class DrawRect1View constructor(context:Context,attributeSet: AttributeSet?,defStyleAttrs:Int): View(context,attributeSet,defStyleAttrs) {

    constructor(context: Context,attributeSet: AttributeSet?):this(context,attributeSet,0){}

    constructor(context: Context):this(context,null){}

    var firstX:Float = 0f
    var firstY:Float = 0f
    lateinit var path: Path
    lateinit var paint:Paint

    init {
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = Color.RED
        paint.textSize = 5f
        paint.style = Paint.Style.STROKE
        path = Path()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawPath(path,paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        var x = event.getX()
        var y = event.getY()
        when(event.action){
            MotionEvent.ACTION_DOWN -> {
                path.reset()
                firstX = x
                firstY = y
            }
            MotionEvent.ACTION_MOVE -> {
                if(firstX < x && firstY < y){
                    path.addRect(firstX,firstX,x,y,Path.Direction.CW)
                }
                if (firstX < x && firstY > y){
                    path.addRect(firstX,y,x,firstY,Path.Direction.CW)
                }
                if (firstX > x && firstY > y){
                    path.addRect(x,y,firstX,firstY,Path.Direction.CW)
                }
                if (firstX > x && firstY < y){
                    path.addRect(x,firstY,firstX,y,Path.Direction.CW)
                }
                invalidate()
            }
            MotionEvent.ACTION_UP ->{
                invalidate()
            }
        }

        return true
    }

}