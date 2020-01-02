package com.example.androiddemokt.ui.scrollIssueRecyclerView

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ViewConfiguration
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by luoling on 2019/12/9.
 * description:
 */
class ScrollIssueRecyclerView(context: Context,attributeSet: AttributeSet?,defStyleAttrs :Int) : RecyclerView(context,attributeSet,defStyleAttrs){

    constructor(context: Context,attributeSet: AttributeSet?):this(context,attributeSet,0){}

    constructor(context: Context):this(context,null){}

    var mLastTouchX : Int = 0
    var mLastTouchY : Int = 0
    var mInitialTouchX : Int = 0
    var mInitialTouchY : Int = 0

    var mScrollPointerId:Int = -1

    var touchSlop : Int = 0

    init {
        var vc = ViewConfiguration.get(context)
        touchSlop = vc.scaledTouchSlop
    }

    /**
     * Configure the scrolling touch slop for a specific use case.
     *
     * Set up the RecyclerView's scrolling motion threshold based on common usages.
     * Valid arguments are [.TOUCH_SLOP_DEFAULT] and [.TOUCH_SLOP_PAGING].
     *
     * @param slopConstant One of the `TOUCH_SLOP_` constants representing
     * the intended usage of this RecyclerView
     */
    override fun setScrollingTouchSlop(slopConstant: Int) {
        val vc = ViewConfiguration.get(context)
        when (slopConstant) {
            // fall-through
            TOUCH_SLOP_DEFAULT -> touchSlop = vc.scaledTouchSlop

            TOUCH_SLOP_PAGING -> touchSlop = vc.scaledPagingTouchSlop
            else -> {

                touchSlop = vc.scaledTouchSlop
            }
        }
    }

    override fun onInterceptTouchEvent(e: MotionEvent): Boolean {

        val canScrollHorizontally = layoutManager?.canScrollHorizontally()
        val canScrollVertically = layoutManager?.canScrollVertically()

        val action = e.actionMasked
        val actionIndex = e.actionIndex
        when(action){
            MotionEvent.ACTION_DOWN -> {
                mLastTouchX = (e.getX(actionIndex) + 0.5f).toInt()
                mInitialTouchX = mLastTouchX
                mLastTouchY = (e.getY(actionIndex) + 0.5f).toInt()
                mInitialTouchY = mLastTouchY
            }
            MotionEvent.ACTION_POINTER_DOWN -> {
                mLastTouchX = (e.getX(actionIndex) + 0.5f).toInt()
                mInitialTouchX = mLastTouchX
                mLastTouchY = (e.getY(actionIndex) + 0.5f).toInt()
                mInitialTouchY = mLastTouchY
            }

            MotionEvent.ACTION_MOVE -> {
                val index = e.findPointerIndex(mScrollPointerId)
                if (index < 0) {
                    return false
                }

                val x = (e.getX(index) + 0.5f).toInt()
                val y = (e.getY(index) + 0.5f).toInt()
                if (scrollState != SCROLL_STATE_DRAGGING) {
                    val dx = x - mInitialTouchX
                    val dy = y - mInitialTouchY
                    var startScroll = false
                    if (canScrollHorizontally!! && Math.abs(dx) > touchSlop && (canScrollVertically!! || Math.abs(dx) > Math.abs(dy))) {
                        mLastTouchX = x
                        startScroll = true
                    }
                    if (canScrollVertically!! && Math.abs(dy) > touchSlop && (canScrollHorizontally!! || Math.abs(dy) > Math.abs(dx))) {
                        mLastTouchY = y
                        startScroll = true
                    }
                    return startScroll && super.onInterceptTouchEvent(e)
                }

            }
            else -> {
                return super.onInterceptTouchEvent(e)
            }
        }

        return super.onInterceptTouchEvent(e)
    }

}