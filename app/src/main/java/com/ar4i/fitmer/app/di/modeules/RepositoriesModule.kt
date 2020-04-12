package com.ar4i.fitmer.app.di.modeules

import com.ar4i.fitmer.data.ISettingsRepository
import com.ar4i.fitmer.data.ISoundsRepository

object RepositoriesModule {

    private var settingsRepository = ISettingsRepository.SettingsRepository.apply {
        setSharedPreferences(SharedPrefsModule.provideSharedPrefs())
    }

    private var soundsRepository = ISoundsRepository.SoundsRepository.apply {
        setContext(AppModule.provideContext())
    }

    fun provideSettingsRepository(): ISettingsRepository = settingsRepository

    fun provideSoundsRepository(): ISoundsRepository = soundsRepository
}