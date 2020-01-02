package com.example.androiddemokt.ui.shadow.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * Created by luoling on 2019/11/28.
 * description:
 */
class GradientAndMatrixView(context: Context,attrs:AttributeSet?,defStyleAttr:Int) : View(context,attrs,defStyleAttr) {

    constructor(context: Context,attrs: AttributeSet?):this(context,attrs,0){}

    constructor(context: Context):this(context,null){}

    var mPaint:Paint
    var mRotate:Float = 0f
    var mMatrix :Matrix
    var mShader :Shader

    init {
        mShader = SweepGradient(180f,100f, intArrayOf(Color.RED, Color.GREEN,Color.BLUE),
            floatArrayOf(0f,0.5f,1f))
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint.shader = mShader
        mMatrix = Matrix()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        startAnimator()
    }

    fun startAnimator(){
        var valueAnimator = ValueAnimator.ofFloat(0f,360f)
        valueAnimator.duration = 5000
        valueAnimator.repeatCount = ValueAnimator.INFINITE
        valueAnimator.repeatMode = ValueAnimator.RESTART
        valueAnimator.startDelay = 50
        valueAnimator.addUpdateListener {
            mRotate = it.animatedValue as Float
            invalidate()
        }
        valueAnimator.start()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.save()
        canvas?.translate(300f,300f)
//        canvas?.drawColor(Color.WHITE)
        mMatrix.setRotate(mRotate,180f,100f)
        mShader.setLocalMatrix(mMatrix)
        canvas?.drawCircle(180f,100f,180f,mPaint)
        canvas?.restore()
    }

}