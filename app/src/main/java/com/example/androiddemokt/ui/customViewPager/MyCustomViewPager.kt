package com.example.androiddemokt.ui.customViewPager

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewConfiguration
import android.view.ViewGroup
import android.widget.Scroller
import androidx.core.view.ViewConfigurationCompat

/**
 * Created by luoling on 2019/12/5.
 * description:
 */
class MyCustomViewPager(context:Context,attrs:AttributeSet?,defStyleAttr :Int) : ViewGroup(context, attrs, defStyleAttr) {

    constructor(context: Context,attrs: AttributeSet?):this(context,attrs,0){}

    constructor(context: Context):this(context,null){}

    var mTouchSlop:Int
    var moveX:Float = 0f
    var lastMoveX:Float = 0f
    var leftBound:Int = 0
    var rightBound:Int = 0
    var mScroller:Scroller

    init {
        // 第一步，创建Scroller的实例
        mScroller = Scroller(context)
        var configuration = ViewConfiguration.get(context)
        //系统允许的滑动的最小距离
        mTouchSlop = configuration.getScaledPagingTouchSlop()
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        measureChildren(widthMeasureSpec,heightMeasureSpec)
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        if (changed){
            for (i in 0 until childCount){
                var child = getChildAt(i)
                child.layout(i * child.measuredWidth,0,(i + 1) * child.measuredHeight,child.measuredHeight)
                child.isClickable = true
            }
        }
        //左边界
        leftBound = getChildAt(0).left
        //右边界
        rightBound = getChildAt(childCount - 1).right
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {

        when(ev.action){
            MotionEvent.ACTION_DOWN -> {
                lastMoveX = ev.rawX
            }
            MotionEvent.ACTION_MOVE -> {
                moveX = ev.rawX
                var xDiff = Math.abs(moveX - lastMoveX)
                if (xDiff > mTouchSlop){
                    return true
                }
            }
            MotionEvent.ACTION_UP -> {}
        }

        return super.onInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        /*
        * 如何有滑动的效果？
        * View.scrollTo(x,y); 让view滚动到某个位置。
        * View.scrollBy(x,y); 让View相对于它现在的位置滚动一段距离。
        * 注意：上面两种方法都是滑动View里面的内容，即里面的所有子控件。
        * */
        when(event.action){
            MotionEvent.ACTION_DOWN -> {
            }
            MotionEvent.ACTION_MOVE -> {
                moveX = event.rawX
                var scrollDx = lastMoveX - moveX
                if (scrollX + scrollDx < leftBound ){
                    scrollTo(leftBound,0)
                    return true
                }else if (scrollY + scrollDx + width > rightBound){
                    scrollTo(rightBound,0)
                    return true
                }
                scrollBy(scrollDx.toInt(),0)
                lastMoveX = moveX
            }
            MotionEvent.ACTION_UP -> {
                //除法一定要用浮点数
                var targetIndex = Math.round(scrollX.toFloat() / width)
                if (targetIndex < 0){
                    targetIndex = 0
                }else if (targetIndex >= childCount){
                    targetIndex = childCount - 1
                }

                var upSmoothScrollDx = getChildAt(targetIndex).left - scrollX

                // 第二步，调用startScroll()方法来初始化滚动数据并刷新界面,startScroll()方法接收四个参数，
                // 第一个参数是滚动开始时X的坐标，第二个参数是滚动开始时Y的坐标，第三个参数是横向滚动的距离，正值表示向左滚动，第四个参数是纵向滚动的距离，正值表示向上滚动
                mScroller.startScroll(scrollX,0,upSmoothScrollDx,0)
                invalidate()
                return true
            }
        }

        return super.onTouchEvent(event)
    }

    override fun computeScroll() {
        //第三步，重写computeScroll()方法，并在其内部完成平滑滚动的逻辑
        if (mScroller.computeScrollOffset()){//返回true证明滑动动画还没结束
            scrollTo(mScroller.currX,mScroller.currY)
            invalidate()
        }

    }

    fun requestParentDisallowInterceptTouchEvent(disallowIntercept:Boolean){
        parent?.requestDisallowInterceptTouchEvent(disallowIntercept)
    }

}