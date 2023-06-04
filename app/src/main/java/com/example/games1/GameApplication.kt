package com.example.games1

import android.app.Application



import com.example.games1.di.AppContainer
import com.example.games1.di.DefaultAppContainer

class Games1Application: Application() {
    /** AppContainer instance used by the rest of classes to obtain dependencies */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}