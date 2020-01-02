package com.example.androiddemokt.ui.mainPage.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.androiddemokt.ui.mainPage.dataSource.NoteDataSourceFactory
import com.example.androiddemokt.ui.mainPage.dataSource.NoteDataSourceFactory.Companion.CustomNoteType
import com.example.androiddemokt.ui.mainPage.dataSource.TitleModel

class DashboardViewModel : ViewModel() {

    var titleItems:LiveData<PagedList<TitleModel>>

    init {

        var config = PagedList.Config.Builder()
            .setPageSize(10)
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .build()

        titleItems = LivePagedListBuilder<Int,TitleModel>(NoteDataSourceFactory(CustomNoteType),config).build()
    }

}