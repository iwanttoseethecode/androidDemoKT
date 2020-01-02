package com.example.androiddemokt.ui.headerFooter

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.RecyclerView


/**
 * Created by luoling on 2019/12/9.
 * description:
 */
class WrapRecyclerView(context: Context,attributeSet: AttributeSet?,defStyleAttrs:Int) : RecyclerView(context,attributeSet,defStyleAttrs) {

    constructor(context: Context,attributeSet: AttributeSet?):this(context,attributeSet,0){}

    constructor(context: Context):this(context,null){}

    var mHeaderViewInfos : ArrayList<View>
    var mFooterViewInfos : ArrayList<View>
    var mAdapter:RecyclerView.Adapter<*>? = null

    init {
        mHeaderViewInfos = ArrayList<View>()
        mFooterViewInfos = ArrayList<View>()
    }

    fun addHeaderView(view : View){
        mHeaderViewInfos.add(view)
    }

    fun addFooterView(view:View){
        mFooterViewInfos.add(view)
    }

    override fun setAdapter(adapter: Adapter<RecyclerView.ViewHolder>?) {
        if (mHeaderViewInfos.size > 0 || mFooterViewInfos.size > 0){
            mAdapter = MyWrapAdapter(mHeaderViewInfos,mFooterViewInfos,adapter!!)
        }else{
            mAdapter = adapter
        }
        super.setAdapter(mAdapter)
    }

}