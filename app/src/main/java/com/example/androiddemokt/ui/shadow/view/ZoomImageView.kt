package com.example.androiddemokt.ui.shadow.view

import android.content.Context
import android.graphics.*
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.androiddemokt.R

/**
 * Created by luoling on 2019/11/28.
 * description:
 */
class ZoomImageView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    View(context, attrs, defStyleAttr) {

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {}

    constructor(context: Context) : this(context, null) {}

    //放大倍数
    var FACTOR = 2f
    //放大镜的半径
    var RADIUS = 100f
    //原图
    var mBitmap: Bitmap
    // 放大后的图
    var mBitmapScale: Bitmap
    // 制作的圆形的图片（放大的局部），盖在Canvas上面
    var mShapeDrawable: ShapeDrawable

    var mMatrix: Matrix

    init {
        mBitmap = BitmapFactory.decodeResource(resources, R.mipmap.baby)
        mBitmapScale = Bitmap.createScaledBitmap(
            mBitmap,
            (mBitmap.width * FACTOR).toInt(),
            (mBitmap.height * FACTOR).toInt(),
            true
        )

        var bitmapShader = BitmapShader(mBitmapScale, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)

        mShapeDrawable = ShapeDrawable(OvalShape())
        mShapeDrawable.paint.shader = bitmapShader
        mShapeDrawable.setBounds(0, 0, (RADIUS * FACTOR).toInt(), (RADIUS * FACTOR).toInt())

        mMatrix = Matrix()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var width = MeasureSpec.getSize(widthMeasureSpec)
        var widthMode = MeasureSpec.getMode(widthMeasureSpec)
        var height = MeasureSpec.getSize(heightMeasureSpec)
        var heightMode = MeasureSpec.getMode(heightMeasureSpec)

        var mWidth = 0
        var mHeight = 0

        if (widthMode == MeasureSpec.EXACTLY)
            mWidth = width
        else if (widthMode == MeasureSpec.AT_MOST)
            mWidth = mBitmap.width
        else
            mWidth = mBitmap.width

        if (heightMode == MeasureSpec.EXACTLY)
            mHeight = height
        else if (widthMode == MeasureSpec.AT_MOST)
            mHeight = mBitmap.height
        else
            mHeight = mBitmap.height

        setMeasuredDimension(mWidth,mHeight)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //画原图
        canvas?.drawBitmap(mBitmap, 0f, 0f, null)
        //画放大镜的图
        mShapeDrawable.draw(canvas!!)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        var x = event.getX()
        var y = event.getY()
        //将放大的图片往相反的方向挪动
        mMatrix.setTranslate(RADIUS - x * FACTOR, RADIUS - y * FACTOR)
        mShapeDrawable.paint.shader.setLocalMatrix(mMatrix)
        // 切出手势区域点位置的圆
        mShapeDrawable.setBounds(
            (x - RADIUS).toInt(), (y - RADIUS).toInt(), (x + RADIUS).toInt(),
            (y + RADIUS).toInt()
        )
        invalidate()
        return true
    }

}