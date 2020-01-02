package com.example.androiddemokt.ui.mainPage.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.androiddemokt.ui.mainPage.dataSource.NoteDataSourceFactory
import com.example.androiddemokt.ui.mainPage.dataSource.NoteDataSourceFactory.Companion.BasicNoteType
import com.example.androiddemokt.ui.mainPage.dataSource.TitleModel

class HomeViewModel : ViewModel() {

    var titleItems:LiveData<PagedList<TitleModel>>;

    init {
        var config=PagedList.Config.Builder()
            .setPageSize(10) //分页加载数量
            .setEnablePlaceholders(false) //当item为null是否使用Placeholders展示
            .setInitialLoadSizeHint(10)//预加载数量
            .build()
        titleItems = LivePagedListBuilder<Int,TitleModel>(NoteDataSourceFactory(BasicNoteType), config).build()
    }



}