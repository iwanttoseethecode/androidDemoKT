package com.example.androiddemokt.ui.waterfallLayout

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup

/**
 * Created by luoling on 2019/12/3.
 * description:
 */
class WaterfallLayout(context: Context,attributeSet: AttributeSet?,defStyleAttr:Int ) : ViewGroup(context,attributeSet,defStyleAttr){

    constructor(context: Context,attributeSet: AttributeSet?):this(context,attributeSet,0){}

    constructor(context: Context):this(context,null){}

    var mColumns:Int = 3
    var mHorizontalSpace = 20
    var mVerticalSpace = 20
    var mChildWidth = 0
    lateinit var mTop:IntArray

    init {
        mTop = IntArray(mColumns)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var widthMeasureSpecSize = MeasureSpec.getSize(widthMeasureSpec)
        var widthMeasureSpecMode = MeasureSpec.getMode(widthMeasureSpec)
        var heightMeasureSpecSize = MeasureSpec.getSize(heightMeasureSpec)
        var heightMeasureSpecMode = MeasureSpec.getMode(heightMeasureSpec)

        var measureWidth = 0
        var measureHeight = 0

        measureChildren(widthMeasureSpec,heightMeasureSpec)

        mChildWidth = (widthMeasureSpecSize - (mColumns - 1)*mHorizontalSpace) / mColumns

        var childCount = childCount
        if (childCount < mColumns){
            measureWidth = mChildWidth * childCount + (childCount - 1) * mHorizontalSpace
        }else{
            measureWidth = widthMeasureSpecSize
        }

        clearTop()

        for(i in 0 until childCount){
            var child = getChildAt(i)
            var childHeight = child.getMeasuredHeight() * mChildWidth / child.getMeasuredWidth();

            var minColumn = minHeightColumn()
            var wp = child.layoutParams as WaterfallLayoutParams
            wp.left = minColumn * (mChildWidth + mHorizontalSpace)
            wp.top = mTop[minColumn]
            wp.right = wp.left + mChildWidth
            wp.bottom = wp.top + childHeight

            mTop[minColumn] += mVerticalSpace + childHeight
        }

        measureHeight = maxHeight()

        if (widthMeasureSpecMode == MeasureSpec.EXACTLY && heightMeasureSpecMode == MeasureSpec.EXACTLY){
            measureWidth = widthMeasureSpecSize
            measureHeight = heightMeasureSpecSize
        }

        setMeasuredDimension(measureWidth,measureHeight)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var childCount = childCount
        clearTop()
        for(i in 0 until childCount){
            var child = getChildAt(i)
            var wp = child.layoutParams as WaterfallLayoutParams
            child.layout(wp.left,wp.top,wp.right,wp.bottom)
        }
    }

    private fun clearTop(){
        var length = mTop.size
        for (i in 0 until length){
            mTop[i] = 0
        }
    }

    private fun minHeightColumn():Int{
        var minColumns = 0
        var minHeight = mTop[0]
        for (i in 0 until mColumns){
            if (minHeight > mTop[i]){
                minColumns = i;
            }
        }
        return minColumns
    }

    private fun maxHeight():Int{
        var maxHeight = 0
        for(i in 0 until mColumns){
            if (maxHeight < mTop[i]){
                maxHeight = mTop[i]
            }
        }
        return maxHeight
    }

    override fun generateDefaultLayoutParams(): LayoutParams {
        return WaterfallLayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT)
    }

    override fun generateLayoutParams(p: LayoutParams): LayoutParams {
        return WaterfallLayoutParams(p as MarginLayoutParams)
    }

    override fun generateLayoutParams(attrs: AttributeSet): LayoutParams {
        return WaterfallLayoutParams(context,attrs)
    }

    internal class WaterfallLayoutParams : MarginLayoutParams{

        constructor(context: Context,attrs:AttributeSet):super(context,attrs){}

        constructor(width:Int,height:Int):super(width, height){}

        constructor(source : MarginLayoutParams):super(source){}

        var left:Int = 0
        var top:Int = 0
        var right:Int = 0
        var bottom:Int = 0
    }

    public interface OnItemClickListener{
        fun itemClick(v:View,index :Int)
    }

    fun setOnItemClickListener(listener:OnItemClickListener){
        var childCount = childCount
        for(i in 0 until childCount){
            var v = getChildAt(i)
            v.setOnClickListener(object: OnClickListener{
                override fun onClick(v: View) {
                    listener.itemClick(v,i)
                }
            })
        }
    }

}