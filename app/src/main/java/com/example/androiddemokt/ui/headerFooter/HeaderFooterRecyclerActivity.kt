package com.example.androiddemokt.ui.headerFooter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androiddemokt.R
import kotlinx.android.synthetic.main.activity_header_footer_recycler.*

class HeaderFooterRecyclerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_header_footer_recycler)

        init()

    }

    fun init(){
        var headerView = TextView(this)
        var layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        headerView.layoutParams = layoutParams
        headerView.text = "headerView添加头部"
        wrapRecyclerView.addHeaderView(headerView)

        var footerView = TextView(this)
        var layoutParams2 = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        footerView.layoutParams = layoutParams2
        footerView.text = "footerView添加头部"
        wrapRecyclerView.addFooterView(footerView)

        var list = ArrayList<String>()
        for (i in 0 until 30){
            list.add(i,"item" + i)
        }
        var adapter = MyAdapter(list)
        wrapRecyclerView.adapter = adapter
        wrapRecyclerView.layoutManager = LinearLayoutManager(this)

    }

}
