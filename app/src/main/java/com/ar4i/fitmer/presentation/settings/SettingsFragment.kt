package com.ar4i.fitmer.presentation.settings

import com.ar4i.fitmer.R
import com.ar4i.fitmer.presentation.base.BaseFragment

class SettingsFragment : BaseFragment<SettingsViewModel>() {
    override val layoutId: Int
        get() = R.layout.fragment_settings
    override val titleId: Int
        get() = R.string.settings_title
    override val viewModel: Class<SettingsViewModel>
        get() = SettingsViewModel::class.java

    override fun initObservers() {
    }

    companion object {
        fun newInstance() = SettingsFragment()
    }
}