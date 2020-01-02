package com.example.androiddemokt.ui.doubleCache.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * Created by luoling on 2019/11/28.
 * description:
 */
class DrawRect2View(context: Context,attributeSet: AttributeSet?,defStyleAttr:Int ) : View(context,attributeSet,defStyleAttr) {

    constructor(context: Context,attributeSet: AttributeSet?):this(context,attributeSet,0){}

    constructor(context: Context):this(context,null){}

    var firstX:Float = 0f
    var firstY:Float = 0f
    lateinit var paint:Paint
    lateinit var path:Path
    lateinit var myBitmap: Bitmap
    lateinit var myCanvas:Canvas

    init {
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.color = Color.RED
        paint.strokeWidth = 5f
        path = Path()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        myBitmap = Bitmap.createBitmap(measuredWidth,measuredHeight,Bitmap.Config.ARGB_8888)
        myCanvas = Canvas(myBitmap)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        var x = event.getX()
        var y = event.getY()
        when(event.action){
            MotionEvent.ACTION_DOWN ->{
                path.reset()
                firstX = x
                firstY = y
            }
            MotionEvent.ACTION_MOVE ->{
                if (firstX < x && firstY < y){
                    path.addRect(firstX,firstY,x,y,Path.Direction.CW)
                }
                if (firstX < x && firstY > y){
                    path.addRect(firstX,y,x,firstY,Path.Direction.CW)
                }
                if (firstX > x && firstY < y){
                    path.addRect(x,firstY,firstX,y,Path.Direction.CW)
                }
                if (firstX > x && firstY > y){
                    path.addRect(x,y,firstX,firstY,Path.Direction.CW)
                }
                invalidate()
            }
            MotionEvent.ACTION_UP -> {
                myCanvas.drawPath(path, paint)
                invalidate()
            }
        }
        return true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawPath(path, paint)
        canvas?.drawBitmap(myBitmap,0f,0f,null)
    }

}