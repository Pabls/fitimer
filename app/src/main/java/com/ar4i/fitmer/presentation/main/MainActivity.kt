package com.ar4i.fitmer.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.animation.AnimationUtils.loadAnimation
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.ar4i.fitmer.R
import com.ar4i.fitmer.presentation.graphs.GraphFragment
import com.ar4i.fitmer.presentation.settings.SettingsFragment
import com.ar4i.fitmer.presentation.splash.SplashFragment
import com.ar4i.fitmer.presentation.timer.TimerFragment
import com.ar4i.fitmer.presentation.workouts.WorkoutsFragment
import com.ar4i.fitmer.utils.dpToPixSize
import com.ar4i.fitmer.utils.showOrGone
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private var toolbar: Toolbar? = null
    private var tvTitle: TextView? = null
    private var flContainer: FrameLayout? = null
    private var navMenu: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        setDarkMode(isDark = true)
        if (savedInstanceState == null) {
            setFragmentWithLeftAnimation(SplashFragment.newInstance())
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    fun setToolbarTitle(title: String, showBackButton: Boolean = false) {
        val anim = loadAnimation(applicationContext, R.anim.fade_in)
        if (anim != null) {
            tvTitle?.animation = anim
        }
        tvTitle?.text = title
        supportActionBar?.setDisplayHomeAsUpEnabled(showBackButton)
        supportActionBar?.setHomeButtonEnabled(showBackButton)
    }

    fun navigateTo(fragment: Fragment?, addToBackStack: Boolean = false) {
        fragment?.let {
            commitTransaction(
                when (addToBackStack) {
                    true -> getFragmentManagerTransaction().addToBackStack(null)
                    false -> getFragmentManagerTransaction()
                }, it
            )
        }
    }

    fun showToolbarShadow(show: Boolean) {
        supportActionBar?.elevation = applicationContext.dpToPixSize(if (show) 4f else 0f).toFloat()
    }

    fun showToolbar(show: Boolean) {
        toolbar?.showOrGone(show)
    }

    fun showBottomBar(show: Boolean) {
        navMenu?.showOrGone(show)
    }

    private fun initView() {
        toolbar = findViewById(R.id.toolbar)
        tvTitle = findViewById(R.id.tvTitle)
        flContainer = findViewById(R.id.flContainer)
        navMenu = findViewById(R.id.navMenu)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.common_empty)

        navMenu?.setOnNavigationItemSelectedListener { it ->
            when (it.itemId) {
                R.id.actionToMyWorkout -> setFragmentWithLeftAnimation(WorkoutsFragment.newInstance())
                R.id.actionToGraph -> setFragmentWithLeftAnimation(GraphFragment.newInstance())
                R.id.actionToTimer -> setFragmentWithLeftAnimation(TimerFragment.newInstance())
                R.id.actionToSettings -> setFragmentWithLeftAnimation(SettingsFragment.newInstance())
            }
            true
        }
    }

    private fun setFragmentWithLeftAnimation(fragment: Fragment?) {
        fragment?.let { commitTransaction(getFragmentManagerTransaction(), it) }
    }

    private fun getFragmentManagerTransaction() = supportFragmentManager
        .beginTransaction()

    private fun commitTransaction(transaction: FragmentTransaction, fragment: Fragment) =
        transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left)
            .replace(R.id.flContainer, fragment)
            .commit()

    private fun setDarkMode(isDark: Boolean) {
        val isCurrentModeDark = delegate.localNightMode == AppCompatDelegate.MODE_NIGHT_YES
        if (isDark) {
            if (!isCurrentModeDark) {
                delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_YES
            }
        } else {
            if (isCurrentModeDark) {
                delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO
            }
        }
    }
}
