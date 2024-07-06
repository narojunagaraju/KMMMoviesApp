package com.nnaroju.movies.android

import android.app.Application
import com.nnaroju.movies.android.di.appModule
import com.nnaroju.movies.di.getSharedModules
import org.koin.core.context.startKoin

class MovieApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule + getSharedModules())
        }
    }
}