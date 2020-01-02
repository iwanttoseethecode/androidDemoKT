package com.example.androiddemokt.ui.xformode

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.LinearInterpolator
import com.example.androiddemokt.R

/**
 * Created by luoling on 2019/11/25.
 * description:
 */
class CircleWaveView constructor(context: Context,attrs:AttributeSet?,defStyleAttr:Int):View(context, attrs, defStyleAttr) {

    constructor(context: Context,attrs: AttributeSet?):this(context,attrs,0){}

    constructor(context: Context):this(context,null){}

    lateinit var mPaint: Paint
    lateinit var bmpSrc:Bitmap
    lateinit var bmpDst:Bitmap
    var dx:Int = 0
    var mItemWaveLength = 0
    //src图片的x坐标
    var mSrcBmpLocation = 0

    init {
        mPaint = Paint()
        mPaint.isAntiAlias = true
        bmpSrc = BitmapFactory.decodeResource(resources, R.mipmap.circle_shape)
        bmpDst = BitmapFactory.decodeResource(resources, R.mipmap.wave_bg)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var width = MeasureSpec.getSize(widthMeasureSpec)
        var widthMode = MeasureSpec.getMode(widthMeasureSpec)
        var height = MeasureSpec.getSize(heightMeasureSpec)
        var heightMode = MeasureSpec.getMode(heightMeasureSpec)

        var measureWidth = 0
        var measureHeight = 0

        if (widthMode == MeasureSpec.EXACTLY)
            measureWidth = width
        else if (widthMode == MeasureSpec.AT_MOST)
            measureWidth = bmpDst.width
        else
            measureWidth = bmpDst.width

        if (heightMode == MeasureSpec.EXACTLY)
            measureHeight = width
        else if (heightMode == MeasureSpec.AT_MOST)
            measureHeight = bmpDst.height
        else
            measureHeight = bmpDst.height

        setMeasuredDimension(measuredWidth,measuredHeight)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        //设置dst波浪图可位移变化范围
        mItemWaveLength = bmpDst.width - bmpSrc.width

        mSrcBmpLocation = (width-bmpSrc.width)/2

        startAnim()

    }

    fun startAnim(){
        var animator = ValueAnimator.ofInt(0,mItemWaveLength)
        animator.duration = 6000
        animator.repeatCount = ValueAnimator.INFINITE
        animator.repeatMode = ValueAnimator.REVERSE
        animator.interpolator = LinearInterpolator()
        animator.addUpdateListener {
            dx = it.animatedValue as Int
            invalidate()
        }
        animator.start()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        var layerId = canvas?.saveLayer(0f,0f,width.toFloat(),height.toFloat(),mPaint)

        canvas?.translate(mSrcBmpLocation.toFloat(),0f)

        canvas?.drawBitmap(bmpSrc,0f,0f,mPaint)

        mPaint.setXfermode(PorterDuffXfermode(PorterDuff.Mode.SRC_IN))

        canvas?.drawBitmap(bmpDst,-dx.toFloat(),0f,mPaint)
        mPaint.setXfermode(null)

        canvas?.restore()
        canvas?.restoreToCount(layerId!!)
    }


}