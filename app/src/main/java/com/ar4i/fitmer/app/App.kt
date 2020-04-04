package com.ar4i.fitmer.app

import android.app.Application
import com.ar4i.fitmer.BuildConfig
import com.ar4i.fitmer.app.di.components.IApplicationComponent
import com.facebook.stetho.Stetho

class App : Application() {
    companion object {
        lateinit var applicationComponent: IApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        applicationComponent = IApplicationComponent.ApplicationComponent(application = this)
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }
}