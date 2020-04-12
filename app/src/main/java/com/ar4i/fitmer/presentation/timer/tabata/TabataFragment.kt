package com.ar4i.fitmer.presentation.timer.tabata

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import com.ar4i.fitmer.R
import com.ar4i.fitmer.presentation.base.BaseFragment
import com.ar4i.fitmer.presentation.views.TimeSelector
import com.ar4i.fitmer.presentation.views.TimeSelector.Type.Counter
import com.ar4i.fitmer.presentation.views.TimeSelector.Type.Timer

class TabataFragment : BaseFragment<TabataViewModel>() {
    override val layoutId: Int
        get() = R.layout.fragment_tabata
    override val titleId: Int
        get() = R.string.common_empty
    override val viewModel: Class<TabataViewModel>
        get() = TabataViewModel::class.java

    private var tvFullTime: TextView? = null
    private var tsPreparation: TimeSelector? = null
    private var tsWork: TimeSelector? = null
    private var tsRest: TimeSelector? = null
    private var tsWorkRounds: TimeSelector? = null
    private var tsСycles: TimeSelector? = null
    private var btnStart: Button? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvFullTime = view.findViewById(R.id.tvFullTime)
        tsPreparation = view.findViewById(R.id.tsPreparation)
        tsWork = view.findViewById(R.id.tsWork)
        tsRest = view.findViewById(R.id.tsRest)
        tsWorkRounds = view.findViewById(R.id.tsWorkRounds)
        tsСycles = view.findViewById(R.id.tsСycles)
        btnStart = view.findViewById(R.id.btnStart)

        tsPreparation?.timerChangeListener = { vm.setPreparation(it) }
        tsWork?.timerChangeListener = { vm.setWork(it) }
        tsRest?.timerChangeListener = { vm.setRest(it) }
        tsWorkRounds?.counterChangeListener = { vm.setRounds(it) }
        tsСycles?.counterChangeListener = { vm.setCycles(it) }
        btnStart?.setOnClickListener { vm.start() }
    }

    override fun showToolbarShadow() = false

    override fun initObservers() {
        vm.viewState.observe(viewLifecycleOwner, Observer { initUi(it) })
        vm.titleState.observe(viewLifecycleOwner, Observer { tvFullTime?.text = it })
    }

    private fun initUi(state: TabataViewModel.ViewState) {
        tsPreparation?.setInitialValue(state.preparation, Timer)
        tsWork?.setInitialValue(state.work, Timer)
        tsRest?.setInitialValue(state.rest, Timer)
        tsWorkRounds?.setInitialValue(state.rounds, Counter)
        tsСycles?.setInitialValue(state.cycles, Counter)
    }

    companion object {
        fun newInstance() = TabataFragment()
    }
}