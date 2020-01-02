package com.example.androiddemokt.ui.drawble

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatImageView

/**
 * Created by luoling on 2019/12/2.
 * description:
 */
class SearchImageView(context: Context, attrs: AttributeSet?, defStyleAttrs:Int) : AppCompatImageView(context,attrs,defStyleAttrs) {

    constructor(context: Context, attrs: AttributeSet?):this(context,attrs,0){}

    constructor(context: Context):this(context,null){}

    var searchDrawble : SearchDrawable? = null

    fun setImageDrawable(drawable: SearchDrawable) {
        searchDrawble = drawable
        super.setImageDrawable(drawable)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action){
            MotionEvent.ACTION_DOWN ->{}
            MotionEvent.ACTION_MOVE -> {}
            MotionEvent.ACTION_UP ->{
                var state = searchDrawble?.mState
                if (state == SearchDrawable.STATE_ANIM_NONE){
                    searchDrawble?.startAnimation()
                }else if(state == SearchDrawable.STATE_ANIM_START){
                    searchDrawble?.stopAnimation()
                }else if(state == SearchDrawable.STATE_ANIM_STOP){
                    searchDrawble?.resetAnimation()
                }
            }
        }
        return true
    }

}