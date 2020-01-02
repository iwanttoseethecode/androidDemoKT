package com.example.androiddemokt.ui.drawble

import android.animation.ValueAnimator
import android.graphics.*
import android.graphics.drawable.Drawable
import android.view.animation.LinearInterpolator

/**
 * Created by luoling on 2019/12/2.
 * description:
 */
class SearchDrawable() : Drawable() {

    companion object {
        var STATE_ANIM_NONE = 0
        var STATE_ANIM_START = 1
        var STATE_ANIM_STOP = 2
    }

    var animationRatio = -1f
    lateinit var mPaint: Paint
    lateinit var ovalRect: RectF
    var mValueAnimator: ValueAnimator? = null

    var mState = STATE_ANIM_NONE
    var mColor: String = "#4caf50"
    var mRadius: Int = 0
    var mCircleX: Int = 0
    var mCircleY: Int = 0


    override fun onBoundsChange(bounds: Rect) {
        super.onBoundsChange(bounds)
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint.strokeWidth = 5f
        mRadius = Math.min(bounds.width(), bounds.height()) / 6
        mCircleX = bounds.width() / 2
        mCircleY = bounds.height() / 2

        ovalRect = RectF()
        ovalRect.left = (mCircleX - mRadius).toFloat()
        ovalRect.right = (mCircleX + mRadius).toFloat()
        ovalRect.top = (mCircleY - mRadius).toFloat()
        ovalRect.bottom = (mCircleY + mRadius).toFloat()
    }

    fun startAnimation() {
        mValueAnimator = ValueAnimator.ofFloat(0f, 1f)
        mValueAnimator?.addUpdateListener {
            animationRatio = it.animatedValue as Float
            invalidateSelf()//会通过callback回调到ImagView的invalidate()
        }
        mValueAnimator?.interpolator = LinearInterpolator()
        mValueAnimator?.duration = 4000
        mValueAnimator?.start()
        mState = STATE_ANIM_START
    }

    fun resetAnimation() {
        mValueAnimator?.cancel()
        animationRatio = -1f
        mValueAnimator = null
        mState = STATE_ANIM_NONE
    }

    fun stopAnimation() {
        mValueAnimator?.cancel()
        animationRatio = 1f
        mState = STATE_ANIM_STOP
    }

    override fun draw(canvas: Canvas) {
        canvas.drawColor(Color.parseColor(mColor))
        when (mState) {
            STATE_ANIM_NONE -> {
                drawNormalView(canvas,mPaint)
            }
            STATE_ANIM_START -> {
                drawRunAnimView(mPaint, canvas)
            }
            STATE_ANIM_STOP -> {
                drawRunAnimView(mPaint, canvas)
            }
        }
    }

    fun drawNormalView(canvas: Canvas, paint: Paint) {
        canvas.save()
        paint.reset()
        paint.isAntiAlias = true
        paint.color = Color.WHITE
        paint.strokeWidth = 5f
        paint.style = Paint.Style.STROKE
        canvas.drawArc(ovalRect, 0f, 360f, false, paint)
        //半径长度在45度角时对应的x方向和y方向的长度
        val cosXLength = (mRadius * Math.cos(0.25 * Math.PI)).toFloat()
        val sinYLength = (mRadius * Math.sin(0.25 * Math.PI)).toFloat()
        canvas.drawLine(
            mCircleX + cosXLength,
            mCircleY + sinYLength,
            mCircleX + 2 * cosXLength,
            mCircleY + 2 * sinYLength,
            paint
        )
    }

    //绘制的圆的半径是mRadius,手柄长度是mRadius
    fun drawRunAnimView(paint: Paint, canvas: Canvas) {
        //半径长度在45度角时对应的x方向和y方向的长度
        val cosXLength = (mRadius * Math.cos(0.25 * Math.PI)).toFloat()
        val sinYLength = (mRadius * Math.sin(0.25 * Math.PI)).toFloat()
        if (animationRatio <= 0.5) {
            //绘制圆,sweepAngle为负角度就是逆时针方向绘制
            canvas.drawArc(ovalRect, 45f, 360 * (2 * animationRatio - 1), false, paint)
            canvas.drawLine(
                mCircleX + cosXLength,
                mCircleY + sinYLength,
                mCircleX + 2 * cosXLength,
                mCircleY + 2 * sinYLength,
                paint
            )
        } else {
            //计算剩余手柄的起始点
            val startX =
                (mCircleX.toDouble() + cosXLength.toDouble() + mRadius.toDouble() * ((animationRatio - 0.5) * 2) * Math.cos(
                    0.25 * Math.PI
                )).toFloat()
            val startY =
                (mCircleY.toDouble() + sinYLength.toDouble() + mRadius.toDouble() * ((animationRatio - 0.5) * 2) * Math.sin(
                    0.25 * Math.PI
                )).toFloat()
            canvas.drawLine(
                startX,
                startY,
                mCircleX + 2 * cosXLength,
                mCircleY + 2 * sinYLength,
                paint
            )
        }

        //绘制下面横线,横线的起点就是手柄的终点
        val lineStartX = mCircleX + 2 * cosXLength
        val lineStartY = mCircleY + 2 * sinYLength
        val lineEndX = lineStartX - animationRatio * 4f * mRadius.toFloat()
        val lineEndY = lineStartY
        canvas.drawLine(lineStartX, lineStartY, lineEndX, lineEndY, paint)
    }

    override fun setAlpha(alpha: Int) {
        mPaint.alpha = alpha
    }

    override fun getOpacity(): Int {
        return PixelFormat.UNKNOWN
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        mPaint.colorFilter = colorFilter
    }
}