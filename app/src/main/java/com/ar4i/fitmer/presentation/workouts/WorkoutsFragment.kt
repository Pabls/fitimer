package com.ar4i.fitmer.presentation.workouts

import com.ar4i.fitmer.R
import com.ar4i.fitmer.presentation.base.BaseFragment

class WorkoutsFragment : BaseFragment<WorkoutsViewModel>() {
    override val layoutId: Int
        get() = R.layout.fragment_workouts
    override val titleId: Int
        get() = R.string.workouts_title
    override val viewModel: Class<WorkoutsViewModel>
        get() = WorkoutsViewModel::class.java

    override fun initObservers() {
    }

    companion object {
        fun newInstance() = WorkoutsFragment()
    }
}