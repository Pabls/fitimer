package com.ar4i.fitmer.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ar4i.fitmer.R
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
        getMainActivity()?.let{
            it.showToolbarShadow(show = showToolbarShadow())
            it.showToolbar(show = showToolbar())
            it.showBottomBar(show = showBottomBar())
        }
        setTitle()
        initObservers()
        super.onViewCreated(view, savedInstanceState)
    }

    protected open fun showBackButton() = false
    protected open fun showToolbarShadow() = true
    protected open fun showToolbar() = true
    protected open fun showBottomBar() = true

    protected fun navigateTo(fragment: Fragment, addToBackStack: Boolean = false) {
        getMainActivity()?.let {
            it.navigateTo(fragment, addToBackStack)
        }
    }

    protected inline fun <T> LiveData<Event<T>>.observeEvent(crossinline observer: (T?) -> Unit) =
        this.observe(
            viewLifecycleOwner,
            Observer { t -> t.getContentIfNotHandled()?.apply { observer.invoke(this) } })

    abstract fun initObservers()

    private fun setTitle() {
        if (titleId != R.string.common_empty) {
            getMainActivity()?.setToolbarTitle(getString(titleId), showBackButton())
        }
    }

    private fun getMainActivity() = activity?.let { it as MainActivity }
}