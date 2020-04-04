package com.ar4i.fitmer.app.di.modeules

import android.content.Context
import com.ar4i.fitmer.BuildConfig

object SharedPrefsModule {
    private const val PREFS_NAME = "${BuildConfig.APPLICATION_ID}.shared_preferences"
    private val prefs = AppModule.provideApplication()
        .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun provideSharedPrefs() = prefs
}