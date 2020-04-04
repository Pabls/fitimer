package com.ar4i.fitmer.presentation.timer

import com.ar4i.fitmer.R
import com.ar4i.fitmer.presentation.base.BaseFragment

class TimerFragment : BaseFragment<TimerViewModel>() {
    override val layoutId: Int
        get() = R.layout.fragment_timer
    override val titleId: Int
        get() = R.string.timer_title
    override val viewModel: Class<TimerViewModel>
        get() = TimerViewModel::class.java

    override fun initObservers() {
    }

    companion object {
        fun newInstance() = TimerFragment()
    }
}