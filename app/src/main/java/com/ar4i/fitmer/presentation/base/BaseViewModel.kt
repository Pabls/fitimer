package com.ar4i.fitmer.presentation.base

import android.app.Application
import androidx.annotation.StringRes
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Job

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
    protected fun <T> EventLiveData() = MutableLiveData<Event<T>>()

    protected fun getString(@StringRes stringResId: Int): String {
        return getApplication<Application>().resources.getString(stringResId)
    }
}