package com.example.androiddemokt.ui.pathMeasure.view

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import android.view.View
import com.example.androiddemokt.R
import com.example.androiddemokt.utils.UIUtil.dp2px

/**
 * Created by luoling on 2019/11/30.
 * description:
 */
class HeadView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    View(context, attrs, defStyleAttr) {

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {}

    constructor(context: Context) : this(context, null) {}

    var mPaint: Paint
    var besselViewBackGroundDrawable: BitmapDrawable
    var numberText: String
    var numberTextColor: Int = 0
    var numberTextSize: Float = 0f
    var unitText: String
    var unitTextColor: Int = 0
    var unitTextSize: Float = 0f
    var describeText: String
    var describeTextColor: Int = 0
    var describeTextSize: Float = 0f

    init {
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

        var typeArray = context.obtainStyledAttributes(attrs, R.styleable.HeadView)
        besselViewBackGroundDrawable =
            typeArray.getDrawable(R.styleable.HeadView_besselViewBackGround) as BitmapDrawable
        numberText = typeArray.getString(R.styleable.HeadView_numberText).toString()
        numberTextColor = typeArray.getColor(R.styleable.HeadView_numberTextColor, Color.WHITE)
        numberTextSize = typeArray.getDimension(R.styleable.HeadView_numberTextSize, 10f)
        unitText = typeArray.getString(R.styleable.HeadView_unitText).toString()
        unitTextColor = typeArray.getColor(R.styleable.HeadView_unitTextColor, Color.WHITE)
        unitTextSize = typeArray.getDimension(R.styleable.HeadView_unitTextSize, 10f)
        describeText = typeArray.getString(R.styleable.HeadView_describeText).toString()
        describeTextColor = typeArray.getColor(R.styleable.HeadView_describeTextColor, Color.WHITE)
        describeTextSize = typeArray.getDimension(R.styleable.HeadView_describeTextSize, 10f)

        typeArray.recycle()

        setBackgroundColor(Color.WHITE)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        var warpContentSize = dp2px(200)

        var width = getMeasurement(widthMeasureSpec, warpContentSize)
        var height = getMeasurement(heightMeasureSpec, warpContentSize)

        setMeasuredDimension(width, height)
    }

    fun getMeasurement(measureSpec: Int, contentSize: Int): Int {
        var measureSize = MeasureSpec.getSize(measureSpec)
        var measureMode = MeasureSpec.getMode(measureSpec)

        var result = 0

        when (measureMode) {
            MeasureSpec.EXACTLY -> {
                result = measureSize
            }
            MeasureSpec.AT_MOST -> {
                result = Math.min(measureSize, contentSize)
            }
            MeasureSpec.UNSPECIFIED -> {
                result = contentSize
            }
        }
        return result;
    }

    override fun onDraw(canvas: Canvas) {
        mPaint.reset()

        var layerId = canvas.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), mPaint)

        besselViewBackGround(canvas)

        mPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)

        drawTopText(canvas)
        canvas.restoreToCount(layerId)
    }

    fun besselViewBackGround(canvas: Canvas) {
        mPaint.style = Paint.Style.FILL
        mPaint.isAntiAlias = true
        if (besselViewBackGroundDrawable != null && besselViewBackGroundDrawable is BitmapDrawable) {
            var bitmapShader = BitmapShader(
                besselViewBackGroundDrawable.bitmap,
                Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP
            )
            mPaint.shader = bitmapShader
        }
        var path = Path()
        path.moveTo(0f, 0f)
        path.lineTo(width.toFloat(), 0f)
        path.lineTo(width.toFloat(), height.toFloat())
        path.moveTo(0f, 0f)
        path.lineTo(0f, height.toFloat())
        path.quadTo(
            width.toFloat() / 2,
            (height * 7 / 8).toFloat(),
            width.toFloat(),
            height.toFloat()
        )
        path.close()

        canvas.drawPath(path, mPaint)
    }

    fun getTextRect(text: String): Rect {
        var rect = Rect()
        mPaint.getTextBounds(text, 0, text.length, rect)
        return rect
    }

    fun drawTopText(canvas: Canvas) {
        mPaint.color = numberTextColor
        mPaint.textSize = numberTextSize
        var rect1 = getTextRect(numberText)
        var fontMetrics = mPaint.fontMetrics

        var center = height / 3

        var baseLine = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom + center

        val rect2 = getTextRect(unitText)

        //单位和数字之间的间隔
        val space = unitTextSize / 2

        val numberTextX = (width - (rect1.width() + space + rect2.width())) / 2

        //画数字
        canvas.drawText(numberText, numberTextX.toFloat(), baseLine, mPaint)

        //画单位
        mPaint.textSize = unitTextSize
        mPaint.color = unitTextColor

        val unitTextX = numberTextX + rect1.width() + space
        canvas.drawText(unitText, unitTextX.toFloat(), baseLine, mPaint)
    }

    fun drawDescribeText(canvas: Canvas) {
        mPaint.color = describeTextColor
        mPaint.textSize = describeTextSize

        val rect = getTextRect(describeText)

        val fontMetrics = mPaint.fontMetrics

        val baseLine = height * 2 / 3 - (fontMetrics.bottom - fontMetrics.top - fontMetrics.bottom)

        canvas.drawText(describeText, ((width - rect.width()) / 2).toFloat(), baseLine, mPaint)
    }
}