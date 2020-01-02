package com.example.androiddemokt.ui.paintPathCanvas

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * Created by luoling on 2019/11/24.
 * description:
 */
class MyView constructor(context: Context,attributes: AttributeSet?,defStyle:Int) :View(context,attributes,defStyle){

    constructor(context: Context,attributes: AttributeSet?):this(context,attributes,0){}

    var showId:Int = 0
        set(value) {
            field = value
            invalidate()
        }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        when(showId){
            0 -> drawStrokeCap(canvas) //StrokeCap示例
            1 -> drawStrokeJoin(canvas) //strokeJoin示例
            2 -> drawCornerPathEffect(canvas) //CornerPathEffect示例
            3 -> drawCornerPathEffectDemo(canvas) //CornerPathEffect DEMO曲线
            4 -> drawDashPathEffectDemo(canvas) //DashPathEffect DEMO效果
            5 -> drawDiscretePathEffectDemo(canvas) //DiscretePathEffect DEMO效果
            6 -> drawPathDashPathEffect(canvas) // PathDashPathEffect效果
            7 -> drawPathDashPathEffectDemo(canvas) //PathDashPathEffect DEMO效果
            8 -> drawComposePathEffectDemo(canvas) //ComposePathEffect与SunPathEffect
            9 -> drawSubpixelText(canvas) //SubpixelText Demo
        }
    }

    private fun drawSubpixelText(canvas: Canvas?) {
        var paint = Paint()
        paint.color = Color.GREEN
        var text = "UI效果测试"
        paint.textSize = 100f

        paint.isSubpixelText = false
        canvas?.drawText(text,0f,200f,paint)

        canvas?.translate(0f,300f)
        paint.isSubpixelText = true
        canvas?.drawText(text,0f,200f,paint)
    }

    private fun drawComposePathEffectDemo(canvas: Canvas?) {

        var paint = getPaint()
        var path = getPath()
        canvas?.drawPath(path, paint)

        //仅应用圆角特效的路径
        canvas?.translate(0f,300f)
        var cornerPathEffect = CornerPathEffect(100f)
        paint.pathEffect = cornerPathEffect
        canvas?.drawPath(path, paint)

        //仅应用虚线特效的路径
        canvas?.translate(0f,300f)
        var dashPathEffect = DashPathEffect(floatArrayOf(5f,8f,10f),0f)
        paint.pathEffect = dashPathEffect
        canvas?.drawPath(path, paint)

        //利用ComposePathEffect先应用圆角特效,再应用虚线特效
        canvas?.translate(0f,300f)
        var composePathEffect = ComposePathEffect(dashPathEffect,cornerPathEffect)
        paint.pathEffect = composePathEffect
        canvas?.drawPath(path,paint)

        //利用SumPathEffect,分别将圆角特效应用于原始路径,然后将生成的两条特效路径合并
        canvas?.translate(0f,300f)
        paint.style = Paint.Style.STROKE
        var sumPathEffect = SumPathEffect(cornerPathEffect,dashPathEffect)
        paint.pathEffect = sumPathEffect
        canvas?.drawPath(path,paint)
    }

    private fun drawPathDashPathEffectDemo(canvas: Canvas?) {
        var paint = getPaint()
        var path = getPath()

        canvas?.drawPath(path, paint)

        canvas?.translate(0f,200f)
        paint.pathEffect = PathDashPathEffect(getStampPath(),35f,0f,PathDashPathEffect.Style.MORPH)
        canvas?.drawPath(path, paint)

        canvas?.translate(0f,200f)
        paint.pathEffect = PathDashPathEffect(getStampPath(),35f,0f,PathDashPathEffect.Style.ROTATE)

        canvas?.translate(0f,200f)
        paint.pathEffect = PathDashPathEffect(getStampPath(),35f,0f,PathDashPathEffect.Style.TRANSLATE)
        canvas?.drawPath(path,paint)

    }

    private fun drawPathDashPathEffect(canvas: Canvas?) {
        var paint = getPaint()

        var path = Path()
        path.moveTo(100f,600f)
        path.lineTo(400f,150f)
        path.lineTo(700f,900f)
        canvas?.drawPath(path, paint)

        canvas?.translate(0f,200f)

        //利用以另一个路径为单位,延着路径盖章.相当于PS的印章工具
        paint.pathEffect = PathDashPathEffect(getStampPath(),35f,0f,PathDashPathEffect.Style.MORPH)
        canvas?.drawPath(path,paint)
    }

    private fun getStampPath():Path{
        var path = Path()
        path.moveTo(0f,20f)
        path.lineTo(10f,0f)
        path.lineTo(20f,20f)
        path.close()

        path.addCircle(0f,0f,3f,Path.Direction.CCW)
        return path
    }

    private fun drawDiscretePathEffectDemo(canvas: Canvas?) {
        var paint = getPaint()
        var path = getPath()

        canvas?.drawPath(path,paint)

        canvas?.translate(0f,200f)
        /**
         * 把原有的路线,在指定的间距处插入一个突刺
         * 第一个这些突出的“杂点”的间距,值越小间距越短,越密集
         * 第二个是突出距离
         */
        paint.pathEffect = DiscretePathEffect(2f,5f)
        canvas?.drawPath(path,paint)

        canvas?.translate(0f,200f)
        paint.pathEffect = DiscretePathEffect(6f,5f)
        canvas?.drawPath(path,paint)

        canvas?.translate(0f,200f)
        paint.pathEffect = DiscretePathEffect(6f,15f)
        canvas?.drawPath(path,paint)

    }

    private fun drawDashPathEffectDemo(canvas: Canvas?) {
        var paint = getPaint()
        var path = getPath()

        paint.pathEffect = DashPathEffect(floatArrayOf(10f,20f,5f,15f,20f),0f)
        canvas?.drawPath(path,paint)
    }

    private fun drawCornerPathEffectDemo(canvas: Canvas?) {
        var paint = getPaint()
        var path = getPath()
        canvas?.drawPath(path,paint)

        canvas?.save()
        canvas?.translate(0f,200f)
        paint.pathEffect = CornerPathEffect(200f)
        canvas?.drawPath(path,paint)
        canvas?.restore()
    }

    private fun drawCornerPathEffect(canvas: Canvas?) {
        var paint = getPaint()
        var path = Path()
        path.moveTo(100f,600f)
        path.lineTo(400f,100f)
        path.lineTo(700f,900f)

        canvas?.drawPath(path,paint)

        paint.color = Color.RED
        paint.pathEffect = CornerPathEffect(100f)
        canvas?.drawPath(path,paint)

        paint.color = Color.YELLOW
        paint.pathEffect = CornerPathEffect(200f)
        canvas?.drawPath(path,paint)
    }

    private fun drawStrokeCap(canvas:Canvas?){
        var paint = Paint()
        paint.strokeWidth = 80f
        paint.isAntiAlias = true
        paint.color = Color.GREEN
        paint.style = Paint.Style.STROKE
        //画笔的倾斜度
        paint.strokeMiter = 0.5f
        //设置图像是否使用抖动处理，会使得绘制出来的图片更加平滑和饱满，图像更加清晰
        paint.isDither = true
        //无线帽
        paint.strokeCap = Paint.Cap.BUTT
        canvas?.drawLine(100f,200f,400f,200f,paint)
        //方形线帽
        paint.strokeCap = Paint.Cap.SQUARE
        canvas?.drawLine(100f,400f,400f,400f,paint)
        //圆形线帽
        paint.strokeCap = Paint.Cap.ROUND
        canvas?.drawLine(100f,600f,400f,600f,paint)
    }

    private fun drawStrokeJoin(canvas: Canvas?) {
        var paint = Paint()
        paint.strokeWidth = 40f
        paint.color = Color.GREEN
        paint.style = Paint.Style.STROKE
        paint.isAntiAlias = true

        var path = Path()
        path.moveTo(100f,100f)
        path.lineTo(450f,100f)
        path.lineTo(100f,300f)
        paint.strokeJoin = Paint.Join.MITER
        canvas?.drawPath(path,paint)

        path.moveTo(100f,400f)
        path.lineTo(450f,400f)
        path.lineTo(100f,600f)
        paint.strokeJoin = Paint.Join.BEVEL
        canvas?.drawPath(path,paint)

        path.moveTo(100f,700f)
        path.lineTo(450f,700f)
        path.lineTo(100f,900f)
        paint.strokeJoin = Paint.Join.ROUND
        canvas?.drawPath(path,paint)
    }

    private fun getPath():Path{
        var path = Path()
        //定义路径的起点
        path.moveTo(0f,0f)

        //定义路径的各个点
        for (i in 0 until 40){
            path.lineTo(i * 35f, (Math.random() * 150).toFloat())
        }
        return path;
    }

    private fun getPaint():Paint{
        var paint = Paint()
        paint.strokeWidth = 4f
        paint.color = Color.GREEN
        paint.style = Paint.Style.STROKE
        paint.isAntiAlias = true
        return paint
    }



}