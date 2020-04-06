package com.ar4i.fitmer.utils

import android.content.Context
import android.view.View
import kotlin.math.ceil

fun Context.dpToPixSize(value: Float): Int {
    return ceil(value * resources.displayMetrics.density).toInt()
}

fun View.showOrGone(show: Boolean?) {
    visibility = if (show != null && show) View.VISIBLE else View.GONE
}