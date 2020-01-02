package com.example.androiddemokt.ui.pathMeasure.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import java.util.ArrayList

/**
 * Created by luoling on 2019/11/29.
 * description:
 */
class PathMeasureBasicView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : View(context, attrs,defStyleAttr)  {

    constructor(context: Context,attrs: AttributeSet?):this(context,attrs,0){}

    constructor(context: Context):this(context,null){}

    var textPaint: Paint? = null
    internal var mDeafultPaint: Paint? = null
    var lightPaint: Paint? = null
    var index = 0
    internal var defaultDrawable: IMyDrawable? = null
    internal var mList: MutableList<IMyDrawable>? = null

    internal interface IMyDrawable {
        fun draw(canvas: Canvas)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        textPaint = Paint()
        textPaint?.setTextSize(40f)
        textPaint?.setColor(Color.GREEN)
        textPaint?.setStyle(Paint.Style.FILL)
        mDeafultPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mDeafultPaint?.setColor(Color.RED)
        mDeafultPaint?.setStrokeWidth(5f)
        mDeafultPaint?.setStyle(Paint.Style.STROKE)
        lightPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        lightPaint?.setColor(Color.DKGRAY)
        lightPaint?.setStrokeWidth(4f)
        lightPaint?.setStyle(Paint.Style.STROKE)

        mList = mutableListOf()
        mList?.add(TestNextContour())
        mList?.add(TestGetSegmentMoveTo())
        mList?.add(TestGetSegment())
        mList?.add(TestForceClosed())
        defaultDrawable = mList?.get(index)
    }

    internal inner class TestNextContour : IMyDrawable{
        override fun draw(canvas: Canvas) {
            val path = Path()
            val path1 = Path()
            val path2 = Path()
            path1.addRect(100f, 100f, 200f, 200f, Path.Direction.CW)
            path2.addRect(100f, 300f, 200f, 500f, Path.Direction.CW)
            path.op(path1, path2, Path.Op.XOR)
            canvas.drawPath(path, mDeafultPaint!!)

            val pathMeasure = PathMeasure(path, false)

            val len1 = pathMeasure.length
            pathMeasure.nextContour()
            val len2 = pathMeasure.length

            val tip = "len1 = $len1    len2 = $len2"

            canvas.drawText(tip, 30f, (height - 60).toFloat(), textPaint!!)
        }

    }

    internal inner class TestGetSegmentMoveTo : IMyDrawable {

        override fun draw(canvas: Canvas) {
            val path = Path()
            path.addRect(100f, 100f, 300f, 300f, Path.Direction.CW)

            val dst = Path()
            dst.moveTo(100f, 100f)

            val pathMeasure = PathMeasure(path, false)

            //startWithMoveTo是否启用MoveTo的点开始截取
            pathMeasure.getSegment(0f, 600f, dst, true)
            canvas.drawPath(path, lightPaint!!)

            canvas.drawPath(dst, mDeafultPaint!!)

        }
    }

    internal inner class TestGetSegment : IMyDrawable {

        override fun draw(canvas: Canvas) {
            val path = Path()
            path.addRect(100f, 100f, 300f, 300f, Path.Direction.CW)

            val dst = Path()

            val pathMeasure = PathMeasure(path, false)

            //startWithMoveTo是否启用MoveTo的点开始截取,不启用就从坐标原点开始
            pathMeasure.getSegment(0f, 600f, dst, false)
            canvas.drawPath(path, lightPaint!!)

            canvas.drawPath(dst, mDeafultPaint!!)
        }
    }

    internal inner class TestForceClosed : IMyDrawable {

        override fun draw(canvas: Canvas) {
            val path = Path()
            path.moveTo(100f, 100f)
            path.lineTo(300f, 100f)
            path.lineTo(300f, 200f)
            path.lineTo(100f, 200f)

            val measure1 = PathMeasure(path, false)
            val measure2 = PathMeasure(path, true)

            canvas.drawPath(path, mDeafultPaint!!)

            canvas.drawText(
                "forceClosed=false length = " + measure1.length,
                20f,
                (height - 100).toFloat(),
                textPaint!!
            )
            canvas.drawText(
                "forceClosed=true length = " + measure2.length,
                20f,
                (height - 40).toFloat(),
                textPaint!!
            )
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when(event.action){
            MotionEvent.ACTION_DOWN -> {
                index++;
                if (index >= mList!!.size){
                    index = 0
                }
                defaultDrawable = mList!!.get(index)
                invalidate()
            }
        }
        return true
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawText("点击切换", 10f, 30f, textPaint!!)
        defaultDrawable?.draw(canvas)
    }

}