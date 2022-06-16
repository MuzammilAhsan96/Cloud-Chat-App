package com.application.cloudchatapp.base

import android.app.Application
import android.support.multidex.MultiDexApplication
import com.application.cloudchatapp.utils.AppUtil

class App : MultiDexApplication() {


    companion object {
        var INSTANCE: Application = App()
    }

    override fun onCreate() {
        super.onCreate()
        // FirebaseApp.initializeApp(this)
        INSTANCE = this@App
        AppUtil.generateFCMToken(INSTANCE)
    }


}
