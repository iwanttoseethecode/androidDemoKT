package com.example.androiddemokt.ui.xformode

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.ViewTreeObserver
import com.example.androiddemokt.R

/**
 * Created by luoling on 2019/11/25.
 * description:
 */
class HeartMapView constructor(context: Context,attrs:AttributeSet?,defStyleAttr:Int ) :View(context, attrs, defStyleAttr){

    constructor(context:Context,attrs: AttributeSet?):this(context,attrs,0){}

    constructor(context: Context):this(context,null){}

    private lateinit var mPaint:Paint
    private var mSrcWidth:Int = 0
    private var dx:Int = 1
    private lateinit var BmpSrc:Bitmap

    init {
        mPaint = Paint()
        mPaint.color = Color.RED
        mPaint.isAntiAlias = true
        BmpSrc = BitmapFactory.decodeResource(resources, R.mipmap.heartmap,null)
        mSrcWidth = BmpSrc.width
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var width:Int = MeasureSpec.getSize(widthMeasureSpec)
        var widthMode = MeasureSpec.getMode(widthMeasureSpec)
        var height:Int = MeasureSpec.getSize(heightMeasureSpec)
        var heightMode = MeasureSpec.getMode(heightMeasureSpec)

        var measureWidth = 0;
        var measureHeight = 0;

        if (widthMeasureSpec == MeasureSpec.EXACTLY)
            measureWidth = width
        else if (widthMeasureSpec == MeasureSpec.AT_MOST)
            measureWidth = BmpSrc.width
        else
            measureWidth = BmpSrc.width

        if (heightMode == MeasureSpec.EXACTLY)
            measureHeight = height
        else if (heightMode == MeasureSpec.AT_MOST)
            measureHeight = BmpSrc.height
        else
            measureHeight = BmpSrc.height

        setMeasuredDimension(measuredWidth,measuredHeight)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        startAnim()

    }

    fun startAnim(){
        var animator = ValueAnimator.ofInt(mSrcWidth,1)//因为图片的宽高必须要大于零
        animator.addUpdateListener {
            dx = it.animatedValue as Int
            invalidate()
        }
        animator.duration = 6000
        animator.repeatMode = ValueAnimator.RESTART
        animator.repeatCount = ValueAnimator.INFINITE
        animator.start()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var layerId = canvas?.saveLayer(0f,0f,width.toFloat(),height.toFloat(),mPaint)

        canvas?.drawBitmap(BmpSrc,0f,0f,mPaint)
        mPaint.setXfermode(PorterDuffXfermode(PorterDuff.Mode.SRC_IN))
        var bmpDst = Bitmap.createBitmap(dx,BmpSrc.height,Bitmap.Config.ARGB_8888)
        canvas?.drawBitmap(bmpDst,0f,0f,mPaint)
        mPaint.setXfermode(null)
        canvas?.restoreToCount(layerId!!)
    }

}