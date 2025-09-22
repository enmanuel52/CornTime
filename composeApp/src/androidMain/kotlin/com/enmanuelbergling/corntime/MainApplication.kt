package com.enmanuelbergling.corntime

import android.app.Application
import com.enmanuelbergling.corntime.di.initKoin
import org.koin.android.ext.koin.androidContext

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@MainApplication)
        }
    }
}