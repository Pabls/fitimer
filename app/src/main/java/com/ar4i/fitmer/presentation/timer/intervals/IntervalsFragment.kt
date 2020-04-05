package com.ar4i.fitmer.presentation.timer.intervals

import com.ar4i.fitmer.R
import com.ar4i.fitmer.presentation.base.BaseFragment

class IntervalsFragment : BaseFragment<IntervalsViewModel>() {
    override val layoutId: Int
        get() = R.layout.fragment_intervals
    override val titleId: Int
        get() = R.string.common_empty
    override val viewModel: Class<IntervalsViewModel>
        get() = IntervalsViewModel::class.java

    override fun showToolbarShadow() = false

    override fun initObservers() {
    }

    companion object {
        fun newInstance() = IntervalsFragment()
    }
}