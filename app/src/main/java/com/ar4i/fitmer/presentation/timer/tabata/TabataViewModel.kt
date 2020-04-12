package com.ar4i.fitmer.presentation.timer.tabata

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ar4i.fitmer.domain.ISoundsInteractor
import com.ar4i.fitmer.presentation.base.BaseViewModel
import com.ar4i.fitmer.utils.secondsToTimeString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TabataViewModel(private var soundsInteractor: ISoundsInteractor, application: Application) :
    BaseViewModel(application) {

    val viewState = MutableLiveData<ViewState>()
    val titleState = MutableLiveData<String>()
    private var currentState: ViewState? = null

    init {
        currentState = ViewState(
            preparation = 10,
            work = 20,
            rest = 10,
            rounds = 8,
            cycles = 1
        )
        viewState.value = currentState
        getFullTime()
    }

    fun setPreparation(value: Int) {
        currentState = currentState?.copy(preparation = value)
        getFullTime()
    }

    fun setWork(value: Int) {
        currentState = currentState?.copy(work = value)
        getFullTime()
    }

    fun setRest(value: Int) {
        currentState = currentState?.copy(rest = value)
        getFullTime()
    }

    fun setRounds(value: Int) {
        currentState = currentState?.copy(rounds = value)
        getFullTime()
    }

    fun setCycles(value: Int) {
        currentState = currentState?.copy(cycles = value)
        getFullTime()
    }

    fun start() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                soundsInteractor.soundTimer()
            }
        }
    }

    private fun getFullTime() {
        titleState.value = getContext().secondsToTimeString(getFullTimeInSeconds())
    }

    private fun getFullTimeInSeconds() =
        currentState?.let { it.preparation + ((it.work + it.rest) * it.rounds * it.cycles) } ?: 0

    data class ViewState(
        val preparation: Int,
        val work: Int,
        val rest: Int,
        val rounds: Int,
        val cycles: Int
    )
}