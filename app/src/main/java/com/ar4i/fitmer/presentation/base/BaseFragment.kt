package com.ar4i.fitmer.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ar4i.fitmer.app.App
import com.ar4i.fitmer.presentation.main.MainActivity

abstract class BaseFragment<V : BaseViewModel> : Fragment() {
    abstract val layoutId: Int
    abstract val titleId: Int
    abstract val viewModel: Class<V>
    protected lateinit var vm: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = ViewModelProviders.of(
            this,
            App.applicationComponent.provideViewModelsFactory(arguments)
        ).get(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(layoutId, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setTitle()
        initObservers()
        super.onViewCreated(view, savedInstanceState)
    }

    protected fun showBackButton() = false

    protected inline fun <T> LiveData<Event<T>>.observeEvent(crossinline observer: (T?) -> Unit) =
        this.observe(
            viewLifecycleOwner,
            Observer { t -> t.getContentIfNotHandled()?.apply { observer.invoke(this) } })

    abstract fun initObservers()

    private fun setTitle() {
        activity?.let {
            if (it is MainActivity) it.setToolbarTitle(getString(titleId), showBackButton())
        }
    }
}