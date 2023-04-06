package com.abbas.gamesign.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.abbas.gamesign.databinding.DetailsActivityItemSliderBinding
import com.abbas.gamesign.interfaces.ItemClickListener
import com.abbas.gamesign.model.game.GameScreenshotModel
import com.abbas.gamesign.utils.GlideUtil
import com.smarteist.autoimageslider.SliderViewAdapter

class DetailsActivitySliderAdapter(private val sliderItems: ArrayList<GameScreenshotModel>, private val itemClickListener: ItemClickListener<Int>? = null) : SliderViewAdapter<DetailsActivitySliderAdapter.SliderAdapterVH>() {

    override fun getCount(): Int {
        return sliderItems.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?): DetailsActivitySliderAdapter.SliderAdapterVH {
        val binding = DetailsActivityItemSliderBinding.inflate(LayoutInflater.from(parent!!.context), parent, false)
        return SliderAdapterVH(binding)
    }

    override fun onBindViewHolder(viewHolder: DetailsActivitySliderAdapter.SliderAdapterVH?, position: Int) {
        viewHolder!!.bind(position)
    }

    fun getItem(position: Int): GameScreenshotModel {
        return sliderItems[position]
    }

    inner class SliderAdapterVH(private val binding: DetailsActivityItemSliderBinding) : ViewHolder(binding.root) {

        fun bind(position: Int) {
            GlideUtil.loadWithRetry(binding.imageSlider, sliderItems[position].image)

            binding.root.setOnClickListener {
                itemClickListener?.onClick(position)
            }

        }
    }
}