package com.example.androiddemokt.ui.shadow.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * Created by luoling on 2019/11/28.
 * description:
 */
class FiveChessView(context: Context,attributeSet: AttributeSet?,defStyleAttrs:Int) : View(context,attributeSet,defStyleAttrs) {

    constructor(context: Context,attributeSet: AttributeSet?):this(context,attributeSet,0){}

    constructor(context: Context):this(context,null){}

    /*
    * 棋盘网格格子的边长
    * */
    var SIZE = 120
    var chessRadius = 55
    /*
    * 发光点的偏移大小
    * */
    var OFFSET = 10
    var paint:Paint

    init {
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var row = height / SIZE
        var cols = width / SIZE
        paint.style = Paint.Style.STROKE
        //画棋盘
        drawChessBoard(canvas,row,cols)
        paint.style = Paint.Style.FILL
        //画棋子
        drawChess(canvas,4,4,ChessType.BLACK)
        drawChess(canvas,4,5,ChessType.BLACK)
        drawChess(canvas,5,4,ChessType.WHITE)
        drawChess(canvas,3,5,ChessType.WHITE)
    }

    private fun drawChess(canvas: Canvas, x: Int, y: Int, chessType: ChessType) {
        //定义棋子颜色
        val colorOuter = if (chessType == ChessType.BLACK) Color.BLACK else Color.GRAY
        val colorinter = Color.WHITE
        // 定义渐变，发光点向右下角偏移OFFSET
        val rg = RadialGradient(
            (x * SIZE + OFFSET).toFloat(),
            (y * SIZE + OFFSET).toFloat(),
            chessRadius.toFloat(),
            colorinter,
            colorOuter,
            Shader.TileMode.CLAMP
        )
        paint.shader = rg
        //画棋子
        setLayerType(View.LAYER_TYPE_SOFTWARE, paint)
        paint.setShadowLayer(5f, -4f, -4f, Color.parseColor("#aacccccc"))//给棋子加阴影
        canvas.drawCircle((x * SIZE).toFloat(), (y * SIZE).toFloat(), (SIZE / 2).toFloat(), paint)
    }

    private fun drawChessBoard(canvas: Canvas, rows: Int, cols: Int) {
        paint.color = Color.GRAY
        paint.setShadowLayer(0f, 0f, 0f, Color.GRAY)
        for (i in 0 until rows + 1) {
            canvas.drawLine(
                0f,
                (i * SIZE).toFloat(),
                (cols * SIZE).toFloat(),
                (i * SIZE).toFloat(),
                paint
            )
        }
        for (i in 0 until cols + 1) {
            canvas.drawLine(
                (i * SIZE).toFloat(),
                0f,
                (i * SIZE).toFloat(),
                (rows * SIZE).toFloat(),
                paint
            )
        }
    }

    enum class ChessType{
        BLACK,WHITE
    }

}