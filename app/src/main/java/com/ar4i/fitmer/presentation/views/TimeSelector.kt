package com.ar4i.fitmer.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.ar4i.fitmer.R
import com.ar4i.fitmer.utils.secondsToTimeString

class TimeSelector : RelativeLayout {

    var counterChangeListener: ((count: Int) -> Unit)? = null
    var timerChangeListener: ((time: Int) -> Unit)? = null

    private var tvTitle: TextView
    private var tvTime: TextView
    private var imgMinus: ImageView
    private var imgPlus: ImageView
    private var currentType = Type.Counter
    private var currentValue = 0

    private val DEFAULT_ATTRS_VALUE = 0
    private val TIME_DIFF = 5

    init {
        LinearLayout.inflate(context, R.layout.view_time_selector, this)
        tvTitle = findViewById(R.id.tvTitle)
        tvTime = findViewById(R.id.tvTime)
        imgMinus = findViewById(R.id.imgMinus)
        imgPlus = findViewById(R.id.imgPlus)

        imgMinus.setOnClickListener { changeValue(false) }
        imgPlus.setOnClickListener { changeValue(true) }
    }

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        attributeSet?.let {
            context.theme.obtainStyledAttributes(
                it,
                R.styleable.TimeSelector,
                DEFAULT_ATTRS_VALUE,
                DEFAULT_ATTRS_VALUE
            )?.let { array ->
                try {
                    tvTitle.text = array.getString(R.styleable.TimeSelector_ts_title)
                } finally {
                    array.recycle()
                }
            }
        }
    }

    fun setInitialValue(value: Int, type: Type) {
        currentType = type
        changeCurrentValue(value)
    }

    private fun changeValue(isPlus: Boolean) {
        val newValue = if (currentType == Type.Counter) {
            when {
                isPlus -> currentValue.inc()
                currentValue > 1 -> currentValue.dec()
                else -> currentValue
            }
        } else {
            if (isPlus) currentValue + TIME_DIFF else currentValue - TIME_DIFF
        }
        changeCurrentValue(newValue)
    }

    private fun changeCurrentValue(value: Int) {
        currentValue = if (value >= DEFAULT_ATTRS_VALUE) value else DEFAULT_ATTRS_VALUE
        tvTime.text = if (currentType == Type.Counter) {
            counterChangeListener?.invoke(currentValue)
            currentValue.toString()
        } else {
            timerChangeListener?.invoke(currentValue)
            context.secondsToTimeString(currentValue)
        }
    }

    enum class Type {
        Timer, Counter
    }
}