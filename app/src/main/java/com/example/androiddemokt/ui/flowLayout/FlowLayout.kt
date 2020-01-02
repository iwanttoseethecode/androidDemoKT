package com.example.androiddemokt.ui.flowLayout

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import java.util.ArrayList

/**
 * Created by luoling on 2019/12/3.
 * description:
 */
class FlowLayout : ViewGroup {
    //保存每一行的View
    private val flowLayoutList = ArrayList<List<View>>()
    //保存每行的最大高度
    private val heightRowList = ArrayList<Int>()

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
    }

    override fun generateLayoutParams(attrs: AttributeSet): ViewGroup.LayoutParams {
        return ViewGroup.MarginLayoutParams(this.context, attrs)
    }

    override fun generateLayoutParams(p: ViewGroup.LayoutParams): ViewGroup.LayoutParams {
        return ViewGroup.MarginLayoutParams(p)
    }

    override fun generateDefaultLayoutParams(): ViewGroup.LayoutParams {
        return ViewGroup.MarginLayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMeasureSpecSize = View.MeasureSpec.getSize(widthMeasureSpec)
        val widthMeasureSpecMode = View.MeasureSpec.getMode(widthMeasureSpec)
        val heightMeasureSpecSize = View.MeasureSpec.getSize(heightMeasureSpec)
        val heightMeasureSpecMode = View.MeasureSpec.getMode(heightMeasureSpec)

        //最终测量的宽度
        var measureWidth = 0
        //最终测量的高度
        var measureHeight = 0
        //某一行的宽度
        var lineWidth = 0
        //某一行高度
        var lineheight = 0

        //每一行的View集合
        var viewList: MutableList<View> = ArrayList()

        val childCount = childCount
        for (i in 0 until childCount) {
            val childView = getChildAt(i)
            measureChild(childView, widthMeasureSpec, heightMeasureSpec)
            val lp = childView.layoutParams as ViewGroup.MarginLayoutParams
            val childWidth = childView.measuredWidth + lp.leftMargin + lp.rightMargin
            val childHeight = childView.measuredHeight + lp.topMargin + lp.bottomMargin

            if (lineWidth + childWidth > widthMeasureSpecSize) {

                measureWidth = Math.max(lineWidth, measureWidth)
                measureHeight += lineheight

                heightRowList.add(lineheight)
                flowLayoutList.add(viewList)

                viewList = ArrayList()


                lineWidth = childWidth
                lineheight = childHeight

                viewList.add(childView)

            } else {

                viewList.add(childView)


                lineWidth += childWidth
                lineheight = Math.max(lineheight, childHeight)
            }

            if (i == childCount - 1) {

                heightRowList.add(lineheight)
                flowLayoutList.add(viewList)

                measureWidth = Math.max(lineWidth, measureWidth)
                measureHeight += lineheight
            }
        }

        if (widthMeasureSpecMode == View.MeasureSpec.EXACTLY) {
            measureWidth = widthMeasureSpecSize
        }

        if (heightMeasureSpecMode == View.MeasureSpec.EXACTLY) {
            measureHeight = heightMeasureSpecSize
        }

        setMeasuredDimension(measureWidth, measureHeight)
    }


    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var left = 0
        var top = 0
        var right = 0
        var buttom = 0
        var curLeft = 0
        var curTop = 0
        val lines = flowLayoutList.size
        for (i in 0 until lines) {
            val viewList = flowLayoutList[i]
            val lineViewSize = viewList.size

            for (j in 0 until lineViewSize) {
                val childView = viewList[j]
                val lp = childView.layoutParams as ViewGroup.MarginLayoutParams
                left = curLeft + lp.leftMargin
                top = curTop + lp.topMargin
                right = left + childView.measuredWidth
                buttom = top + childView.measuredHeight
                childView.layout(left, top, right, buttom)
                curLeft = right + lp.rightMargin
            }
            curLeft = 0
            curTop += heightRowList[i]
        }

        flowLayoutList.clear()
        heightRowList.clear()
    }

    interface OnItemClickListener {
        /**
         * @param v 点击的view
         * @param index 点击view的索引值
         */
        fun ItemClick(v: View, index: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        val count = childCount
        for (i in 0 until count) {
            val v = getChildAt(i)

            v.setOnClickListener { v -> listener.ItemClick(v, i) }
        }
    }
}
