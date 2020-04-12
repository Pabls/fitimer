package com.ar4i.fitmer.app.di.modeules

import com.ar4i.fitmer.domain.ISettingsIteractor
import com.ar4i.fitmer.domain.ISoundsInteractor

object InteractorsModule {
    fun provideSettingsIteractor(): ISettingsIteractor =
        ISettingsIteractor.SettingsIteractor(RepositoriesModule.provideSettingsRepository())

    fun provideSoundsInteractor(): ISoundsInteractor =
        ISoundsInteractor.SoundsInteractor(RepositoriesModule.provideSoundsRepository())
}