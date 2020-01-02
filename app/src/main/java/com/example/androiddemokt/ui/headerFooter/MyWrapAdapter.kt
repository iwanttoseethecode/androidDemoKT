package com.example.androiddemokt.ui.headerFooter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import java.util.ArrayList

/**
 * Created by luoling on 2019/12/9.
 * description:
 */
class MyWrapAdapter(
    private val headerList: ArrayList<View>,
    private val footerList: ArrayList<View>,
    adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val mAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>

    init {
        this.mAdapter = adapter
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == RecyclerView.INVALID_TYPE) {
            return MyViewHolder(headerList[0])
        } else if (viewType == RecyclerView.INVALID_TYPE - 1) {
            return MyViewHolder(footerList[0])
        }
        return mAdapter!!.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = getItemViewType(position)
        if (viewType == RecyclerView.INVALID_TYPE) {
            return
        } else if (viewType == RecyclerView.INVALID_TYPE - 1) {
            return
        } else {
            mAdapter!!.onBindViewHolder(holder, position - headerList.size)
        }
    }

    override fun getItemCount(): Int {
        return if (mAdapter != null) mAdapter.itemCount + footerList.size + headerList.size else footerList.size + headerList.size
    }

    override fun getItemViewType(position: Int): Int {

        val numHeaders = headerList.size
        if (numHeaders > position) {
            return RecyclerView.INVALID_TYPE
        }
        val adjPosition = position - numHeaders
        var adapterCount = 0
        if (mAdapter != null) {
            adapterCount = mAdapter.itemCount
            if (adapterCount > adjPosition) {
                return mAdapter.getItemViewType(adjPosition)
            }
        }
        return RecyclerView.INVALID_TYPE - 1
    }

    internal inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
