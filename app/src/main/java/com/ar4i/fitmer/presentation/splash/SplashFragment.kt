package com.ar4i.fitmer.presentation.splash

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
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
    private var imgClose: ImageView? = null
    private var btnNext: Button? = null
    private var manager: LinearLayoutManager? = null
    private var splashAdapter = SplashAdapter()
    private val snapHelper = PagerSnapHelper()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvSplash = view.findViewById(R.id.rvSplash)
        vIndicator = view.findViewById(R.id.vIndicator)
        imgClose = view.findViewById(R.id.imgClose)
        btnNext = view.findViewById(R.id.btnNext)

        imgClose?.setOnClickListener { vm.closeIconClick() }
        btnNext?.setOnClickListener { vm.nextButtonClick(getCurrentPageIndex()) }
        setActiveIndicator(0)

        rvSplash?.let {
            it.adapter = splashAdapter
            snapHelper.attachToRecyclerView(it)
            manager = (it.layoutManager as LinearLayoutManager)
            it.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(rv: RecyclerView, ns: Int) {
                    val currentPageIndex = getCurrentPageIndex()
                    if(currentPageIndex >= 0) {
                        setActiveIndicator(currentPageIndex)
                        vm.scroll(currentPageIndex)
                    }
                }
            })
        }
    }

    override fun initObservers() {
        vm.pages.observe(viewLifecycleOwner, Observer { splashAdapter.updateAdapter(it) })
        vm.buttonText.observe(viewLifecycleOwner, Observer { btnNext?.text = it })
        vm.pagePosition.observe(viewLifecycleOwner, Observer {
            rvSplash?.smoothScrollToPosition(it)
        })

        vm.toFinish.observeEvent { navigateToApp() }
    }

    override fun showToolbar() = false

    override fun showBottomBar() = false

    private fun getCurrentPageIndex() = manager?.findFirstCompletelyVisibleItemPosition() ?: 0

    private fun setActiveIndicator(index: Int?) {
        vIndicator?.setActive(index)
    }

    private fun navigateToApp() {
        navigateTo(WorkoutsFragment.newInstance())
    }

    companion object {
        fun newInstance() = SplashFragment()
    }
}