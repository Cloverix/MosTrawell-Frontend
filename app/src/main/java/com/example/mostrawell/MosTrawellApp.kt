package com.example.mostrawell

import android.app.Application
import com.example.mostrawell.di.dataStoreModule
import com.example.mostrawell.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MosTrawellApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MosTrawellApp)
            modules(dataStoreModule, networkModule)
        }
    }
}