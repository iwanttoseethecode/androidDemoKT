package com.example.androiddemokt.ui.pathMeasure.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.androiddemokt.R

/**
 * Created by luoling on 2019/11/29.
 * description:
 */
class ShipView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    View(context, attrs) {
    private val WAVE_LENGTH = 600
    private var mPath: Path? = null
    private var mPaint: Paint? = null
    private var mBitmap: Bitmap? = null
    private var faction: Float = 0.toFloat()
    private var mDeltaX: Int = 0

    init {
        init()
    }

    private fun init() {
        mPaint = Paint()
        mPaint!!.color = Color.RED
        mPaint!!.style = Paint.Style.FILL_AND_STROKE
        mPath = Path()
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        options.inSampleSize = 1
        mBitmap = BitmapFactory.decodeResource(resources, R.mipmap.ship, options)
        val bmpWidth = options.outWidth
        val bmpHeight = options.outHeight
        while (bmpWidth / options.inSampleSize > 160 || bmpHeight / options.inSampleSize > 130) {
            options.inSampleSize *= 2
        }
        options.inJustDecodeBounds = false
        mBitmap = BitmapFactory.decodeResource(resources, R.mipmap.ship, options)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        startAnim()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        mPath!!.reset()
        val halfWaveLength = WAVE_LENGTH / 2
        mPath!!.moveTo((-WAVE_LENGTH + mDeltaX).toFloat(), (height * 0.6).toFloat())
        var i = -WAVE_LENGTH + mDeltaX
        while (i < width + WAVE_LENGTH + mDeltaX) {
            mPath!!.rQuadTo((halfWaveLength / 2).toFloat(), 60f, halfWaveLength.toFloat(), 0f)
            mPath!!.rQuadTo((halfWaveLength / 2).toFloat(), -60f, halfWaveLength.toFloat(), 0f)
            i += WAVE_LENGTH
        }

        val pathMeasure = PathMeasure(mPath, false)
        val length = pathMeasure.length
        val matrix = Matrix()
        val pos = FloatArray(2)
        val tan = FloatArray(2)
        val posTan = pathMeasure.getPosTan(length * faction, pos, tan)
        if (posTan) {
            // 方案一 ：自己计算
// 将tan值通过反正切函数得到对应的弧度，在转化成对应的角度度数
//            var degrees =  (Math.atan2(tan[1].toDouble(),tan[0].toDouble())*180f / Math.PI).toFloat()
//            mMatrix.postRotate(degrees, (mBitmap.getWidth()/2).toFloat(),
//                (mBitmap.getHeight() / 2).toFloat()
//            );
//            mMatrix.postTranslate(pos[0]- mBitmap.getWidth() / 2,pos[1] - mBitmap.getHeight());
//            canvas.drawBitmap(mBitmap,mMatrix,mPaint);

            pathMeasure.getMatrix(
                length * faction,
                matrix,
                PathMeasure.TANGENT_MATRIX_FLAG or PathMeasure.POSITION_MATRIX_FLAG
            )
            //matrix保存了pos这点，让图片的底边的中心点设置到该点上就需要平移
            matrix.preTranslate((-mBitmap!!.width / 2).toFloat(), (-mBitmap!!.height).toFloat())
            canvas.drawBitmap(mBitmap!!, matrix, mPaint)
        }
        mPath!!.lineTo(width.toFloat(), height.toFloat())
        mPath!!.lineTo(0f, height.toFloat())
        mPath!!.close()
        canvas.drawPath(mPath!!, mPaint!!)
    }

    private fun startAnim() {
        val valueAnimator = ValueAnimator.ofFloat(0f, 1f)
        valueAnimator.addUpdateListener { animation ->
            faction = animation.animatedValue as Float

            mDeltaX = (WAVE_LENGTH * faction).toInt()
            invalidate()
        }
        valueAnimator.repeatCount = ValueAnimator.INFINITE
        valueAnimator.duration = 6000
        valueAnimator.start()
    }
}
