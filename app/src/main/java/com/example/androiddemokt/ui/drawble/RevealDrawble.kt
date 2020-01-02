package com.example.androiddemokt.ui.drawble

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.PixelFormat
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.Gravity

/**
 * Created by luoling on 2019/12/2.
 * description:
 */
class RevealDrawble(
    private val mUnselectedDrawable: Drawable,
    private val mSelectedDrawable: Drawable,
    private val mOrienttation: Int
) : Drawable() {

    override fun draw(canvas: Canvas) {
        //绘制
        val level = level//from 0 to 10000,这是系统约定的
        if (level == 10000 || level == 0) {//全部画选中区域
            mUnselectedDrawable.draw(canvas)
        } else if (level == 5000) {//全部画选中区域
            mSelectedDrawable.draw(canvas)
        } else {//混合画选中和未选中区域
            val ratio = level / 5000 - 1f
            val bounds = bounds
            var w = bounds.width()
            var h = bounds.height()
            val tempRect = Rect()
            run {
                //画未选中部分
                if (mOrienttation == HORIZONTAL) {
                    w = (w * Math.abs(ratio)).toInt()
                }
                if (mOrienttation == VERTICAL) {
                    h = (h * Math.abs(ratio)).toInt()
                }

                val gravity = if (ratio < 0) Gravity.LEFT else Gravity.RIGHT

                Gravity.apply(gravity, w, h, bounds, tempRect)
                canvas.save()
                canvas.clipRect(tempRect)
                mUnselectedDrawable.draw(canvas)
                canvas.restore()
            }
            run {
                //画选中的部分
                if (mOrienttation == HORIZONTAL) {
                    w -= (w * Math.abs(ratio)).toInt()
                }
                if (mOrienttation == VERTICAL) {
                    h -= (h * Math.abs(ratio)).toInt()
                }

                val gravity = if (ratio < 0) Gravity.RIGHT else Gravity.LEFT
                Gravity.apply(gravity, w, h, bounds, tempRect)
                canvas.save()
                canvas.clipRect(tempRect)
                mSelectedDrawable.draw(canvas)
                canvas.restore()
            }
        }
    }

    override fun onLevelChange(level: Int): Boolean {
        //回调重绘自己
        invalidateSelf()
        return true
    }

    override fun setAlpha(alpha: Int) {

    }

    override fun setBounds(bounds: Rect) {
        super.setBounds(bounds)
    }

    override fun onBoundsChange(bounds: Rect) {
        mUnselectedDrawable.bounds = bounds
        mSelectedDrawable.bounds = bounds
    }

    override fun getIntrinsicWidth(): Int {
        return Math.max(mUnselectedDrawable.intrinsicWidth, mSelectedDrawable.intrinsicWidth)
    }

    override fun getIntrinsicHeight(): Int {
        return Math.max(mUnselectedDrawable.intrinsicHeight, mSelectedDrawable.intrinsicHeight)
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {

    }

    override fun getOpacity(): Int {
        return PixelFormat.UNKNOWN
    }

    companion object {
        val HORIZONTAL = 1
        val VERTICAL = 2
    }
}