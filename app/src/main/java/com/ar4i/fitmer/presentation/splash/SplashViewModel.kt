package com.ar4i.fitmer.presentation.splash

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ar4i.fitmer.R
import com.ar4i.fitmer.domain.ISettingsIteractor
import com.ar4i.fitmer.presentation.base.BaseViewModel
import com.ar4i.fitmer.presentation.base.Event
import com.ar4i.fitmer.presentation.splash.adapter.ScreenEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashViewModel(private val settingsIteractor: ISettingsIteractor, application: Application) :
    BaseViewModel(application) {

    var pages = MutableLiveData<List<ScreenEntity>>()
    var buttonText = MutableLiveData<String>()
    var pagePosition = MutableLiveData<Int>()
    var toFinish = EventLiveData<Unit>()

    private val pageList = arrayListOf<ScreenEntity>()

    private val okBtnText = getString(R.string.splash_ok)
    private val finishBtnText = getString(R.string.splash_finish)

    init {
        checkState()
    }

    fun closeIconClick() {
        saveShownState()
    }

    fun nextButtonClick(pageIndex: Int) {
        if (pageIndex == pageList.size.dec()) {
            closeIconClick()
        } else {
            pagePosition.value = pageIndex.inc()
        }
    }

    fun scroll(pageIndex: Int) {
        buttonText.value = when (pageIndex) {
            pageList.size.dec() -> finishBtnText
            else -> okBtnText
        }
    }

    private fun checkState() {
        viewModelScope.launch {
            val isLaunchScreenViewed = withContext(Dispatchers.IO) {
                settingsIteractor.isLaunchScreenShown()
            }
            when {
                isLaunchScreenViewed -> finishLaunching()
                else -> initScreen()
            }
        }
    }

    private fun initScreen() {
        pageList.add(ScreenEntity.First)
        pageList.add(ScreenEntity.Second)
        pageList.add(ScreenEntity.Third)
        pages.value = pageList
        buttonText.value = okBtnText
    }

    private fun saveShownState() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                settingsIteractor.launchScreenShown()
            }
            finishLaunching()
        }
    }

    private fun finishLaunching() {
        toFinish.value = Event(Unit)
    }
}