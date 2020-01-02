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
class ComposeShaderView(context: Context,attributeSet: AttributeSet?,defStyleAttr:Int): View(context,attributeSet,defStyleAttr) {

    constructor(context: Context,attributeSet: AttributeSet?):this(context,attributeSet,0){}

    constructor(context: Context):this(context,null){}

    lateinit var launcherBmp:Bitmap
    lateinit var mPaint:Paint


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        launcherBmp = BitmapFactory.decodeResource(resources,R.mipmap.my_abcdefg)
        mPaint = Paint()
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        var bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
        var bs = BitmapShader(launcherBmp,Shader.TileMode.REPEAT,Shader.TileMode.MIRROR)
        var lg = LinearGradient(0f,0f,measuredWidth.toFloat(),0f,Color.RED,Color.BLUE,Shader.TileMode.CLAMP)

        var cs = ComposeShader(bs,lg,PorterDuff.Mode.DST_ATOP)
        mPaint.shader = cs
        canvas?.drawRect(Rect(0,0,measuredWidth,800),mPaint)

    }

}