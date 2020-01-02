package com.example.androiddemokt.ui.paintPathCanvas

import android.graphics.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.androiddemokt.R
import com.example.androiddemokt.utils.UIUtil.sp2px
import kotlinx.android.synthetic.main.activity_first_paint_canvas.*

class FirstPaintCanvasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_paint_canvas)
    }

    fun clickBtn1(view: View) {
        var bmp = Bitmap.createBitmap(400,400,Bitmap.Config.ARGB_8888)
        var canvas = Canvas(bmp)
        var paint = Paint()
        paint.isAntiAlias = true
        paint.textAlign = Paint.Align.LEFT
        paint.textSize = sp2px(16f).toFloat()
        paint.textSkewX = 0.5f
        paint.isUnderlineText = true
        paint.isFakeBoldText = true
        paint.style = Paint.Style.STROKE
        canvas.drawText("今天天气很好，可以去公园晒晒太阳",10f,50f,paint)

        //绘制矩形
        paint.color = Color.RED
        paint.strokeWidth = 20f
        paint.strokeJoin = Paint.Join.BEVEL
        //绘制一个矩形
        canvas.drawRect(RectF(50f,100f,350f,350f),paint)
        IV.setImageBitmap(bmp)
    }
    fun clickBtn2(view: View) {
        var bmpBuf = Bitmap.createBitmap(800,800,Bitmap.Config.ARGB_8888)
        var canvas = Canvas(bmpBuf)

        var bmp = BitmapFactory.decodeResource(resources,R.mipmap.my_abcdefg)
        canvas.drawBitmap(bmp,0f,0f,null)

        var bmpWidth = bmp.width
        var bmpHeight = bmp.height

        var src = Rect(0,0,bmpWidth,bmpHeight)
        var dst = Rect(0,bmpHeight,bmpWidth * 3,bmpHeight * 3)

        canvas.drawBitmap(bmp,src,dst,null)

        IV.setImageBitmap(bmpBuf)
    }
    fun clickBtn3(view: View) {
        var bmp = Bitmap.createBitmap(800,800,Bitmap.Config.ARGB_8888)
        var canvas = Canvas(bmp)
        var paint = Paint()
        paint.color = Color.RED
        paint.strokeWidth = 8f
        canvas.drawPoint(40f,50f,paint)

        paint.color = Color.GREEN

        var points = floatArrayOf(50f,52f,80f,60f,160f,90f,230f,324f,180f,470f)
        canvas.drawPoints(points,paint)

        IV.setImageBitmap(bmp)
    }
    fun clickBtn4(view: View) {
        var bmpBuf = Bitmap.createBitmap(800,800,Bitmap.Config.ARGB_8888)
        var canvas = Canvas(bmpBuf)
        var paint = Paint()
        paint.color = Color.RED
        paint.strokeWidth = 3f

        var step = 50f

        for (i in 0 until 5){
            canvas.drawLine(10f,step * i,300f,step * i,paint)
        }

        IV.setImageBitmap(bmpBuf)
    }
    fun clickBtn5(view: View) {
        val bmpBuffer = Bitmap.createBitmap(500, 800, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bmpBuffer)
        val paint = Paint()
        paint.color = Color.RED
        paint.strokeCap = Paint.Cap.ROUND
        canvas.drawRect(10f, 10f, 400f, 300f, paint)
//        30，30分别代表水平半径和垂直半径的椭圆
//        canvas.drawRoundRect(10,10,400,300,30,30,paint);
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f
        canvas.drawRect(Rect(10, 320, 400, 620), paint)
        IV.setImageBitmap(bmpBuffer)
    }

    fun clickBtn6(view: View) {
        var bmpBuf = Bitmap.createBitmap(800,800,Bitmap.Config.ARGB_8888)
        var canvas = Canvas(bmpBuf)
        var paint = Paint()
        paint.color = Color.RED
        paint.strokeWidth = 3f
        paint.style = Paint.Style.STROKE
        paint.isAntiAlias = true
        canvas.drawOval(RectF(10f,10f,400f,300f),paint)

        paint.style = Paint.Style.FILL
        canvas.drawCircle(200f,450f,100f,paint)
        IV.setImageBitmap(bmpBuf)
    }
    fun clickBtn7(view: View) {
        var bmpBuf = Bitmap.createBitmap(800,1000,Bitmap.Config.ARGB_8888)
        var canvas = Canvas(bmpBuf)
        var paint = Paint()
        paint.color = Color.RED
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f

        //参数startAngle表示起始角度，sweep表示扇形或弧线所占有的角度，正数表示顺时针，负数表示逆时针。userCenter表示是否使用中心点，true表示扇形，false表示弧线。
        canvas.drawArc(RectF(10f,10f,400f,300f),30f,60f,false,paint)
        canvas.drawArc(RectF(10f,310f,400f,610f),30f,60f,true,paint)

        paint.style = Paint.Style.FILL
        canvas.drawArc(RectF(10f,620f,400f,920f),30f,60f,true,paint)
        IV.setImageBitmap(bmpBuf)
    }
    fun clickBtn8(view: View) {
        var bmpBuf = Bitmap.createBitmap(800,800,Bitmap.Config.ARGB_8888)
        var canvas = Canvas(bmpBuf)
        var paint = Paint()
        paint.style = Paint.Style.STROKE
        var path = Path()
        path.moveTo(0f,150f)
        path.rLineTo(300f,0f)
        path.rLineTo(-300f,150f)
        path.rLineTo(150f,-300f)
        path.rLineTo(150f,300f)
        path.close()
        canvas.drawPath(path,paint)
        IV.setImageBitmap(bmpBuf)
    }

}
