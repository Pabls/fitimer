package com.ar4i.fitmer.presentation.graphs

import com.ar4i.fitmer.R
import com.ar4i.fitmer.presentation.base.BaseFragment

class GraphFragment : BaseFragment<GraphsViewModel>() {
    override val layoutId: Int
        get() = R.layout.fragment_graph
    override val titleId: Int
        get() = R.string.graphs_title
    override val viewModel: Class<GraphsViewModel>
        get() = GraphsViewModel::class.java

    override fun initObservers() {
    }

    companion object {
        fun newInstance() = GraphFragment()
    }
}