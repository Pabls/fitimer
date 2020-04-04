package com.ar4i.fitmer.app.di.components

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ar4i.fitmer.app.App
import com.ar4i.fitmer.app.di.modeules.AppModule

interface IApplicationComponent {

    fun provideViewModelsFactory(bundle: Bundle? = null): ViewModelProvider.Factory

    class ApplicationComponent :
        IApplicationComponent {

        constructor(application: App) {
            AppModule.setApplication(application)
        }

        override fun provideViewModelsFactory(bundle: Bundle?): ViewModelProvider.Factory =
            ViewModelsFactory.also { it.setBundle(bundle) }
    }
}