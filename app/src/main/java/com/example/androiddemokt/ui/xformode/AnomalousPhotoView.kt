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
class AnomalousPhotoView(context: Context,attributeSet: AttributeSet?,defStyleAttr:Int) : View(context,attributeSet,defStyleAttr){

    constructor(context: Context,attributeSet: AttributeSet?):this(context,attributeSet,0){}

    constructor(context: Context):this(context,null){}

    lateinit var bmpWoman : Bitmap
    lateinit var bmpMask : Bitmap
    lateinit var paint:Paint
    var OFFSET : Float = 70f

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        bmpWoman = BitmapFactory.decodeResource(resources, R.mipmap.woman)
        bmpMask = BitmapFactory.decodeResource(resources,R.mipmap.pc_fill)
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var layerId = canvas?.saveLayer(RectF(OFFSET,OFFSET,bmpMask.width+OFFSET,bmpMask.height+OFFSET),paint)
        canvas?.drawBitmap(bmpWoman,0f,0f,paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
        canvas?.drawBitmap(bmpMask,OFFSET,OFFSET,paint)
        canvas?.restoreToCount(layerId!!)
        paint.xfermode = null
    }

}