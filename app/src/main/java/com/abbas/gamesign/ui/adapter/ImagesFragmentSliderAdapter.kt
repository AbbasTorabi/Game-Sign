package com.abbas.gamesign.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.abbas.gamesign.databinding.ImagesFragmentItemSliderBinding
import com.abbas.gamesign.model.game.GameScreenshotModel
import com.abbas.gamesign.utils.GlideUtil
import com.smarteist.autoimageslider.SliderViewAdapter

class ImagesFragmentSliderAdapter(private val sliderItems: ArrayList<GameScreenshotModel>) : SliderViewAdapter<ImagesFragmentSliderAdapter.SliderAdapterVH>() {

    override fun getCount(): Int {
        return sliderItems.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?): ImagesFragmentSliderAdapter.SliderAdapterVH {
        val binding = ImagesFragmentItemSliderBinding.inflate(LayoutInflater.from(parent!!.context), parent, false)
        return SliderAdapterVH(binding)
    }

    override fun onBindViewHolder(viewHolder: ImagesFragmentSliderAdapter.SliderAdapterVH?, position: Int) {
        viewHolder!!.bind(position)
    }

    inner class SliderAdapterVH(private val binding: ImagesFragmentItemSliderBinding) : ViewHolder(binding.root) {

        fun bind(position: Int) {
            GlideUtil.loadWithRetry(binding.imageSlider, sliderItems[position].image)
        }
    }
}