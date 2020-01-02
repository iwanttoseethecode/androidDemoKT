package com.example.androiddemokt.utils

import android.util.DisplayMetrics
import android.util.TypedValue
import com.example.androiddemokt.baseComponent.BaseApplication

/**
 * Created by luoling on 2019/11/22.
 * description:
 */
object UIUtil {

//    fun dp2px(dp:Int):Int{
//        var metrics : DisplayMetrics = BaseApplication.context.resources.displayMetrics
//        var density:Float = metrics.density
//
//        return (dp * density + 0.5f).toInt()
//    }

    fun dp2px(dp:Int):Int{
        var typedValue = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp.toFloat(),BaseApplication.context.resources.displayMetrics)

        return typedValue.toInt();
    }

    fun px2sp(pxValue:Float):Int{
        var metrics:DisplayMetrics= BaseApplication.context.resources.displayMetrics
        var density:Float = metrics.scaledDensity

        return (pxValue/density + 0.5f).toInt()
    }

    fun sp2px(spValue: Float):Int{
        var metrics:DisplayMetrics = BaseApplication.context.resources.displayMetrics
        var density:Float = metrics.scaledDensity

        return (spValue * density + 0.5f).toInt()
    }

}