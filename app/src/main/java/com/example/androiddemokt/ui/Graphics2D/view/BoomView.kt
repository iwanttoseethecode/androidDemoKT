package com.example.androiddemokt.ui.Graphics2D.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import com.example.androiddemokt.R

/**
 * Created by luoling on 2019/11/27.
 * description:
 */
class BoomView(context: Context,attributeSet: AttributeSet?,defStyleAttrs:Int) : View(context,attributeSet,defStyleAttrs) {

    var boomBmp :Bitmap

    var boomFrameWidth = 0
    var boomFrameHeight = 0
    var frameCount = 7 //爆炸图片有7帧
    var currentFrame = 0;

    constructor(context: Context,attributeSet: AttributeSet?):this(context,attributeSet,0){}

    constructor(context: Context):this(context,null){}

    init {
        boomBmp = BitmapFactory.decodeResource(resources, R.mipmap.boompic)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        boomFrameWidth = boomBmp.width / frameCount
        boomFrameHeight = boomBmp.height

        var width = MeasureSpec.getSize(widthMeasureSpec)
        var widthMode = MeasureSpec.getMode(widthMeasureSpec)
        var height = MeasureSpec.getSize(heightMeasureSpec)
        var heightMode = MeasureSpec.getMode(heightMeasureSpec)

        var measureWidth = 0
        var measureHeight = 0

        if (measureWidth == MeasureSpec.EXACTLY)
            measureWidth = width
        else if (measureWidth == MeasureSpec.AT_MOST)
            measureWidth = boomFrameWidth
        else
            measureWidth = boomFrameWidth

        if (measureHeight == MeasureSpec.EXACTLY)
            measureHeight = height
        else if (measureHeight == MeasureSpec.AT_MOST)
            measureHeight = boomFrameHeight
        else
            measureHeight = boomFrameHeight
        setMeasuredDimension(measureWidth,measureHeight)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        startAnimation()
    }

    fun startAnimation(){
        var valueAnimator = ValueAnimator.ofInt(0,frameCount)
        valueAnimator.duration = 700
        valueAnimator.repeatMode = ValueAnimator.RESTART
        valueAnimator.repeatCount = ValueAnimator.INFINITE
        valueAnimator.addUpdateListener {
            currentFrame = it.animatedValue as Int
            invalidate()
        }
        valueAnimator.startDelay = 30
        valueAnimator.start()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        var rect = Rect(0,0,boomFrameWidth,boomFrameHeight)
        canvas?.clipRect(rect)
        canvas?.drawBitmap(boomBmp,(-currentFrame * boomFrameWidth).toFloat(),0f,null)

    }

}