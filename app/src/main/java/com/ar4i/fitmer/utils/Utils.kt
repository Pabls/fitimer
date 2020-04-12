package com.ar4i.fitmer.utils

import android.content.Context
import android.view.View
import com.ar4i.fitmer.R
import kotlin.math.ceil

fun Context.dpToPixSize(value: Float): Int {
    return ceil(value * resources.displayMetrics.density).toInt()
}

fun View.showOrGone(show: Boolean?) {
    visibility = if (show != null && show) View.VISIBLE else View.GONE
}

fun Context.secondsToTimeString(seconds: Int): String {
    fun getHalfTime(value: Int) = if (value < 10) {
        String.format(getString(R.string.time_selector_half_time), value)
    } else {
        value.toString()
    }

    val sec = seconds % 60
    val min = (seconds / 60) % 60
    val hours = (seconds / (60 * 60)) % 60
    return if (hours > 0) {
        String.format(
            getString(R.string.time_selector_full_time),
            getHalfTime(hours),
            getHalfTime(min),
            getHalfTime(sec)
        )
    } else {
        String.format(
            getString(R.string.time_selector_full_minutes_time),
            getHalfTime(min),
            getHalfTime(sec)
        )
    }
}