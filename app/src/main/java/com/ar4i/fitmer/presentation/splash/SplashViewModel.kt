package com.ar4i.fitmer.presentation.splash

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.ar4i.fitmer.R
import com.ar4i.fitmer.presentation.base.BaseViewModel
import com.ar4i.fitmer.presentation.base.Event
import com.ar4i.fitmer.presentation.splash.adapter.ScreenEntity

class SplashViewModel(application: Application) : BaseViewModel(application) {

    var pages = MutableLiveData<List<ScreenEntity>>()
    var buttonText = MutableLiveData<String>()
    var pagePosition = MutableLiveData<Int>()
    var toFinish = EventLiveData<Unit>()

    private val pageList = arrayListOf<ScreenEntity>()

    private val okBtnText = getString(R.string.splash_ok)
    private val finishBtnText = getString(R.string.splash_finish)

    init {
        pageList.add(ScreenEntity.First)
        pageList.add(ScreenEntity.Second)
        pageList.add(ScreenEntity.Third)
        pages.value = pageList
        buttonText.value = okBtnText
    }

    fun closeIconClick() {
        toFinish.value = Event(Unit)
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
}