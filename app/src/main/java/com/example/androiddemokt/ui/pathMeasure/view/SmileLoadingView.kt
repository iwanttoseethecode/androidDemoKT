package com.example.androiddemokt.ui.pathMeasure.view

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.androiddemokt.utils.UIUtil.dp2px

/**
 * Created by luoling on 2019/11/29.
 * description:
 */
class SmileLoadingView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : View(context, attrs,defStyleAttr) {

    constructor(context: Context,attrs: AttributeSet?):this(context,attrs,0){}

    constructor(context: Context):this(context,null){}

    /**
     * 左眼距离左边的距离（控件宽度＊EYE_PERCENT_W）
     */
    private val EYE_PERCENT_W = 0.35f

    /**
     * 眼睛距离top的距离（控件的高度＊EYE_PERCENT_H）
     */
    private val EYE_PERCENT_H = 0.38f

    /**
     * 嘴巴左边跟右边距离top的距离（控件的高度＊MOUCH_PERCENT_H）
     */
    private val MOUTH_PERCENT_H = 0.55f

    /**
     * 嘴巴中间距离top的距离（控件的高度＊MOUTH_PERCENT_H2）
     */
    private val MOUTH_PERCENT_H2 = 0.7f

    /**
     * 嘴巴左边距离边缘的位置（控件宽度＊MOUCH_PERCENT_W）
     */
    private val MOUTH_PERCENT_W = 0.23f

    /**
     * 眼睛跟嘴巴摆动的区间范围
     */
    private val QUIVER_AREA = 0.10f

    private var backgroundPaint: Paint
    private var reachedPaint: Paint
    private var unreachedPaint: Paint
    private var reachedPath: Path? = null
    private var unreachedPath: Path? = null
    private val mouthPath = Path()
    private var progress = 0f
    private var lineWidth:Float = 0f

    private var mRadius: Float = 0f

    private var mMouthH = MOUTH_PERCENT_H

    private var mMouthH2 = MOUTH_PERCENT_H2

    private var mEyesH = EYE_PERCENT_H

    private var quiverAnimator: ObjectAnimator? = null
    private var valueAnimator: ValueAnimator? = null
    private var animatorSet: AnimatorSet? = null


    init {
        lineWidth = dp2px(2).toFloat()
        reachedPaint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG)
        reachedPaint.style = Paint.Style.STROKE
        reachedPaint.strokeWidth = lineWidth
        reachedPaint.color = Color.WHITE
        reachedPaint.strokeCap = Paint.Cap.ROUND
        reachedPaint.strokeJoin = Paint.Join.ROUND

        unreachedPaint = Paint(reachedPaint)
        unreachedPaint.color = Color.GRAY

        backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        backgroundPaint.style = Paint.Style.FILL
        backgroundPaint.color = Color.argb(230, 230, 230, 230)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        mRadius = (width / 14).toFloat()
        if (unreachedPath == null) {
            unreachedPath = Path()
        }
        unreachedPath!!.addRoundRect(
            RectF(lineWidth, lineWidth, w - lineWidth, h - lineWidth),
            (w / 6).toFloat(),
            (w / 6).toFloat(),
            Path.Direction.CW
        )
        if (reachedPath == null) {
            reachedPath = Path()
        }
        reachedPath!!.addRoundRect(
            RectF(lineWidth, lineWidth, w - lineWidth, h - lineWidth),
            (w / 6).toFloat(),
            (w / 6).toFloat(),
            Path.Direction.CW
        )
        initAnim()
    }

    private fun initAnim() {
        quiverAnimator = ObjectAnimator.ofFloat(this, "progress", 0f, 1f)
        quiverAnimator!!.duration = 3000
        quiverAnimator!!.repeatCount = ValueAnimator.INFINITE
        quiverAnimator!!.repeatMode = ValueAnimator.REVERSE

        valueAnimator = ValueAnimator.ofFloat(0f, 1f)
        valueAnimator!!.addUpdateListener { animation ->
            val offset = QUIVER_AREA * animation.animatedFraction
            mMouthH = MOUTH_PERCENT_H + offset
            mMouthH2 = MOUTH_PERCENT_H2 + offset
            mEyesH = EYE_PERCENT_H + offset
            invalidate()
        }
        valueAnimator!!.duration = 1000
        valueAnimator!!.repeatCount = ValueAnimator.INFINITE
        valueAnimator!!.repeatMode = ValueAnimator.REVERSE
        animatorSet = AnimatorSet()
        animatorSet!!.playTogether(quiverAnimator, valueAnimator)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawPath(unreachedPath!!, backgroundPaint!!)
        canvas.save()
        drawFace(canvas)
        drawReachedRect(canvas)
        canvas.restore()
    }

    private fun drawReachedRect(canvas: Canvas) {
        unreachedPaint.style = Paint.Style.STROKE
        canvas.drawPath(unreachedPath!!, unreachedPaint)
        val pathMeasure = PathMeasure(reachedPath, false)
        val length = pathMeasure.length
        val currLength = length * progress
        val path = Path()
        path.moveTo(0f, 0f)
        pathMeasure.getSegment(0f, currLength, path, true)
        canvas.drawPath(path, reachedPaint!!)
    }

    private fun drawFace(canvas: Canvas) {
        unreachedPaint.style = Paint.Style.FILL
        //画左边眼睛
        canvas.drawCircle(
            width * EYE_PERCENT_W,
            height * mEyesH - mRadius,
            mRadius,
            unreachedPaint
        )
        //画右边眼睛
        canvas.drawCircle(
            width * (1 - EYE_PERCENT_W),
            height * mEyesH - mRadius,
            mRadius,
            unreachedPaint
        )

        mouthPath.reset()
        mouthPath.moveTo(width * MOUTH_PERCENT_W, height * mMouthH)
        mouthPath.quadTo(
            (width / 2).toFloat(),
            height * mMouthH2,
            width * (1 - MOUTH_PERCENT_W),
            height * mMouthH
        )
        unreachedPaint.style = Paint.Style.STROKE
        canvas.drawPath(mouthPath, unreachedPaint)
    }

    private fun setProgress(value: Float) {
        progress = value
    }

    private fun startAnim() {
        animatorSet!!.start()
    }

    private fun stopAnim() {
        animatorSet!!.end()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                if (animatorSet != null && animatorSet!!.isRunning) {
                    stopAnim()
                    return true
                } else if (animatorSet == null) {
                    initAnim()
                }
                startAnim()
            }
            MotionEvent.ACTION_UP -> {
            }
        }
        return super.onTouchEvent(event)
    }

}