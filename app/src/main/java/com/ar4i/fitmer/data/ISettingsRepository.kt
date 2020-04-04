package com.ar4i.fitmer.data

import android.content.SharedPreferences
import java.lang.Exception

interface ISettingsRepository {
    suspend fun saveMode(isDark: Boolean)
    suspend fun isDarkMode(): Boolean

    object SettingsRepository : ISettingsRepository {
        private const val APP_MODE = "APP_MODE"
        private lateinit var prefs: SharedPreferences
        fun setSharedPreferences(prefs: SharedPreferences) {
            this.prefs = prefs
        }

        override suspend fun saveMode(isDark: Boolean) {
            try {
                val mode = if (isDark) AppMode.Dark else AppMode.Light
                getEditor().putString(APP_MODE, mode.name).commit()
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }

        override suspend fun isDarkMode(): Boolean =
            try {
                val mode = prefs.getString(APP_MODE, AppMode.Light.name)
                mode == AppMode.Dark.name
            } catch (ex: Exception) {
                false
            }

        private fun getEditor() = prefs.edit()

        private enum class AppMode {
            Dark, Light
        }
    }
}