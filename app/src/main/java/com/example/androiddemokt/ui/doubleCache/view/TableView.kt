package com.example.androiddemokt.ui.doubleCache.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * Created by luoling on 2019/11/27.
 * description:
 */
class TabletView constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    constructor(context: Context,attrs: AttributeSet?):this(context, attrs,0){}
    constructor(context: Context):this(context,null){}

    /**上一个点的坐标 */
    private var preX: Int = 0
    private var preY: Int = 0
    /**当前点的坐标 */
    private var currentX: Int = 0
    private var currentY: Int = 0
    /**Bitmap 缓冲区 */
    private var bitmapButter: Bitmap? = null
    private var bitmapCanvas: Canvas? = null
    private val paint: Paint

    init {
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.isAntiAlias = true
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeCap = Paint.Cap.ROUND
        paint.style = Paint.Style.STROKE
        paint.color = Color.RED
        paint.strokeWidth = 13f
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (bitmapButter == null) {
            val width = measuredWidth
            val height = measuredHeight
            bitmapButter = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            bitmapCanvas = Canvas(bitmapButter!!)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmapButter!!, 0f, 0f, null)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x.toInt()
        val y = event.y.toInt()
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                preX = x
                preY = y
            }
            MotionEvent.ACTION_MOVE -> {
                currentX = x
                currentY = y
                bitmapCanvas!!.drawLine(
                    preX.toFloat(),
                    preY.toFloat(),
                    currentX.toFloat(),
                    currentY.toFloat(),
                    paint
                )
                invalidate()
                preX = currentX
                preY = currentY
            }
            MotionEvent.ACTION_UP -> invalidate()
        }
        return true
    }
}