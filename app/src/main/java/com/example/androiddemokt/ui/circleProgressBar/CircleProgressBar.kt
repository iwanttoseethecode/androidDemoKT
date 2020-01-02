package com.example.androiddemokt.ui.circleProgressBar

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.androiddemokt.R

/**
 * Created by luoling on 2019/12/2.
 * description:
 */
class CircleProgressBar(context:Context,attrs:AttributeSet ) : View(context,attrs) {

    private val max: Int
    private val roundColor: Int
    private val roundProgressColor: Int
    private val textColor: Int
    private val textSize: Float
    private val roundWidth: Float
    private val textShow: Boolean
    private var progress: Int = 0
    private val paint: Paint

    init {
        paint = Paint()
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomProgressBar)
        max = typedArray.getInteger(R.styleable.CustomProgressBar_max, 100)
        roundColor = typedArray.getColor(R.styleable.CustomProgressBar_roundColor, 0)
        roundProgressColor =
            typedArray.getColor(R.styleable.CustomProgressBar_roundProgressColor, Color.BLUE)
        textColor = typedArray.getColor(R.styleable.CustomProgressBar_textColor, Color.BLUE)
        textSize = typedArray.getDimension(R.styleable.CustomProgressBar_textSize, 55f)
        roundWidth = typedArray.getDimension(R.styleable.CustomProgressBar_roundWidth, 10f)
        textShow = typedArray.getBoolean(R.styleable.CustomProgressBar_textShow, true)

        typedArray.recycle()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //画背景圆环
        val center = width / 2

        val radius = center - roundWidth / 2
        paint.color = roundColor
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = roundWidth
        paint.isAntiAlias = true
        canvas.drawCircle(center.toFloat(), center.toFloat(), radius, paint)

        //画进度百分比
        paint.color = textColor
        paint.strokeWidth = 0f
        paint.textSize = textSize
        paint.typeface = Typeface.DEFAULT_BOLD

        val percent = (progress / max.toFloat() * 100).toInt()

        val strPercent = "$percent%"
        val fm = paint.fontMetrics
        if (percent != 0) {
            canvas.drawText(
                strPercent, width / 2 - paint.measureText(strPercent) / 2,
                width / 2 + (fm.bottom - fm.top) / 2 - fm.bottom, paint
            )
        }

        //画圆弧
        val oval = RectF(center - radius, center - radius, center + radius, center + radius)
        paint.color = roundProgressColor
        paint.strokeWidth = roundWidth
        paint.style = Paint.Style.STROKE
        paint.strokeCap = Paint.Cap.ROUND
        canvas.drawArc(oval, 0f, (360 * progress / max).toFloat(), false, paint)
    }

    fun setProgress(progress: Int) {
        var progress = progress
        require(progress >= 0) { "进度Progress不能小于0" }
        if (progress > max) {
            progress = max
        }
        if (progress <= max) {
            this.progress = progress
            postInvalidate()
        }
    }

    companion object {
        val STROKE = 0
        val FILL = 1
    }
}
