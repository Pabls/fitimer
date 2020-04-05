package com.ar4i.fitmer.presentation.timer

import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.ar4i.fitmer.R
import com.ar4i.fitmer.presentation.base.BaseFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class TimerFragment : BaseFragment<TimerViewModel>() {
    override val layoutId: Int
        get() = R.layout.fragment_timer
    override val titleId: Int
        get() = R.string.timer_title
    override val viewModel: Class<TimerViewModel>
        get() = TimerViewModel::class.java

    private var vpTimers: ViewPager2? = null
    private var tabLayoutMediator: TabLayoutMediator? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vpTimers = view.findViewById(R.id.vpTimers)
        val tlTimers = view.findViewById<TabLayout>(R.id.tlTimers)

        vpTimers?.let {
            tabLayoutMediator = TabLayoutMediator(tlTimers, it) { tab, position ->
                tab.text = getString(TimerTabs.values()[position].titleId)
            }
            it.adapter = TimersAdapter(this)
            tabLayoutMediator?.attach()
        }
    }

    override fun showToolbarShadow() = false

    override fun initObservers() {
    }

    companion object {
        fun newInstance() = TimerFragment()
    }
}