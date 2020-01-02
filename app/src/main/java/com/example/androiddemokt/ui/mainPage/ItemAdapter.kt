package com.example.androiddemokt.ui.mainPage

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androiddemokt.R
import com.example.androiddemokt.databinding.ItemTextviewBinding
import com.example.androiddemokt.ui.mainPage.dataSource.TitleModel

/**
 * Created by luoling on 2019/11/22.
 * description:
 */
class ItemAdapter(var context:Context) : PagedListAdapter<TitleModel,ItemAdapter.MyViewHolder>(TitleModelDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var binding = DataBindingUtil.inflate<ItemTextviewBinding>(LayoutInflater.from(context), R.layout.item_textview,parent,false)


        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var titleModel:TitleModel? = getItem(position)

        holder.binding.classtext.text = titleModel?.title
        holder.bind(View.OnClickListener {
            var intent:Intent = Intent(context,titleModel?.intentObject);
            context.startActivity(intent)
        })

    }

    class MyViewHolder(var binding:ItemTextviewBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(listener:View.OnClickListener){
            binding.apply {
                this.listener = listener
                executePendingBindings()
            }
        }
    }

    class TitleModelDiffCallBack:DiffUtil.ItemCallback<TitleModel>(){

        override fun areItemsTheSame(oldItem: TitleModel, newItem: TitleModel): Boolean {
            return oldItem.intentObject == newItem.intentObject
        }

        override fun areContentsTheSame(oldItem: TitleModel, newItem: TitleModel): Boolean {
            return oldItem == newItem
        }

    }

}