package com.example.androiddemokt.ui.headerFooter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androiddemokt.R

/**
 * Created by luoling on 2019/12/9.
 * description:
 */
class MyAdapter(listData:ArrayList<String>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    var listData:ArrayList<String>

    init {
        this.listData = listData
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        var view = layoutInflater.inflate(android.R.layout.simple_list_item_1,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView.text = listData.get(position)
    }


    class MyViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView
        init {
            textView = itemView.findViewById(android.R.id.text1)
        }
    }
}