package com.ar4i.fitmer.presentation.timer.tabata

import com.ar4i.fitmer.R
import com.ar4i.fitmer.presentation.base.BaseFragment

class TabataFragment: BaseFragment<TabataViewModel>() {
    override val layoutId: Int
        get() = R.layout.fragment_tabata
    override val titleId: Int
        get() = R.string.common_empty
    override val viewModel: Class<TabataViewModel>
        get() = TabataViewModel::class.java

    override fun initObservers() {
    }

    override fun showToolbarShadow() = false

    companion object {
        fun newInstance() = TabataFragment()
    }
}