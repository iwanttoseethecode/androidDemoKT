package com.example.androiddemokt.ui.xformode

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.androiddemokt.R

/**
 * Created by luoling on 2019/11/29.
 * description:
 */
class CirclePhotoSpannableView(context: Context, attributeSet: AttributeSet?, defStyleAttr :Int):View(context,attributeSet,defStyleAttr) {

    constructor(context: Context,attributeSet: AttributeSet?):this(context,attributeSet,0){}

    constructor(context: Context):this(context,null){}

    var bmpWoman : Bitmap
    var bmpCircle: Bitmap
    var cvsCricle: Canvas
    var paint : Paint

    init {
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        bmpWoman = BitmapFactory.decodeResource(resources, R.mipmap.woman)
        var length = Math.min(bmpWoman.width,bmpWoman.height)
        bmpCircle = Bitmap.createBitmap(length,length,Bitmap.Config.ARGB_8888)
        cvsCricle = Canvas(bmpCircle)
        var r = length / 2
        cvsCricle.drawCircle(r.toFloat(),r.toFloat(),r.toFloat(),paint)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var size = cvsCricle.width
        var layerId = canvas?.saveLayer(RectF(100f,100f,100f + size,100f + size),paint)
        canvas?.drawBitmap(bmpWoman,100f,100f,paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
        canvas?.drawBitmap(bmpCircle,100f,100f,paint)
        canvas?.restoreToCount(layerId!!)
        paint.xfermode = null
    }

}