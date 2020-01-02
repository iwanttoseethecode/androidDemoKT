package com.example.androiddemokt.ui.drawble

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.LinearLayout

/**
 * Created by luoling on 2019/12/2.
 * description:
 */
class GallaryHorizonalScrollView : HorizontalScrollView {

    private var container: LinearLayout? = null
    private var centerX: Int = 0
    private var icon_width: Int = 0

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    private fun init() {
        val params = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        container = LinearLayout(context)
        container!!.layoutParams = params
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        val v = container!!.getChildAt(0)
        icon_width = v.width
        centerX = width / 2
        //中心图片的左边界
        centerX = centerX - icon_width / 2
        container!!.setPadding(centerX, 0, centerX, 0)
    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        reveal()
    }

    private fun reveal() {
        val scrollX = scrollX
        val index_left = scrollX / icon_width
        val index_right = index_left + 1

        for (i in 0 until container!!.childCount) {
            if (i == index_left || i == index_right) {
                val ratio = 5000f / icon_width
                val iv_left = container!!.getChildAt(index_left) as ImageView
                iv_left.setImageLevel((5000 - scrollX % icon_width * ratio).toInt())

                if (index_right < container!!.childCount) {
                    val iv_right = container!!.getChildAt(index_right) as ImageView
                    iv_right.setImageLevel((10000 - scrollX % icon_width * ratio).toInt())
                }
            } else {
                val iv = container!!.getChildAt(i) as ImageView
                iv.setImageLevel(0)
            }
        }
    }

    fun addImageViews(revealDrawables: Array<Drawable?>) {
        for (i in revealDrawables.indices) {
            val img = ImageView(context)
            img.setImageDrawable(revealDrawables[i])
            container!!.addView(img)
            if (i == 0) {
                img.setImageLevel(5000)
            }
        }
        addView(container)
    }

}
