package com.example.androiddemokt.ui.newAnimation

import android.animation.TypeEvaluator
import android.graphics.PointF

/**
 * Created by luoling on 2019/11/23.
 * description:
 */
class MyEvalutor:TypeEvaluator<PointF> {
    override fun evaluate(fraction: Float, startValue: PointF?, endValue: PointF?): PointF {
        var pointf = PointF()
        //x方向移动函数，线性轨迹，匀速移动
        pointf.x = 400 * fraction
        //y方向移动函数，匀加速移动，总距离是1000 y=0.5*10*v*v;
        pointf.y = 2000f * 0.5f * fraction * fraction
        return pointf
    }
}