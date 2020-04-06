package com.ar4i.fitmer.presentation.splash

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.ar4i.fitmer.presentation.base.BaseViewModel
import com.ar4i.fitmer.presentation.splash.adapter.ScreenEntity

class SplashViewModel(application: Application) : BaseViewModel(application) {
    var pages = MutableLiveData<List<ScreenEntity>>()

    init {
        val pageList = arrayListOf<ScreenEntity>()
        pageList.add(ScreenEntity.First)
        pageList.add(ScreenEntity.Second)
        pageList.add(ScreenEntity.Third)
        pages.value = pageList
    }
}