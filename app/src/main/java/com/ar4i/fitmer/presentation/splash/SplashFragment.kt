package com.ar4i.fitmer.presentation.splash

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.ar4i.fitmer.R
import com.ar4i.fitmer.presentation.base.BaseFragment
import com.ar4i.fitmer.presentation.splash.adapter.SplashAdapter
import com.ar4i.fitmer.presentation.views.IndicatorView
import com.ar4i.fitmer.presentation.workouts.WorkoutsFragment

class SplashFragment : BaseFragment<SplashViewModel>() {
    override val layoutId: Int
        get() = R.layout.fragment_splash
    override val titleId: Int
        get() = R.string.common_empty
    override val viewModel: Class<SplashViewModel>
        get() = SplashViewModel::class.java

    private var rvSplash: RecyclerView? = null
    private var vIndicator: IndicatorView? = null
    private var splashAdapter = SplashAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvSplash = view.findViewById(R.id.rvSplash)
        vIndicator = view.findViewById(R.id.vIndicator)
        vIndicator?.setActive(0)
        val snapHelper = PagerSnapHelper()
        rvSplash?.let {
            it.adapter = splashAdapter
            snapHelper.attachToRecyclerView(it)
            val manager = (it.layoutManager as LinearLayoutManager)
            it.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    val position = manager.findFirstCompletelyVisibleItemPosition()
                    if (position >= 0) {
                        vIndicator?.setActive(position)
                    }
                }
            })
        }
    }

    override fun initObservers() {
        vm.pages.observe(viewLifecycleOwner, Observer { splashAdapter.updateAdapter(it) })
    }

    override fun showToolbar() = false
    override fun showBottomBar() = false

    private fun navigateToApp() {
        navigateTo(WorkoutsFragment.newInstance())
    }

    companion object {
        fun newInstance() = SplashFragment()
    }
}