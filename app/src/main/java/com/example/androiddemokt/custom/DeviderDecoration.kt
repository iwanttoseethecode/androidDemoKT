package com.example.androiddemokt.custom

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by luoling on 2019/11/22.
 * description:
 */
class DeviderDecoration(var deviderHeight : Int): RecyclerView.ItemDecoration() {

    private var dividerPaint:Paint

    init{
        dividerPaint = Paint();
        dividerPaint.setColor(Color.parseColor("#f0f0f0"))
        this.deviderHeight = deviderHeight
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = deviderHeight;
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)

        var childCount = parent.childCount
        var left = parent.paddingLeft
        var right = parent.width - parent.paddingRight

        for (i in 0 until childCount - 1){
            var view = parent.getChildAt(i)
            var top = view.bottom
            var bottom = top + deviderHeight
            c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(),dividerPaint)
        }

    }

}