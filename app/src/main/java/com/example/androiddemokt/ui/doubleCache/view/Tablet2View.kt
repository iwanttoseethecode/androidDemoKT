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
class Tablet2View constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) :
    View(context, attrs, defStyleAttr) {

    constructor(context: Context,attrs: AttributeSet?):this(context, attrs,0){}
    constructor(context: Context):this(context,null){}


    private val path: Path
    private var PreX: Int = 0
    private var PreY: Int = 0
    private val paint: Paint

    init {
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.isAntiAlias = true
        paint.strokeCap = Paint.Cap.ROUND
        paint.style = Paint.Style.STROKE
        paint.strokeJoin = Paint.Join.ROUND
        paint.color = Color.RED
        paint.strokeWidth = 20f
        path = Path()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(path, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        val x = event.x.toInt()
        val y = event.y.toInt()
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.reset()
                PreX = x
                PreY = y
                path.moveTo(x.toFloat(), y.toFloat())
            }
            MotionEvent.ACTION_MOVE -> {
                path.quadTo(PreX.toFloat(), PreY.toFloat(), x.toFloat(), y.toFloat())
                invalidate()
                PreX = x
                PreY = y
            }
            MotionEvent.ACTION_UP -> {
            }
        }
        return true
    }
}