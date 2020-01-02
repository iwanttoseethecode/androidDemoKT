package com.example.androiddemokt.ui.newAnimation

import android.animation.TimeInterpolator

/**
 * Created by luoling on 2019/11/23.
 * description:
 */
class MyInterpolator(private var mCycles:Float) :TimeInterpolator{

    override fun getInterpolation(input: Float): Float {
        return (Math.cos(2*mCycles*Math.PI*input)).toFloat()
    }

}
