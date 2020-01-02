package com.example.androiddemokt.ui.paintPathCanvas

import android.graphics.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.androiddemokt.R
import kotlinx.android.synthetic.main.activity_first_paint_canvas.*

class PathActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_path)
    }

    fun clickBtn1(view: View) {
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
    fun clickBtn2(view: View) {
        var bmpBuf = Bitmap.createBitmap(800,800,Bitmap.Config.ARGB_8888)
        var canvas = Canvas(bmpBuf)
        var paint = Paint()
        paint.style = Paint.Style.STROKE
        var path = Path()

        //往Path对象中添加矩形椭圆园弧，需要调用Path类中定义的一组以“add”开头的方法，这组方法
        //有些需要传递一个类型为path.Direction的参数，这是一个枚举类型，枚举值cw表示顺时针，ccw表示逆时针。

        //矩形
        path.addRect(RectF(10f,10f,300f,100f),Path.Direction.CW)
        //圆角矩形
        path.addRoundRect(RectF(10f,120f,300f,220f), floatArrayOf(10f,20f,20f,10f,30f,40f,40f,30f),Path.Direction.CW)

        //椭圆
        path.addOval(RectF(10f,240f,300f,340f),Path.Direction.CW)

        //圆
        path.addCircle(60f,390f,50f,Path.Direction.CW)

        //弧线
        path.addArc(RectF(10f,500f,300f,600f),-30f,-60f)

        canvas.drawPath(path,paint)
        IV.setImageBitmap(bmpBuf)
    }
    fun clickBtn3(view: View) {
        var bmpBuf = Bitmap.createBitmap(800,800,Bitmap.Config.ARGB_8888)
        var canvas = Canvas(bmpBuf)
        var paint = Paint()
        paint.style = Paint.Style.STROKE
        var path = Path()
        path.moveTo(100f,100f)
        path.quadTo(200f,10f,300f,300f)
        canvas.drawPath(path,paint)

        paint.strokeWidth = 4f
        paint.color = Color.RED
        canvas.drawPoints(floatArrayOf(100f,100f,200f,10f,300f,300f),paint)
        //绘制三阶贝塞尔曲线
        path.moveTo(100f,200f)
        path.cubicTo(100f,350f,300f,320f,600f,800f)
        canvas.drawPath(path,paint)
        IV.setImageBitmap(bmpBuf)
    }
    fun clickBtn4(view: View) {
        var bmpBuf = Bitmap.createBitmap(800,800,Bitmap.Config.ARGB_8888)
        var canvas = Canvas(bmpBuf)
        var paint = Paint()
        var path = Path()
        paint.style = Paint.Style.STROKE
        path.moveTo(100f,100f)
        path.arcTo(RectF(100f,150f,300f,300f),-30f,60f,false)
        canvas.drawPath(path,paint)
        IV.setImageBitmap(bmpBuf)
    }
    fun clickBtn5(view: View) {
        var bmpBuf = Bitmap.createBitmap(800,800,Bitmap.Config.ARGB_8888)
        var canvas = Canvas(bmpBuf)
        var paint = Paint()
        paint.isAntiAlias = true
        paint.style = Paint.Style.FILL
        var path1 = Path()
        path1.addRect(RectF(10f,10f,110f,110f),Path.Direction.CW)
        canvas.drawPath(path1,paint)
        var path2 = Path()
        path2.addCircle(100f,100f,50f,Path.Direction.CW)
        paint.color = Color.RED
        canvas.drawPath(path2,paint)
        IV.setImageBitmap(bmpBuf)
    }
    fun clickBtn6(view: View) {
        var bmpBuf = Bitmap.createBitmap(800,800,Bitmap.Config.ARGB_8888)
        var canvas = Canvas(bmpBuf)
        var paint = Paint()
        paint.isAntiAlias = true
        paint.style = Paint.Style.FILL

        var path1 = Path()
        path1.addRect(RectF(10f,10f,110f,110f),Path.Direction.CW)

        var path2 = Path()
        path2.addCircle(100f,100f,50f,Path.Direction.CW)
        paint.color = Color.RED
        //差集
        path1.op(path2,Path.Op.DIFFERENCE)

        canvas.drawPath(path1,paint)
        IV.setImageBitmap(bmpBuf)
    }
    fun clickBtn7(view: View) {
        var bmpBuf = Bitmap.createBitmap(800,800,Bitmap.Config.ARGB_8888)
        var canvas = Canvas(bmpBuf)
        var paint = Paint()
        paint.isAntiAlias = true
        paint.style = Paint.Style.FILL
        var path1 = Path()
        path1.addRect(RectF(10f,10f,110f,110f),Path.Direction.CW)

        var path2 = Path()
        path2.addCircle(100f,100f,50f,Path.Direction.CW)
        paint.color = Color.RED
        //交集
        path1.op(path2,Path.Op.INTERSECT)
        canvas.drawPath(path1,paint)
        IV.setImageBitmap(bmpBuf)
    }
    fun clickBtn8(view: View) {
        var bmpBuf = Bitmap.createBitmap(800,800,Bitmap.Config.ARGB_8888)
        var canvas = Canvas(bmpBuf)
        var paint = Paint()
        paint.style = Paint.Style.FILL
        paint.isAntiAlias = true

        var path1 = Path()
        path1.addRect(RectF(10f,10f,110f,110f),Path.Direction.CW)

        var path2 = Path()
        path2.addCircle(100f,100f,50f,Path.Direction.CW)
        paint.color = Color.RED
        //反差集
        path1.op(path2,Path.Op.REVERSE_DIFFERENCE)
        canvas.drawPath(path1,paint)
        IV.setImageBitmap(bmpBuf)
    }
    fun clickBtn9(view: View) {
        var bmpBuf = Bitmap.createBitmap(800,800,Bitmap.Config.ARGB_8888)
        var canvas = Canvas(bmpBuf)
        var paint = Paint()
        paint.isAntiAlias = true
        paint.style = Paint.Style.FILL
        var path1 = Path()
        path1.addRect(RectF(10f,10f,110f,110f),Path.Direction.CW)
        var path2 = Path()
        path2.addCircle(100f,100f,50f,Path.Direction.CW)
        paint.color = Color.RED
        //并集
        path1.op(path2,Path.Op.UNION)
        canvas.drawPath(path1,paint)
        IV.setImageBitmap(bmpBuf)
    }
    fun clickBtn10(view: View) {
        var bmpBuf = Bitmap.createBitmap(800,800,Bitmap.Config.ARGB_8888)
        var canvas = Canvas(bmpBuf)
        var paint = Paint()
        paint.isAntiAlias = true
        paint.style = Paint.Style.FILL
        var path1 = Path()
        path1.addRect(RectF(10f,10f,110f,110f),Path.Direction.CW)
        var path2 = Path()
        path2.addCircle(100f,100f,50f,Path.Direction.CW)
        paint.color = Color.RED
        //补集
        path1.op(path2,Path.Op.XOR)
        canvas.drawPath(path1,paint)
        IV.setImageBitmap(bmpBuf)
    }
    fun clickBtn11(view: View) {
        var bmpBuf = Bitmap.createBitmap(800,800,Bitmap.Config.ARGB_8888)
        var canvas = Canvas(bmpBuf)
        var paint = Paint()
        paint.isAntiAlias = true
        paint.style = Paint.Style.FILL
        paint.textSize = 24f
        var text = "天宫二号,改时间烦恼无论是啊额，我爱android!"
        canvas.drawText(text,10f,50f,paint)
        canvas.drawText(text,0,4,10f,100f,paint)
        canvas.drawText(text.toCharArray(),5,10,10f,150f,paint)

        var path = Path()
        path.moveTo(10f,200f)
        path.cubicTo(100f,100f,300f,330f,400f,200f)
        canvas.drawTextOnPath(text,path,15f,15f,paint)
        paint.color = Color.RED
        paint.style = Paint.Style.STROKE
        canvas.drawPath(path,paint)
        IV.setImageBitmap(bmpBuf)
    }

    // INVERSE_EVEN_ODD 模式 --- 取path所有未占和相交的区域
    fun clickBtn12(view: View) {
        var bmpBuf = Bitmap.createBitmap(800,800,Bitmap.Config.ARGB_8888)
        var canvas = Canvas(bmpBuf)
        var paint = Paint()
        paint.isAntiAlias = true
        paint.color = Color.RED
        var path = Path()
        path.offset(100f,100f)
        path.addCircle(200f,200f,100f,Path.Direction.CW)
        path.addCircle(300f,300f,100f,Path.Direction.CW)
        path.fillType = Path.FillType.INVERSE_EVEN_ODD
        canvas.drawPath(path,paint)
        IV.setImageBitmap(bmpBuf)
    }

    // INVERSE_WINDING 模式 -- 取path所有未占的区域
    fun clickBtn13(view: View) {
        var bmpBuf = Bitmap.createBitmap(800,800,Bitmap.Config.ARGB_8888)
        var canvas = Canvas(bmpBuf)
        var paint = Paint()
        paint.isAntiAlias = true
        paint.color = Color.RED
        var path = Path()
        path.offset(100f,100f)
        path.addCircle(200f,200f,100f,Path.Direction.CW)
        path.addCircle(300f,300f,100f,Path.Direction.CW)
        path.fillType = Path.FillType.INVERSE_WINDING
        canvas.drawPath(path,paint)
        IV.setImageBitmap(bmpBuf)
    }

    // EVEN_ODD 模式 --- 取Path所在不相交的区域
    fun clickBtn14(view: View) {
        var bmpBuf = Bitmap.createBitmap(800,800,Bitmap.Config.ARGB_8888)
        var canvas = Canvas(bmpBuf)
        var paint = Paint()
        paint.isAntiAlias = true
        paint.color = Color.RED
        var path = Path()
        path.offset(100f,100f)
        path.addCircle(200f,200f,100f,Path.Direction.CW)
        path.addCircle(300f,300f,100f,Path.Direction.CW)
        path.fillType = Path.FillType.EVEN_ODD
        canvas.drawPath(path,paint)
        IV.setImageBitmap(bmpBuf)
    }

    // WINDING 模式 --- 取Path所有所在的区域 -- 默认的模式
    fun clickBtn15(view: View) {
        var bmpBuffer = Bitmap.createBitmap(800,800,Bitmap.Config.ARGB_8888)
        var canvas = Canvas(bmpBuffer)
        var paint = Paint()
        paint.color = Color.RED
        var path = Path()
        path.offset(100f,100f)
        path.addCircle(200f,200f,100f,Path.Direction.CW)
        path.addCircle(300f,300f,100f,Path.Direction.CW)
        path.fillType = Path.FillType.WINDING
        canvas.drawPath(path,paint)
        IV.setImageBitmap(bmpBuffer)
    }
}
