package com.rahul.zenithra

import android.app.Application
import com.rahul.zenithra.core.utils.AppContext
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ZenithApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppContext.initialize(this)

    }
}