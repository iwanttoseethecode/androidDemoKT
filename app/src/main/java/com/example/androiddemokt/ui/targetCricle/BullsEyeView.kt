package com.example.androiddemokt.ui.targetCricle

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * Created by luoling on 2019/12/2.
 * description:
 */
class BullsEyeView(context: Context,attributeSet: AttributeSet?,defStyleAttr:Int) : View(context,attributeSet,defStyleAttr){

    constructor(context:Context,attributeSet: AttributeSet?):this(context,attributeSet,0){}

    constructor(context: Context):this(context,null){}

    lateinit var mPaint:Paint
    lateinit var mCenter: PointF
    var mRadius:Float = 0f

    init {
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint.style = Paint.Style.FILL
        mCenter = PointF()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        var mWidth:Int = 0
        var mHeight:Int = 0

        var contentWidth = 100
        var contentHeight = 100

        mWidth = getMeasurement(widthMeasureSpec,contentWidth)
        mHeight = getMeasurement(heightMeasureSpec,contentHeight)

        setMeasuredDimension(mWidth,mHeight)
    }

    fun getMeasurement(measureSpec:Int,contentSize:Int):Int{
        var specSize = MeasureSpec.getSize(measureSpec)
        var specMode = MeasureSpec.getMode(measureSpec)
        when(specMode){
            MeasureSpec.EXACTLY ->{
                return Math.min(specSize,contentSize)
            }
            MeasureSpec.AT_MOST -> {
                return contentSize
            }
            MeasureSpec.UNSPECIFIED ->{
                return specSize
            }
        }
        return 0;
    }

    //测量完后，调用onSizeChanged()收集一些所需要的基本数据来绘制目标圆
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (w != oldw || h != oldh){
            //如果有变化则复位参数
            mCenter.x = (w / 2).toFloat()
            mCenter.y = (h / 2).toFloat()
            mRadius = Math.min(mCenter.x,mCenter.y);
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        mPaint.color = Color.RED
        canvas.drawCircle(mCenter.x,mCenter.y,mRadius,mPaint)

        mPaint.color = Color.WHITE
        canvas.drawCircle(mCenter.x,mCenter.y,mRadius * 0.8f,mPaint)

        mPaint.color = Color.RED
        canvas.drawCircle(mCenter.x,mCenter.y,mRadius * 0.6f,mPaint)

        mPaint.color =Color.WHITE
        canvas.drawCircle(mCenter.x, mCenter.y, mRadius * 0.4f, mPaint)

        mPaint.color = Color.RED
        canvas.drawCircle(mCenter.x, mCenter.y, mRadius * 0.1f, mPaint)
    }

}