package com.ar4i.fitmer.presentation.splash.adapter

sealed class ScreenEntity {
    object First: ScreenEntity()
    object Second: ScreenEntity()
    object Third: ScreenEntity()
}