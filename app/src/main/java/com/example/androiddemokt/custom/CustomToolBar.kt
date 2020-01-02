package com.example.androiddemokt.custom

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import com.example.androiddemokt.R
import com.example.androiddemokt.databinding.CustomToolBarBinding
import kotlinx.android.synthetic.main.custom_tool_bar.view.*

/**
 * Created by luoling on 2019/11/21.
 * description:
 */
class CustomToolBar : LinearLayout {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {

       var binding = DataBindingUtil.inflate<CustomToolBarBinding>(LayoutInflater.from(context),R.layout.custom_tool_bar,this,true)

        mBinding = binding

        orientation = LinearLayout.HORIZONTAL

        var typedArray:TypedArray = context.obtainStyledAttributes(attrs,R.styleable.CustomToolStatusbar)

        var title = typedArray.getString(R.styleable.CustomToolStatusbar_title)
        binding.titleTV.text = title

        var bgColor = typedArray.getColor(R.styleable.CustomToolStatusbar_bg_color,0)
        binding.toolbar.background = ColorDrawable(bgColor)

        typedArray.recycle()
    }

    public lateinit var mBinding:CustomToolBarBinding

}
