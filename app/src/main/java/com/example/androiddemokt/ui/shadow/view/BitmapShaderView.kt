package com.example.androiddemokt.ui.shadow.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.androiddemokt.R

/**
 * Created by luoling on 2019/11/28.
 * description:
 */
class BitmapShaderView(context: Context,attrs:AttributeSet?,defStyleAttrs:Int) : View(context, attrs,defStyleAttrs) {

    constructor(context: Context,attrs: AttributeSet?):this(context,attrs,0){}

    constructor(context: Context):this(context,null){}

    lateinit var launcherBmp:Bitmap
    lateinit var mPaint:Paint

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        launcherBmp = BitmapFactory.decodeResource(resources, R.mipmap.my_abcdefg)
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var bitmapShader = BitmapShader(launcherBmp,Shader.TileMode.REPEAT,Shader.TileMode.MIRROR)

        mPaint.shader = bitmapShader
        canvas?.drawRect(Rect(0,0,measuredWidth,800),mPaint)
    }

}