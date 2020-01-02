package com.example.androiddemokt.ui.shadow.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.androiddemokt.baseComponent.BaseApplication.Companion.context

/**
 * Created by luoling on 2019/11/28.
 * description:
 */
class RadialGradientView(context:Context,attributeSet: AttributeSet?,defStyleAttr:Int) : View(context,attributeSet,defStyleAttr) {

    constructor(context: Context,attributeSet: AttributeSet?):this(context,attributeSet,0){}

    constructor(context: Context):this(context,null){}

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        var rect = Rect(50,50,450,450)
        var rg = RadialGradient(250f,250f,220f, Color.RED,Color.BLUE, Shader.TileMode.MIRROR)
        var paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.shader = rg
        canvas?.drawRect(rect,paint)

        canvas?.translate(500f,0f)
        canvas?.drawOval(RectF(rect),paint)

    }

}