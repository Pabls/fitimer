package com.ar4i.fitmer.utils

import android.content.Context
import kotlin.math.ceil

fun Context.dpToPixSize(value: Float): Int {
    return ceil(value * resources.displayMetrics.density).toInt()
}