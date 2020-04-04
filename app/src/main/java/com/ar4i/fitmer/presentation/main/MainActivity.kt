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
import com.ar4i.fitmer.R
import com.ar4i.fitmer.presentation.graphs.GraphFragment
import com.ar4i.fitmer.presentation.timer.TimerFragment
import com.ar4i.fitmer.presentation.workouts.WorkoutsFragment
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
            setFragmentFromWithLeftAnimation(WorkoutsFragment.newInstance())
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

    private fun initView() {
        toolbar = findViewById(R.id.toolbar)
        tvTitle = findViewById(R.id.tvTitle)
        flContainer = findViewById(R.id.flContainer)
        navMenu = findViewById(R.id.navMenu)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.common_empty)

        navMenu?.setOnNavigationItemSelectedListener { it ->
            when (it.itemId) {
                R.id.actionToMyWorkout -> setFragmentFromWithLeftAnimation(WorkoutsFragment.newInstance())
                R.id.actionToGraph -> setFragmentFromWithLeftAnimation(GraphFragment.newInstance())
                R.id.actionToTimer -> setFragmentFromWithLeftAnimation(TimerFragment.newInstance())
            }
            true
        }
    }

    private fun setFragmentFromWithLeftAnimation(fragment: Fragment?) {
        if (fragment != null) {
            supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left)
                .replace(R.id.flContainer, fragment)
                .commit()
        }
    }

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
