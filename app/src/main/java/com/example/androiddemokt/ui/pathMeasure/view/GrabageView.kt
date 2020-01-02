package com.example.androiddemokt.ui.pathMeasure.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.animation.addListener

/**
 * Created by luoling on 2019/11/29.
 * description:
 */
class GrabageView(context:Context,attrs:AttributeSet?,defStyleAttr: Int) : View(context, attrs,defStyleAttr) {

    constructor(context: Context,attrs: AttributeSet?):this(context,attrs,0){}

    constructor(context: Context):this(context,null){}

    private var paint: Paint
    private var headPath: Path
    private var bodyPath: Path
    private var delta: Int = 0
    private var valueAnimator: ValueAnimator? = null

    init {
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.setStrokeWidth(2f)
        paint.setStyle(Paint.Style.STROKE)
        paint.setColor(Color.RED)
        headPath = Path()
        bodyPath = Path()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        headPath.reset()
        bodyPath.reset()

        //画盖子
        headPath.moveTo((width * 4 / 20).toFloat(), (height * 6 / 20).toFloat())
        headPath.lineTo((width * 16 / 20).toFloat(), (height * 6 / 20).toFloat())
        headPath.moveTo((width * 9 / 20).toFloat(), (height * 6 / 20).toFloat())
        headPath.lineTo((width * 9 / 20).toFloat(), (height * 5 / 20).toFloat())
        headPath.lineTo((width * 11 / 20).toFloat(), (height * 5 / 20).toFloat())
        headPath.lineTo((width * 11 / 20).toFloat(), (height * 6 / 20).toFloat())

        //画桶
        bodyPath.moveTo((width * 5 / 20).toFloat(), (height * 6 / 20).toFloat())
        bodyPath.lineTo((width * 7 / 20).toFloat(), (height * 15 / 20).toFloat())
        bodyPath.lineTo((width * 13 / 20).toFloat(), (height * 15 / 20).toFloat())
        bodyPath.lineTo((width * 15 / 20).toFloat(), (height * 6 / 20).toFloat())
        bodyPath.moveTo((width * 8 / 20).toFloat(), (height * 8 / 20).toFloat())
        bodyPath.lineTo((width * 8 / 20).toFloat(), (height * 15 / 20).toFloat())
        bodyPath.moveTo((width * 12 / 20).toFloat(), (height * 8 / 20).toFloat())
        bodyPath.lineTo((width * 12 / 20).toFloat(), (height * 15 / 20).toFloat())

        canvas.drawPath(bodyPath, paint)
        canvas.save()
        canvas.rotate(delta.toFloat(), (width * 16 / 20).toFloat(), (height * 6 / 20).toFloat())
        canvas.drawPath(headPath, paint)
        canvas.restore()
    }

    fun startAnim(){
        valueAnimator = ValueAnimator.ofInt(0,30,0)
        valueAnimator?.duration = 1600
        valueAnimator?.addUpdateListener {
            delta = it.animatedValue as Int
            invalidate()
        }
        valueAnimator?.addListener(
            onStart = {},
            onEnd = {},
            onCancel = {},
            onRepeat = {}
        )
        valueAnimator?.start()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        when(event.action){
            MotionEvent.ACTION_DOWN -> {
                valueAnimator?.cancel()
                delta = 0
                startAnim()
            }
        }
        return true
    }

}