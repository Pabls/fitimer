package com.ar4i.fitmer.app.di.modeules

import com.ar4i.fitmer.domain.ISettingsIteractor

object InteractorsModule {
    fun provideSettingsIteractor(): ISettingsIteractor =
        ISettingsIteractor.SettingsIteractor(RepositoriesModule.provideSettingsRepository())
}