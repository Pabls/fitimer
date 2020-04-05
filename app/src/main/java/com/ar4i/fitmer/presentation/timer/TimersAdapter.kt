package com.ar4i.fitmer.presentation.timer

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ar4i.fitmer.presentation.timer.intervals.IntervalsFragment
import com.ar4i.fitmer.presentation.timer.tabata.TabataFragment

class TimersAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = TimerTabs.values().size

    override fun createFragment(position: Int) = when (position) {
        0 -> IntervalsFragment.newInstance()
        else -> TabataFragment.newInstance()
    }
}