package com.example.androiddemokt.ui.xformode

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.androiddemokt.R
import java.util.*

/**
 * Created by luoling on 2019/11/29.
 * description:
 */
class GuaGuaLeView(context: Context,attributeSet: AttributeSet?,defStyleAttr:Int) : View(context,attributeSet,defStyleAttr) {

    constructor(context: Context,attributeSet: AttributeSet?):this(context,attributeSet,0){}

    constructor(context: Context):this(context,null){}

    var paint:Paint
    var clearPaint:Paint
    /*缓冲区*/
    lateinit var bmpBuffer:Bitmap
    /*缓冲区画布*/
    lateinit var canvasBuffer:Canvas
    var curX :Float = 0f
    var curY:Float = 0f

    var rnd:Int = 0
        get() {
            var random = Random()
            return random.nextInt(PRIZE.size)
        }

    companion object{
        val PRIZE = arrayOf(
            "无形之刃最为致命",
            "带你装逼带你飞",
            "带你走近垃圾堆",
            "大王叫我来巡山"
        )
        /*涂抹的粗细*/
        val FINGER = 90f
    }

    init {
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.textSize = 100f
        paint.color = Color.WHITE

        clearPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        clearPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        clearPaint.strokeCap = Paint.Cap.ROUND
        clearPaint.strokeJoin = Paint.Join.ROUND
        clearPaint.strokeWidth = FINGER
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        drawBackground()

        //初始化缓存区
        bmpBuffer = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888)
        canvasBuffer = Canvas(bmpBuffer)
        //为缓冲区蒙上一灰色
        canvasBuffer.drawColor(Color.parseColor("#FF808080"))
    }

    fun drawBackground(){
        var bmpBackground = BitmapFactory.decodeResource(resources, R.mipmap.ggbackground)
        //从资源中读取的图片不可以修改，复制出一张可修改的图片
        var bmpBackgroundMutable = bmpBackground.copy(Bitmap.Config.ARGB_8888,true)
        var cvsBackGround = Canvas(bmpBackgroundMutable)
        //计算出文字所占的区域，将位置放在正中间
        var text = PRIZE[rnd]
        var rect = Rect()
        paint.getTextBounds(text,0,text.length,rect)
        var x = (bmpBackgroundMutable.width - rect.width())/2
        var centerY = bmpBackgroundMutable.height / 2

        var fontMetrics = paint.fontMetrics

        var y = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom + centerY


        setLayerType(View.LAYER_TYPE_SOFTWARE,paint)
        paint.setShadowLayer(10f,0f,0f,Color.GREEN)
        cvsBackGround.drawText(text,x.toFloat(),y,paint)
        paint.setShadowLayer(0f,0f,0f,Color.YELLOW)
        //画背景
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
            background = BitmapDrawable(resources,bmpBackgroundMutable)
        }else{
            background = BitmapDrawable(bmpBackgroundMutable)
        }

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawBitmap(bmpBuffer,0f,0f,paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        var x = event?.getX()
        var y = event?.getY()
        when(event.action){
            MotionEvent.ACTION_DOWN -> {
                curX = x
                curY = y
            }
            MotionEvent.ACTION_MOVE -> {
                canvasBuffer.drawLine(curX, curY, x, y, clearPaint)
                invalidate()
                curX = x
                curY = y
            }
            MotionEvent.ACTION_UP -> {
                invalidate()
            }
        }
        return true
    }

}