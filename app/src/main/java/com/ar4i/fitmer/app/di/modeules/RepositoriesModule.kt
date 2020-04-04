package com.ar4i.fitmer.app.di.modeules

import com.ar4i.fitmer.data.ISettingsRepository

object RepositoriesModule {

    private var settingsRepository = ISettingsRepository.SettingsRepository.apply {
        setSharedPreferences(SharedPrefsModule.provideSharedPrefs())
    }

    fun provideSettingsRepository(): ISettingsRepository = settingsRepository
}