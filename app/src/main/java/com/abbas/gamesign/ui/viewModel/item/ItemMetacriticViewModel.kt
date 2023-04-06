package com.abbas.gamesign.ui.viewModel.item

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import com.abbas.gamesign.R
import com.abbas.gamesign.model.metacritic.MetacriticModel

class ItemMetacriticViewModel constructor(val model: MetacriticModel) : ViewModel() {

    var icon = ObservableInt()

    init {
        val platformName = model.platform!!.name
        when {
            platformName!!.contains("PlayStation") -> icon.set(R.drawable.playstation)
            platformName.contains("Nintendo") -> icon.set(R.drawable.nintendo)
            platformName.contains("PC") -> icon.set(R.drawable.windows)
            platformName.contains("Xbox") -> icon.set(R.drawable.xbox)
        }
    }

    companion object {

        @JvmStatic
        @BindingAdapter("setMetacriticBackground")
        fun View.setMetacriticBackground(metaScore: Int) {

            when (metaScore) {
                in 0..49 -> {
                    this.setBackgroundResource(R.drawable.metacritic_red_gradient)
                }
                in 50..74 -> {
                    this.setBackgroundResource(R.drawable.metacritic_orange_gradient)
                }
                in 75..100 -> {
                    this.setBackgroundResource(R.drawable.metacritic_green_gradient)
                }
            }

        }

    }

}