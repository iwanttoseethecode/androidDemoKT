package com.example.androiddemokt.ui.mainPage.dataSource

import androidx.paging.PageKeyedDataSource

/**
 * Created by luoling on 2019/11/21.
 * description:
 */
class BasicNoteDatasource : PageKeyedDataSource<Int,TitleModel>(){

    private val PAGE_SIZE = 10

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, TitleModel>
    ) {
        var titleModels = generateBasicNoteData(0,9)

        titleModels?.let {
            callback.onResult(it,0,1)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, TitleModel>) {

        var currentPage = params.key;

        var startIndex = currentPage * PAGE_SIZE

        var endIndex = (currentPage + 1) * PAGE_SIZE - 1

        var titleModels = generateBasicNoteData(startIndex, endIndex)

        titleModels?.let {
            callback.onResult(it,currentPage + 1)
        }

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, TitleModel>) {
        var currentPage = params.key
        if (currentPage <= 0){
            return
        }

        var startIndex = (currentPage - 1) * PAGE_SIZE
        var endIndex = currentPage * PAGE_SIZE

        var titleModels = generateBasicNoteData(startIndex, endIndex)

        titleModels?.let {
            callback.onResult(it,currentPage - 1)
        }

    }
}