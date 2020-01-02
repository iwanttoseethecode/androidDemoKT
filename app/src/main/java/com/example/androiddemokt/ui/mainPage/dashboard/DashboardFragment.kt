package com.example.androiddemokt.ui.mainPage.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.androiddemokt.R
import com.example.androiddemokt.custom.DeviderDecoration
import com.example.androiddemokt.databinding.FragmentDashboardBinding
import com.example.androiddemokt.ui.mainPage.ItemAdapter
import com.example.androiddemokt.utils.UIUtil

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)

        var binding:FragmentDashboardBinding = DataBindingUtil.inflate<FragmentDashboardBinding>(inflater,R.layout.fragment_dashboard,container,false)

        var adapter = ItemAdapter(context!!)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(DeviderDecoration(UIUtil.dp2px(1)))

        dashboardViewModel.titleItems.observe(this, Observer {
            adapter.submitList(it)
        })

        return binding.root
    }
}