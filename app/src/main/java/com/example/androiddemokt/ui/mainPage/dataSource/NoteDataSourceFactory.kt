package com.example.androiddemokt.ui.mainPage.dataSource

import androidx.paging.DataSource

/**
 * Created by luoling on 2019/11/21.
 * description:
 */
class NoteDataSourceFactory(val noteType:Int) : DataSource.Factory<Int,TitleModel>(){

    companion object {
        const val BasicNoteType = 0;
        const val CustomNoteType = 1;
    }

    override fun create(): DataSource<Int, TitleModel> {
        when(noteType){
            BasicNoteType -> return BasicNoteDatasource()
            CustomNoteType -> return CustomNoteDatasource()
            else -> return BasicNoteDatasource()
        }
    }

}