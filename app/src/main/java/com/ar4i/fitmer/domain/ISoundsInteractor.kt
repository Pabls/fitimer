package com.ar4i.fitmer.domain

import com.ar4i.fitmer.data.ISoundsRepository

interface ISoundsInteractor {
    suspend fun soundStart()
    suspend fun soundEnd()
    suspend fun soundTimer()

    class SoundsInteractor(private var soundsRepository: ISoundsRepository) : ISoundsInteractor {
        override suspend fun soundStart() {
            soundsRepository.soundStart()
        }

        override suspend fun soundEnd() {
            soundsRepository.soundEnd()
        }

        override suspend fun soundTimer() {
            soundsRepository.soundTimer()
        }
    }
}