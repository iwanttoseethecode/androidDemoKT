package com.example.luoling.android_dome.xformode

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.androiddemokt.R

class EraserView : View {

    private var mBitPaint: Paint? = null
    private var BmpDst: Bitmap? = null
    private var BmpSrc: Bitmap? = null
    private var mPath: Path? = null
    private var mPreX: Float = 0.toFloat()
    private var mPreY: Float = 0.toFloat()

    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
    }

    init{
        setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        mBitPaint = Paint()
        mBitPaint!!.color = Color.RED
        mBitPaint!!.style = Paint.Style.STROKE
        mBitPaint!!.strokeCap = Paint.Cap.ROUND
        mBitPaint!!.strokeWidth = 45f

        BmpSrc = BitmapFactory.decodeResource(resources, R.mipmap.p4)
        BmpDst = Bitmap.createBitmap(BmpSrc!!.width, BmpSrc!!.height, Bitmap.Config.ARGB_8888)
        mPath = Path()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = View.MeasureSpec.getSize(widthMeasureSpec)
        val widthMode = View.MeasureSpec.getMode(widthMeasureSpec)
        val height = View.MeasureSpec.getSize(heightMeasureSpec)
        val heightMode = View.MeasureSpec.getMode(heightMeasureSpec)

        var measureWidth = 0
        var measureHeight = 0

        if (widthMode == View.MeasureSpec.EXACTLY) {
            measureWidth = width
        } else if (widthMode == View.MeasureSpec.AT_MOST) {
            measureWidth = BmpDst!!.width
        }

        if (heightMode == View.MeasureSpec.EXACTLY) {
            measureHeight = height
        } else if (heightMode == View.MeasureSpec.AT_MOST) {
            measureHeight = BmpDst!!.height
        }

        setMeasuredDimension(measureWidth, measureHeight)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val layerId =
            canvas.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), mBitPaint)

        val c = Canvas(BmpDst!!)
        c.drawPath(mPath!!, mBitPaint!!)

        canvas.drawBitmap(BmpDst!!, 0f, 0f, mBitPaint)

        mBitPaint!!.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_OUT)
        canvas.drawBitmap(BmpSrc!!, 0f, 0f, mBitPaint)

        mBitPaint!!.xfermode = null
        canvas.restoreToCount(layerId)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                mPreX = event.x
                mPreY = event.y
                mPath!!.moveTo(mPreX, mPreY)
            }
            MotionEvent.ACTION_MOVE -> {
                val endX = (mPreX + event.x) / 2
                val endY = (mPreY + event.y) / 2
                mPath!!.quadTo(mPreX, mPreY, endX, endY)
                mPreX = event.x
                mPreY = event.y
            }
            MotionEvent.ACTION_UP -> {
            }
        }
        postInvalidate()
        return true
    }

}
