package com.ar4i.fitmer.app.di.modeules

import android.content.Context
import com.ar4i.fitmer.app.App

object AppModule {
    private lateinit var app: App

    fun setApplication(application: App) {
        this.app = application
    }

    fun provideContext(): Context = app
    fun provideApplication(): App = app
}