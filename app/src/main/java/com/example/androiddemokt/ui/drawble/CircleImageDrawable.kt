package com.example.androiddemokt.ui.drawble

import android.graphics.*
import android.graphics.drawable.Drawable

/**
 * Created by luoling on 2019/12/2.
 * description:
 */
class CircleImageDrawable(bitmap:Bitmap) :Drawable(){

    private val paint: Paint
    private val mBitmap: Bitmap
    private val radius: Float

    init {
        paint = Paint()
        paint.isAntiAlias = true
        mBitmap = bitmap
        var bitmapShaper = BitmapShader(bitmap,Shader.TileMode.CLAMP,Shader.TileMode.CLAMP)
        paint.shader = bitmapShaper
        radius = (Math.min(mBitmap.width,bitmap.height)/2).toFloat()
    }

    override fun getIntrinsicWidth(): Int {
        return (radius * 2).toInt()
    }

    override fun getIntrinsicHeight(): Int {
        return (radius * 2).toInt()
    }

    override fun draw(canvas: Canvas) {
        canvas.drawCircle(radius,radius,radius,paint)
    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
    }

    override fun getOpacity(): Int {
        return PixelFormat.UNKNOWN
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        paint.colorFilter = colorFilter
    }
}