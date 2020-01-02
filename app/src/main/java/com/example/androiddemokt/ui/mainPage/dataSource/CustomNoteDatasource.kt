package com.example.androiddemokt.ui.mainPage.dataSource

import androidx.paging.PageKeyedDataSource

/**
 * Created by luoling on 2019/11/21.
 * description:
 */
class CustomNoteDatasource :PageKeyedDataSource<Int, TitleModel>(){

    private val TAG:String by lazy {
        this::class.java.simpleName
    }

    private val PAGE_SIZE = 10;

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, TitleModel>
    ) {

        val titleModels = generateCustomNoteData(0,9)
        titleModels?.let {
            callback.onResult(it,0,1)
        }

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, TitleModel>) {
        var currentPage = params.key

        var startIndex:Int = currentPage * PAGE_SIZE
        var endIndex:Int = (currentPage + 1) * PAGE_SIZE - 1

        val titleModels = generateCustomNoteData(startIndex,endIndex)

        titleModels?.let {
            callback.onResult(it,currentPage + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, TitleModel>) {

        var currentPage = params.key
        if (currentPage <= 0){
            return
        }

        var startIndex:Int = (currentPage - 1) * PAGE_SIZE
        var endIndex:Int = currentPage * PAGE_SIZE -1

        val titleModel = generateCustomNoteData(startIndex, endIndex)

        titleModel?.let {
            callback.onResult(it,currentPage - 1)
        }

    }



}