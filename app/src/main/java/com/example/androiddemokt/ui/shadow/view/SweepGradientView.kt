package com.example.androiddemokt.ui.shadow.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator

/**
 * Created by luoling on 2019/11/28.
 * description:
 */
class SweepGradientView(context: Context,attrs:AttributeSet?,defStyleAttrs:Int) :
    View(context,attrs,defStyleAttrs) {

    constructor(context: Context,attrs: AttributeSet?):this(context,attrs,0){}

    constructor(context: Context):this(context,null){}

    var type = TYPE.TYPE1

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        startAnimator()
    }

    fun startAnimator(){
        var animator = ValueAnimator.ofFloat(0f,1f)
        animator.duration = 4000
        animator.repeatMode = ValueAnimator.REVERSE
        animator.repeatCount = ValueAnimator.INFINITE
        animator.interpolator = LinearInterpolator()
        animator.addUpdateListener {
            var num = it.animatedValue as Float
            if (num <= 0.5f) type = TYPE.TYPE1 else if (num >= 0.5f) type =TYPE.TYPE2
            invalidate()
        }
        animator.startDelay = 40
        animator.start()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        var paint = Paint(Paint.ANTI_ALIAS_FLAG)
        var sg = if (type == TYPE.TYPE1) getSweepGradient1() else getSweepGradient2()
        paint.shader = sg
        canvas?.drawOval(RectF(200f,200f,800f,800f),paint)

    }

    fun getSweepGradient1():SweepGradient{
        return SweepGradient(500f,500f, Color.GREEN,Color.YELLOW)
    }

    fun getSweepGradient2():SweepGradient{
        return SweepGradient(500f,500f, intArrayOf(Color.RED,Color.GREEN,Color.BLUE), floatArrayOf(0f,0.4f,1f))
    }

    enum class TYPE{
        TYPE1,TYPE2
    }

}