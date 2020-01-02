package com.example.androiddemokt.ui.pathMeasure.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * Created by luoling on 2019/11/29.
 * description:
 */
class LoadingView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : View(context, attrs,defStyleAttr){

    constructor(context: Context,attrs: AttributeSet?):this(context,attrs,0){}

    constructor(context: Context):this(context,null){}

    var mPaint:Paint
    var mPath:Path
    lateinit var pathMeasure :PathMeasure
    var animatorValue:Float = 0f
    lateinit var dstPath:Path
    var mLength:Float = 0f

    init {
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 5f
        mPaint.color = Color.argb(210,210,210,210)
        mPath = Path()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mPath.addCircle((width/2).toFloat(), (height/2).toFloat(),100f,Path.Direction.CW)
        pathMeasure = PathMeasure(mPath,true)
        mLength = pathMeasure.length
        dstPath = Path()

        var valueAnimator = ValueAnimator.ofFloat(0f,1f)
        valueAnimator.addUpdateListener {
            animatorValue = it.animatedValue as Float
            invalidate()
        }
        valueAnimator.duration = 2000
        valueAnimator.repeatCount = ValueAnimator.INFINITE
        valueAnimator.repeatMode = ValueAnimator.RESTART
        valueAnimator.start()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        dstPath.reset()
        var stop = mLength * animatorValue
        var start = (stop - (0.5 - Math.abs(animatorValue - 0.5)) * mLength).toFloat()
        pathMeasure.getSegment(start,stop,dstPath,true)
        canvas.drawPath(dstPath,mPaint)
    }



}