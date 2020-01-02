package com.example.androiddemokt.ui.mainPage.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.androiddemokt.R
import com.example.androiddemokt.custom.DeviderDecoration
import com.example.androiddemokt.databinding.FragmentHomeBinding
import com.example.androiddemokt.ui.mainPage.ItemAdapter
import com.example.androiddemokt.utils.UIUtil
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        var binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater,R.layout.fragment_home,container,false)

        var adapter = ItemAdapter(context!!);

        binding.recyclerView.adapter = adapter;
        binding.recyclerView.addItemDecoration(DeviderDecoration(UIUtil.dp2px(1)))


        homeViewModel.titleItems.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        recyclerView.requestFocus()
        recyclerView.requestFocusFromTouch()
    }

}