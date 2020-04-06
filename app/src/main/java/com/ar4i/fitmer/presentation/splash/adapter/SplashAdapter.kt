package com.ar4i.fitmer.presentation.splash.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ar4i.fitmer.R
import com.ar4i.fitmer.presentation.base.StubViewHolder

class SplashAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = listOf<ScreenEntity>()

    fun updateAdapter(items: List<ScreenEntity>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_FIRST -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_splash_first, parent, false)
                Holder.First(view)
            }
            TYPE_SECOND -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_splash_second, parent, false)
                Holder.Second(view)
            }
            TYPE_THIRD -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_splash_third, parent, false)
                Holder.Third(view)
            }
            else -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_stub, parent, false)
                StubViewHolder(view)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }

    override fun getItemViewType(position: Int) = when (items[position]) {
        is ScreenEntity.First -> TYPE_FIRST
        is ScreenEntity.Second -> TYPE_SECOND
        is ScreenEntity.Third -> TYPE_THIRD
    }

    sealed class Holder(view: View) : RecyclerView.ViewHolder(view) {
        class First(view: View) : Holder(view)
        class Second(view: View) : Holder(view)
        class Third(view: View) : Holder(view)
    }

    companion object {
        const val TYPE_FIRST = 666
        const val TYPE_SECOND = 667
        const val TYPE_THIRD = 668
    }
}