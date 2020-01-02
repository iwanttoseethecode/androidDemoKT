package com.example.androiddemokt.baseComponent

import android.app.Application
import android.content.Context
import android.os.Handler

/**
 * Created by luoling on 2019/11/22.
 * description:
 */
class BaseApplication : Application() {

    companion object{
        var mMainHandler: Handler = Handler()
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }

}