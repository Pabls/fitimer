package com.ar4i.fitmer.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import com.ar4i.fitmer.R

class IndicatorView(context: Context, attributeSet: AttributeSet?) :
    LinearLayout(context, attributeSet) {

    val imgStart: ImageView
    val imgMiddle: ImageView
    val imgEnd: ImageView

    init {
        orientation = HORIZONTAL
        inflate(getContext(), R.layout.view_indicator, this)

        imgStart = findViewById(R.id.imgStart)
        imgMiddle = findViewById(R.id.imgMiddle)
        imgEnd = findViewById(R.id.imgEnd)
    }

    fun setActive(index: Int) {
        val colors = when (index) {
            0 -> Triple(
                R.drawable.background_circle_accent,
                R.drawable.background_circle_gray,
                R.drawable.background_circle_gray
            )
            1 -> Triple(
                R.drawable.background_circle_gray,
                R.drawable.background_circle_accent,
                R.drawable.background_circle_gray
            )
            2 -> Triple(
                R.drawable.background_circle_gray,
                R.drawable.background_circle_gray,
                R.drawable.background_circle_accent
            )
            else -> Triple(
                R.drawable.background_circle_gray,
                R.drawable.background_circle_gray,
                R.drawable.background_circle_gray
            )
        }

        setColors(colors.first, colors.second, colors.third)
    }

    private fun setColors(@DrawableRes startRes: Int, midRes: Int, endRes: Int) {
        imgStart.setBackgroundResource(startRes)
        imgMiddle.setBackgroundResource(midRes)
        imgEnd.setBackgroundResource(endRes)
    }
}