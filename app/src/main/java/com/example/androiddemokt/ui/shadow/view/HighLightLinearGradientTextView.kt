package com.example.androiddemokt.ui.shadow.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

/**
 * Created by luoling on 2019/11/28.
 * description:
 */
class HighLightLinearGradientTextView(context: Context,attrs: AttributeSet?,defStyleAttr:Int ) : AppCompatTextView(context, attrs, defStyleAttr) {

    constructor(context: Context,attrs: AttributeSet?):this(context,attrs,0){}

    constructor(context: Context):this(context,null){}

    lateinit var mPaint:Paint
    lateinit var mLinearGradient:LinearGradient
    lateinit var mMatrix:Matrix
    var mTranslate:Float = 0f

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        //获取TextView的画笔
        mPaint = paint
        mMatrix = Matrix()
        var text = getText().toString()
        var textWith = mPaint.measureText(text)
        var gradientSize = textWith / text.length * 3
        mLinearGradient = LinearGradient(-gradientSize,0f,0f,0f, intArrayOf(
            0x22ffffff,
            -0x1,//白色的补码
            0x22ffffff),null,
            Shader.TileMode.CLAMP)
        mPaint.shader = mLinearGradient

        startAnimator(textWith)
    }

    fun startAnimator(rangeWidth:Float){
        var valueAnimator = ValueAnimator.ofFloat(0f,rangeWidth)
        valueAnimator.duration = 4000
        valueAnimator.repeatMode = ValueAnimator.REVERSE
        valueAnimator.repeatCount = ValueAnimator.INFINITE
        valueAnimator.startDelay = 50
        valueAnimator.addUpdateListener {
            mTranslate = it.animatedValue as Float
            invalidate()
        }
        valueAnimator.start()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mMatrix.setTranslate(mTranslate,0f)
        mLinearGradient.setLocalMatrix(mMatrix)

    }

}