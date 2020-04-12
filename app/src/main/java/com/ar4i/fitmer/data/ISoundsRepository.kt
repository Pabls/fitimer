package com.ar4i.fitmer.data

import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import com.ar4i.fitmer.R
import java.lang.Exception

interface ISoundsRepository {
    suspend fun soundStart()
    suspend fun soundEnd()
    suspend fun soundTimer()

    object SoundsRepository : ISoundsRepository {
        const val START_DIR = R.raw.start
        const val STOP_DIR = R.raw.stop
        const val TIMER_EN = R.raw.timer_en
        const val TIMER_RU = R.raw.timer_ru

        private lateinit var context: Context

        fun setContext(context: Context) {
            this.context = context
        }

        override suspend fun soundStart() {
            startSound(START_DIR)
        }

        override suspend fun soundEnd() {
            startSound(STOP_DIR)
        }

        override suspend fun soundTimer() {
            val locale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                context.resources.configuration.locales[0].country
            } else {
                context.resources.configuration.locale.country
            }
            startSound(
                when (locale) {
                    "RU" -> TIMER_RU
                    else -> TIMER_EN
                }
            )
        }

        private fun startSound(id: Int) {
            try {
                val mediaPlayer = MediaPlayer.create(context, id)
                mediaPlayer.start()
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }
}