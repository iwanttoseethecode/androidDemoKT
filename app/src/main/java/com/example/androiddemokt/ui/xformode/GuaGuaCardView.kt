package com.example.androiddemokt.ui.xformode

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.androiddemokt.R
import java.util.*

/**
 * Created by luoling on 2019/11/26.
 * description:
 */
class GuaGuaCardView constructor(context:Context, attrs: AttributeSet?,defStyleAttrs:Int ):
    View(context,attrs,defStyleAttrs){

    constructor(context: Context,attrs: AttributeSet?):this(context,attrs,0){}

    constructor(context: Context):this(context,null){}

    lateinit var mPaint: Paint
    lateinit var bmpDst:Bitmap
    lateinit var bmpSrc:Bitmap
    lateinit var mPath: Path
    var mPreX:Float = 0f
    var mPreY:Float = 0f

    val PRIZE = arrayOf("无形之刃最为致命", "带你装逼带你飞", "带你走近垃圾堆", "大王叫我来巡山", "逗你玩呢")

    init {
        setLayerType(View.LAYER_TYPE_SOFTWARE,null)
        mPaint = Paint()
        mPaint.color = Color.RED
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth=45f
        mPaint.strokeCap = Paint.Cap.ROUND
        bmpSrc = BitmapFactory.decodeResource(resources, R.mipmap.guaguaka,null)
        bmpDst = Bitmap.createBitmap(bmpSrc.width,bmpSrc.height,Bitmap.Config.ARGB_8888)
        mPath = Path()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var width = MeasureSpec.getSize(widthMeasureSpec)
        var height = MeasureSpec.getSize(heightMeasureSpec)
        var widthMode = MeasureSpec.getMode(widthMeasureSpec)
        var heightMode = MeasureSpec.getMode(heightMeasureSpec)

        var measureWidth = 0
        var measureHeight = 0

        if (widthMode == MeasureSpec.EXACTLY)
            measureWidth = width
        else if (widthMode == MeasureSpec.AT_MOST)
            measureWidth = bmpSrc.width
        else
            measureWidth = bmpSrc.width

        if (heightMode == MeasureSpec.EXACTLY)
            measureHeight = height
        else if (heightMode == MeasureSpec.AT_MOST)
            measureHeight = bmpSrc.height
        else
            measureHeight = bmpSrc.height

        setMeasuredDimension(measureWidth,measureHeight)

    }

    fun getRandom():Int{
        var random = Random()
        return random.nextInt(PRIZE.size)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        var bmpBg = Bitmap.createBitmap(bmpSrc.width,bmpSrc.height,Bitmap.Config.ARGB_8888)
        var canvas = Canvas(bmpBg)

        var text = PRIZE[getRandom()]

        var paint = Paint()
        var rect = Rect()
        paint.getTextBounds(text,0,text.length,rect)
        paint.textSize = 35f
        var fontMetrics = paint.fontMetrics

        var center = height / 2

        var baseLine = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom + center

        var x = (width - rect.width()) / 2
        canvas.drawText(text,x.toFloat(),baseLine,paint)

        this.setBackground(BitmapDrawable(resources,bmpBg))
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action){
            MotionEvent.ACTION_DOWN ->
            {
                mPreX = event.getX()
                mPreY = event.getY()
            }
            MotionEvent.ACTION_MOVE ->
            {
                var endX = event.getX()
                var endY = event.getY()

                var controlX = (mPreX + endX) / 2
                var controlY = (mPreY + endY ) / 2

                mPath.cubicTo(mPreX,mPreY,controlX,controlY,endX,endY)

                mPreX = endX
                mPreY = endY
            }
            MotionEvent.ACTION_UP ->
            {
                mPreX = 0f
                mPreY = 0f
            }
        }
        invalidate()
        return true
    }

    override fun onDraw(canvas: Canvas?) {

        var layerId = canvas?.saveLayer(0f,0f,width.toFloat(),height.toFloat(),mPaint)

        var c = Canvas(bmpDst)
        c.drawPath(mPath,mPaint)


        canvas?.drawBitmap(bmpDst,0f,0f,mPaint)
        mPaint.setXfermode(PorterDuffXfermode(PorterDuff.Mode.SRC_OUT))
        canvas?.drawBitmap(bmpSrc,0f,0f,mPaint)


        mPaint.setXfermode(null)
        canvas?.restoreToCount(layerId!!)


    }

}