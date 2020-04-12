package com.ar4i.fitmer.domain

import com.ar4i.fitmer.data.ISettingsRepository

interface ISettingsIteractor {

    suspend fun saveMode(isDark: Boolean)
    suspend fun isDarkMode(): Boolean
    suspend fun launchScreenShown()
    suspend fun isLaunchScreenShown(): Boolean

    class SettingsIteractor(private val settingsRepository: ISettingsRepository) :
        ISettingsIteractor {

        override suspend fun saveMode(isDark: Boolean) {
            settingsRepository.saveMode(isDark)
        }

        override suspend fun isDarkMode(): Boolean {
            return settingsRepository.isDarkMode()
        }

        override suspend fun launchScreenShown() {
            settingsRepository.launchScreenShown()
        }

        override suspend fun isLaunchScreenShown(): Boolean {
            return settingsRepository.isLaunchScreenShown()
        }
    }
}