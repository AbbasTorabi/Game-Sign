package com.abbas.gamesign.ui.fragment

import androidx.fragment.app.activityViewModels
import com.abbas.gamesign.R
import com.abbas.gamesign.databinding.FragmentGameImagesBinding
import com.abbas.gamesign.ui.adapter.ImagesFragmentSliderAdapter
import com.abbas.gamesign.ui.fragment.base.BaseBindableFragment
import com.abbas.gamesign.ui.viewModel.GameDetailsViewModel
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GameImagesFragment : BaseBindableFragment<FragmentGameImagesBinding>(R.layout.fragment_game_images) {

    private val detailsViewModel: GameDetailsViewModel by activityViewModels()

    override fun initView() {
        val sliderView = binding.imageSlider

        val adapter = ImagesFragmentSliderAdapter(detailsViewModel.screenshots.value!!)

        val bundle = this.arguments
        var currentPosition = 0
        if (bundle != null) {
            currentPosition = bundle.getInt("currentPosition", 0)
        }

        sliderView.setSliderAdapter(adapter)
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM) // indicator animator style
        sliderView.currentPagePosition = currentPosition
    }

    override fun observe() {
    }

    override fun onClickListener() {
        binding.back.setOnClickListener {
            activity!!.supportFragmentManager.popBackStack()
        }
    }

}
