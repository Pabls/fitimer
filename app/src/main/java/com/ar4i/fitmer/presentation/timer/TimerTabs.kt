package com.ar4i.fitmer.presentation.timer

import androidx.annotation.StringRes
import com.ar4i.fitmer.R

enum class TimerTabs(val position: Int, @StringRes val titleId: Int) {
    INTERVALS(0, R.string.timer_tab_intervals),
    TABATA(1, R.string.timer_tab_tabata)
}