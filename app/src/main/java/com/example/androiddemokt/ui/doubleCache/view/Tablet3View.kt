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
class Tablet3View constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) :
    View(context, attrs, defStyleAttr) {

    constructor(context: Context,attrs: AttributeSet?):this(context, attrs,0){}
    constructor(context: Context):this(context,null){}

    private val path: Path
    private var preX: Int = 0
    private var preY: Int = 0
    private val paint: Paint
    private var bitmapBuffer: Bitmap? = null
    private var bitmapCanvas: Canvas? = null

    init {
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.isAntiAlias = true
        paint.strokeCap = Paint.Cap.ROUND
        paint.style = Paint.Style.STROKE
        paint.isAntiAlias = true
        paint.strokeWidth = 25f
        paint.color = Color.RED
        path = Path()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (bitmapBuffer == null) {
            val width = measuredWidth
            val height = measuredHeight
            bitmapBuffer = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            bitmapCanvas = Canvas(bitmapBuffer!!)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmapBuffer!!, 0f, 0f, null)
        canvas.drawPath(path, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x.toInt()
        val y = event.y.toInt()
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.reset()
                preX = x
                preY = y
                path.moveTo(x.toFloat(), y.toFloat())
            }
            MotionEvent.ACTION_MOVE -> {
                //使用贝塞尔曲线可以使路径更加平滑
                path.quadTo(
                    ((preX + x) / 2).toFloat(),
                    ((preY + y) / 2).toFloat(),
                    x.toFloat(),
                    y.toFloat()
                )
                invalidate()
                preX = x
                preY = y
            }
            MotionEvent.ACTION_UP -> {
                bitmapCanvas!!.drawPath(path, paint)
                invalidate()
            }
        }
        return true
    }
}
