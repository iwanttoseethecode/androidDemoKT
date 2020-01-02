package com.example.androiddemokt.ui.drawble

import android.graphics.*
import android.graphics.drawable.Drawable

/**
 * Created by luoling on 2019/12/2.
 * description:
 */
class RoundImageDrawable(bitmap: Bitmap,corner:Float) :Drawable() {

    var paint:Paint
    var mBitmap: Bitmap
    var mCorner:Float

    init{
        mBitmap = bitmap
        this.mCorner = corner
        var bitmapShader = BitmapShader(bitmap,Shader.TileMode.CLAMP,Shader.TileMode.CLAMP)
        paint = Paint()
        paint.isAntiAlias = true
        paint.shader = bitmapShader
    }

    override fun getIntrinsicWidth(): Int {
        return mBitmap.width
    }

    override fun getIntrinsicHeight(): Int {
        return mBitmap.height
    }

    override fun draw(canvas: Canvas) {
        canvas.drawRoundRect(RectF(bounds), mCorner, mCorner,paint)
    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSPARENT
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        paint.colorFilter = colorFilter
    }
}