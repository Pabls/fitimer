package com.ar4i.fitmer.app.di.components

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ar4i.fitmer.app.di.modeules.AppModule
import com.ar4i.fitmer.app.di.modeules.InteractorsModule
import com.ar4i.fitmer.presentation.base.EmptyViewModel
import com.ar4i.fitmer.presentation.graphs.GraphsViewModel
import com.ar4i.fitmer.presentation.settings.SettingsViewModel
import com.ar4i.fitmer.presentation.splash.SplashViewModel
import com.ar4i.fitmer.presentation.timer.TimerViewModel
import com.ar4i.fitmer.presentation.timer.intervals.IntervalsViewModel
import com.ar4i.fitmer.presentation.timer.tabata.TabataViewModel
import com.ar4i.fitmer.presentation.workouts.WorkoutsViewModel

object ViewModelsFactory : ViewModelProvider.Factory {

    private val app = AppModule.provideApplication()
    private val interactorsModule = InteractorsModule
    private var bundle: Bundle? = null

    fun setBundle(bundle: Bundle? = null) {
        this.bundle = bundle
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            SplashViewModel::class.java -> SplashViewModel(
                interactorsModule.provideSettingsIteractor(),
                app
            )
            WorkoutsViewModel::class.java -> WorkoutsViewModel(app)
            GraphsViewModel::class.java -> GraphsViewModel(app)
            TimerViewModel::class.java -> TimerViewModel(app)
            SettingsViewModel::class.java -> SettingsViewModel(app)
            TabataViewModel::class.java -> TabataViewModel(
                interactorsModule.provideSoundsInteractor(),
                app
            )
            IntervalsViewModel::class.java -> IntervalsViewModel(app)
            else -> EmptyViewModel(app)
        } as T
    }
}