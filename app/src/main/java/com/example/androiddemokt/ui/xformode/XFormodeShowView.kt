package com.example.androiddemokt.ui.xformode

import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.View

/**
 * Created by luoling on 2019/11/25.
 * description:
 */
class XFormodeShowView constructor(context:Context,attrs:AttributeSet?,defStyleAttr:Int ) : View(context, attrs, defStyleAttr){
    var mPaint: Paint? = null
    var itemSize:Float = 0f
    var itemHorizontalOffset = 0f
    var itemVerticalOffset = 0f
    var mCircleRadius:Float = 0f
    var mRectSize:Float = 0f
    var mCircleColor:Long = 0xffffcc44//黄色
    var mRectColor:Long = 0xff66aaff//蓝色
    var mTextSize:Float = 25f;

    constructor(context:Context,attrs: AttributeSet?):this(context,attrs,0){}

    companion object{
        val sModes = arrayOf(
            PorterDuffXfermode(PorterDuff.Mode.CLEAR),
            PorterDuffXfermode(PorterDuff.Mode.SRC),
            PorterDuffXfermode(PorterDuff.Mode.DST),
            PorterDuffXfermode(PorterDuff.Mode.SRC_OVER),
            PorterDuffXfermode(PorterDuff.Mode.DST_OVER),
            PorterDuffXfermode(PorterDuff.Mode.SRC_IN),
            PorterDuffXfermode(PorterDuff.Mode.DST_IN),
            PorterDuffXfermode(PorterDuff.Mode.SRC_OUT),
            PorterDuffXfermode(PorterDuff.Mode.DST_OUT),
            PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP),
            PorterDuffXfermode(PorterDuff.Mode.DST_ATOP),
            PorterDuffXfermode(PorterDuff.Mode.XOR),
            PorterDuffXfermode(PorterDuff.Mode.DARKEN),
            PorterDuffXfermode(PorterDuff.Mode.LIGHTEN),
            PorterDuffXfermode(PorterDuff.Mode.MULTIPLY),
            PorterDuffXfermode(PorterDuff.Mode.SCREEN)
        )

        val sLabels = arrayOf(
            "clear",
            "src",
            "dst",
            "srcOver",
            "dstOver",
            "srcIn",
            "dstIn",
            "srcOut",
            "dstOut",
            "srcATop",
            "dstATop",
            "xor",
            "darken",
            "lighten",
            "multiply",
            "screen"
        )
    }

    init {
        if (Build.VERSION.SDK_INT >= 11){
            setLayerType(LAYER_TYPE_SOFTWARE,null)
        }
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint?.textSize = mTextSize
        //这个参数很重要
        mPaint?.textAlign = Paint.Align.CENTER
        mPaint?.strokeWidth = 2f
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        itemSize = w / 4.5f
        itemHorizontalOffset = itemSize / 6
        itemVerticalOffset = itemSize * 0.426f
        mCircleRadius = itemSize / 3
        mRectSize = itemSize * 0.6f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        setLayerType(LAYER_TYPE_HARDWARE,null)

        var canvasWidth = canvas?.width
        var canvasHeight = canvas?.height

        for (row in 0 until 4){
            for (column in 0 until 4){
                var layer = canvas?.saveLayer(0f,0f,canvasWidth!!.toFloat(),canvasHeight!!.toFloat(),mPaint)

                var index = row * 4 + column

                var translateX = (itemSize + itemHorizontalOffset) * column;
                var translateY = (itemSize + itemVerticalOffset) * row;
                canvas?.translate(translateX,translateY)

                //画文字
                var text = sLabels[index]
                mPaint?.color = Color.BLACK
                var textXOffset = itemSize / 2
                var textYOffset = mTextSize + (itemVerticalOffset - mTextSize) / 2
                canvas?.drawText(text,textXOffset,textYOffset,mPaint!!)

                canvas?.translate(0f, itemVerticalOffset)
                //画边框
                mPaint?.style = Paint.Style.STROKE
                mPaint?.color = 0xff000000.toInt()
                canvas?.drawRect(2f,2f,itemSize-2,itemSize-2,mPaint!!)

                //画圆
                mPaint?.style = Paint.Style.FILL
                mPaint?.color = mCircleColor.toInt()
                var left = mCircleRadius + 3
                var top = mCircleRadius + 3
                canvas?.drawCircle(left,top,mCircleRadius,mPaint!!)

                mPaint?.setXfermode(sModes[index])

                //画矩形
                mPaint?.color = mRectColor.toInt()
                var right = left + mRectSize;
                var bottom = top + mRectSize;

                canvas?.drawRect(left,top,right,bottom,mPaint!!)

                mPaint?.setXfermode(null)
                canvas?.restoreToCount(layer!!)

            }
        }

    }

}
















