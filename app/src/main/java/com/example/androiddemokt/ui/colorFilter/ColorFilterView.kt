package com.example.androiddemokt.ui.colorFilter

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.androiddemokt.R

/**
 * Created by luoling on 2019/11/26.
 * description:
 */
class ColorFilterView constructor(context: Context,attrs:AttributeSet?,defStyleAttr:Int) :
    View(context,attrs,defStyleAttr) {

    constructor(context: Context,attrs: AttributeSet?):this(context,attrs,0){}

    constructor(context: Context):this(context,null){}

    internal lateinit var paint: Paint
    internal lateinit var bitmap:Bitmap

    lateinit var mFilterDraw:FilterDraw
    var viewType:ViewType = ViewType.BlurMaskFilter

    init {
        paint = Paint()
        paint.color = Color.RED
        bitmap = BitmapFactory.decodeResource(resources, R.mipmap.p4)
        mFilterDraw = BlurMaskFilterDraw()
    }

    public fun setViewType(viewType:ViewType,mode:Int){
        this.viewType = viewType

        if (viewType.equals(ViewType.BlurMaskFilter)){
            mFilterDraw = BlurMaskFilterDraw()
        }else if (viewType.equals(ViewType.EmbossMaskFilter)){
            mFilterDraw = EmbossMaskFilterDraw()
        }else if (viewType.equals(ViewType.ColorMatrixColorFilter)){
            mFilterDraw = ColorMatrixFilterDraw()
        }else if (viewType.equals(ViewType.LightingColorFilter)){
            mFilterDraw = LightingColorFilterDraw()
        }else if (viewType.equals(ViewType.PorterDuffColorFilter)){
            mFilterDraw = PorterDuffColorFilterDraw()
        }
        mFilterDraw.setMode(mode)
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var width = MeasureSpec.getSize(widthMeasureSpec)
        var widthMode = MeasureSpec.getMode(widthMeasureSpec)
        var height = MeasureSpec.getSize(heightMeasureSpec)
        var heightMode = MeasureSpec.getMode(heightMeasureSpec)

        var measureWidth = 0
        var measureHeight = 0

        if (widthMode == MeasureSpec.EXACTLY)
            measureWidth = width
        else if(widthMode == MeasureSpec.AT_MOST)
            measureWidth = 1080
        else
            measureWidth = 1080

        if (heightMode == MeasureSpec.EXACTLY)
            measureHeight = height
        else if(heightMode == MeasureSpec.AT_MOST)
            measureHeight = 1920
        else
            measureHeight = 1920

        setMeasuredDimension(measureWidth,measureHeight)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mFilterDraw.onDraw(canvas!!)
    }

    interface FilterDraw{
        fun onDraw(canvas:Canvas)
        fun setMode(mode:Int)
    }

    enum class ViewType{
        BlurMaskFilter,
        EmbossMaskFilter,
        ColorMatrixColorFilter,
        LightingColorFilter,
        PorterDuffColorFilter
    }

    private fun getRangeNum(num:Int,max:Int,min:Int):Int{
        return Math.max(Math.min(num,max),min)
    }

    internal inner class BlurMaskFilterDraw : FilterDraw{

        var myMode:Int = 0

        override fun onDraw(canvas: Canvas) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null)
            val rectF = RectF(100f, 100f, bitmap.getWidth().toFloat(), bitmap.getHeight().toFloat())

            paint.reset()
            paint.color = Color.RED
            canvas.drawRect(rectF,paint)

            var rectF1 = RectF(100f,(300 + bitmap.height).toFloat(),bitmap.width.toFloat(),(300 + bitmap.height + bitmap.height).toFloat())

            //BlurMaskFilter.Blur.NORMAL,BlurMaskFilter.Blur.INNER,BlurMaskFilter.Blur.OUTER,BlurMaskFilter.Blur.SOLID
            var blurs = BlurMaskFilter.Blur.values()

            myMode = getRangeNum(myMode,blurs.size,0)

            //给图形添加阴影模糊
            paint.maskFilter = BlurMaskFilter(30f,blurs[myMode])
            canvas.drawRect(rectF1,paint)

        }

        override fun setMode(mode: Int) {
            myMode = mode
        }

    }

    internal inner class EmbossMaskFilterDraw : FilterDraw{
        override fun onDraw(canvas: Canvas) {
            var rectF = RectF(100f,100f,bitmap.width.toFloat(),bitmap.height.toFloat())
            paint.reset()
            paint.color = Color.RED
            canvas.drawRect(rectF,paint)

            /**
             * @param direction  指定光源的位置，长度为xxx的数组标量[x,y,z]
             * @param ambient    环境光的因子 （0~1），越接近0，环境光越暗
             * @param specular   镜面反射系数 越接近0，镜面反射越强
             * @param blurRadius 模糊半径 值越大，模糊效果越明显
             */
            paint.maskFilter = EmbossMaskFilter(floatArrayOf(1f,1f,1f),0.2f,60f,80f)

            var rectF1 = RectF(100f,(300 + bitmap.height).toFloat(),bitmap.width.toFloat(),(300 + bitmap.height + bitmap.height).toFloat())
            canvas.drawRect(rectF1,paint)
        }

        override fun setMode(mode: Int) {

        }
    }

    internal inner class ColorMatrixFilterDraw : FilterDraw{

        var myMode = 0

        override fun onDraw(canvas: Canvas) {
            paint.reset()
            canvas.drawBitmap(bitmap,100f,100f,paint)

            myMode = getRangeNum(myMode,9,0)
            var colorMatrix = getColorMatrix(myMode)
            paint.colorFilter = ColorMatrixColorFilter(colorMatrix!!)
            canvas.drawBitmap(bitmap,100f,(300 + bitmap.height).toFloat(),paint)

        }

        private fun getColorMatrix(myMode: Int): ColorMatrix? {
            var colorMatrix:ColorMatrix? = null

            when(myMode){
                0 ->
                    //平移运算--加法，给green通道添加颜色值
                    colorMatrix = ColorMatrix(
                        floatArrayOf(
                            1f,0f,0f,0f,0f,
                            0f,1f,0f,0f,100f,
                            0f,0f,1f,0f,0f,
                            0f,0f,0f,1f,0f
                        )
                    )
                1 ->
                    //相反效果 -- 底片效果
                    colorMatrix = ColorMatrix(
                        floatArrayOf(
                            -1f,0f,0f,0f,0f,
                            0f,-1f,0f,0f,0f,
                            0f,0f,-1f,0f,0f,
                            0f,0f,0f,-1f,0f
                        )
                    )
                2 ->
                    //放缩运算--颜色增强
                    colorMatrix = ColorMatrix(
                        floatArrayOf(
                            1.2f,0f,0f,0f,0f,
                            0f,1.2f,0f,0f,0f,
                            0f,0f,1.2f,0f,0f,
                            0f,0f,0f,1.2f,0f
                        )
                    )
                3 ->
                    /*
                    * 黑白照片
                    * 去色原理：只要把rgb三个颜色通道的色彩信息设置成一样，那么图像就会变成灰色，同时为了保证图像亮度不变，同一个通道里的R+G+B = 1
                    * */
                    colorMatrix = ColorMatrix(
                        floatArrayOf(
                            0.213f,0.715f,0.072f,0f,0f,
                            0.213f,0.715f,0.072f,0f,0f,
                            0.213f,0.715f,0.072f,0f,0f,
                            0f,0f,0f,1f,0f
                        )
                    )
                4 ->
                    //发色效果 ---（比如红色和绿色交换）
                    colorMatrix = ColorMatrix(
                        floatArrayOf(
                            0f,01f,0f,0f,0f,
                            1f,0f,0f,0f,0f,
                            0f,0f,1f,0f,0f,
                            0f,0f,0f,1f,0f
                        )
                    )
                5 ->
                    //复古效果
                    colorMatrix = ColorMatrix(
                        floatArrayOf(
                            1/2f,1/2f,1/2f,0f,0f,
                            1/3f,1/3f,1/3f,0f,0f,
                            1/4f,1/4f,1/4f,0f,0f,
                            0f,0f,0f,1f,0f
                        )
                    )
                6 ->
                    //颜色通道过滤
                    colorMatrix = ColorMatrix(
                        floatArrayOf(
                            1f,0f,0f,0f,0f,
                            0f,0f,0f,0f,0f,
                            0f,0f,0f,0f,0f,
                            0f,0f,0f,1f,0f
                        )
                    )
                7 ->{
                    //将一个颜色安系数进行放缩
                    colorMatrix = ColorMatrix()
                    colorMatrix.setScale(1.2f,1.2f,1.2f,1f)
                }
                8 ->{
                    //设置饱和度
                    colorMatrix = ColorMatrix();
                    colorMatrix.setSaturation(0.5f)
                }
                9 ->{
                    //aixs-- 0 红色轴，1，绿色，2，蓝色
                    // degrees -- 旋转的角度
                    colorMatrix = ColorMatrix();
                    colorMatrix.setRotate(0,50f)
                }
            }

            return colorMatrix
        }

        override fun setMode(mode: Int) {
            myMode = mode
        }

    }

    internal inner class LightingColorFilterDraw : FilterDraw{

        var myMode = 0

        override fun onDraw(canvas: Canvas) {
            paint.reset()

            canvas.drawBitmap(bitmap,100f,100f,paint)
            myMode = getRangeNum(myMode,4,0)
            paint.colorFilter = getColorFilter(myMode)
            canvas.drawBitmap(bitmap,100f,(300+bitmap.height).toFloat(),paint)

        }

        private fun getColorFilter(mode:Int):ColorFilter?{
            var colorFilter:ColorFilter? = null

            when(mode){
                0 -> colorFilter = LightingColorFilter(0x800000,0x3f0000)//红色亮度修改
                1 -> colorFilter = LightingColorFilter(0x008000,0x003f00)//绿色亮度修改
                2 -> colorFilter = LightingColorFilter(0x000080,0x00003f)//蓝色亮度修改
                3 -> colorFilter = LightingColorFilter(0x808080,0x3f3f3f)//rgb亮度修改
            }
            return colorFilter
        }

        override fun setMode(mode: Int) {
            myMode = mode
        }

    }

    internal inner class PorterDuffColorFilterDraw : FilterDraw{

        var myMode = 0

        override fun onDraw(canvas: Canvas) {
            paint.reset()
            canvas.drawBitmap(bitmap,100f,100f,paint)

            myMode = getRangeNum(myMode,4,0)
            paint.colorFilter = getColorFilter(myMode)
            canvas.drawBitmap(bitmap,100f,300f + bitmap.height.toFloat(),paint)
        }

        private fun getColorFilter(mode: Int):PorterDuffColorFilter?{
            var porterDuffColorFilter:PorterDuffColorFilter? = null
            when(mode){
                0 -> porterDuffColorFilter = PorterDuffColorFilter(Color.BLUE,PorterDuff.Mode.DST_IN)
                1 -> porterDuffColorFilter = PorterDuffColorFilter(Color.GREEN,PorterDuff.Mode.SRC_ATOP)
                2 -> porterDuffColorFilter = PorterDuffColorFilter(Color.RED,PorterDuff.Mode.SRC_IN)
            }
            return porterDuffColorFilter
        }


        override fun setMode(mode: Int) {
            myMode = mode
        }

    }

}