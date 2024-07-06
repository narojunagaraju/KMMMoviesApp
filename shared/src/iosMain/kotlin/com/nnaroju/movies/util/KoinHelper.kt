package com.nnaroju.movies.util

import com.nnaroju.movies.di.getSharedModules
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(getSharedModules())
    }
}