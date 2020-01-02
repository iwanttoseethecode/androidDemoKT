package com.example.androiddemokt.ui.Graphics2D.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.androiddemokt.R

/**
 * Created by luoling on 2019/11/27.
 * description: 高版本的android api关于path的裁剪只保留了两种模式Region.Op.DIFFERENCE、Region.Op.INTERSECT
 */
class ClipView constructor(context: Context,attrs:AttributeSet?,defStyleAttrs:Int): View(context,attrs,defStyleAttrs) {

    constructor(context: Context,attrs: AttributeSet?):this(context,attrs,0){}

    constructor(context: Context):this(context,null){}

    private var serialNumber = 0

    fun setSerialNumber(type:Int){
        serialNumber = type
        postInvalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        var bmp = BitmapFactory.decodeResource(resources, R.mipmap.aodi)
        //绘制图片
        canvas?.drawBitmap(bmp,0f,0f,null)
        //平移坐标
        canvas?.translate(0f,600f)
        //定义剪裁区
        canvas?.clipRect(Rect(100,50,400,400))
        var path = Path()
        path.addCircle(400f,250f,200f,Path.Direction.CW)

        //高版本的android api关于path的裁剪只保留了两种模式Region.Op.DIFFERENCE、Region.Op.INTERSECT
        when (serialNumber) {
            0 -> { }
            1 -> canvas?.clipPath(path, Region.Op.DIFFERENCE) //canvas?.clipOutPath(path)
//            2 -> canvas?.clipPath(path, Region.Op.REVERSE_DIFFERENCE)
            3 -> canvas?.clipPath(path, Region.Op.INTERSECT) //canvas?.clipPath(path)
//            4 -> canvas?.clipPath(path, Region.Op.REPLACE)
//            5 -> canvas?.clipPath(path, Region.Op.UNION)
//            6 -> canvas?.clipPath(path, Region.Op.XOR)
        }

        canvas?.drawBitmap(bmp,0f,0f,null)
    }

}