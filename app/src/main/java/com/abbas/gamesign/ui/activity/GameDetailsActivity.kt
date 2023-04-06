package com.abbas.gamesign.ui.activity

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.text.Html
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.LinearLayoutManager
import com.abbas.gamesign.R
import com.abbas.gamesign.databinding.ActivityGameDetailsBinding
import com.abbas.gamesign.interfaces.ItemClickListener
import com.abbas.gamesign.model.game.GameListModel
import com.abbas.gamesign.model.game.GameScreenshotModel
import com.abbas.gamesign.ui.activity.base.TransformBindableActivity
import com.abbas.gamesign.ui.adapter.DetailsActivitySliderAdapter
import com.abbas.gamesign.ui.adapter.SingleViewBindableAdapter
import com.abbas.gamesign.ui.element.BlurTransformation
import com.abbas.gamesign.ui.fragment.GameImagesFragment
import com.abbas.gamesign.ui.viewModel.GameDetailsViewModel
import com.abbas.gamesign.ui.viewModel.item.ItemMetacriticViewModel
import com.abbas.gamesign.utils.FragmentTag
import com.abbas.gamesign.utils.GlideUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.skydoves.transformationlayout.TransformationCompat
import com.skydoves.transformationlayout.TransformationLayout
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GameDetailsActivity : TransformBindableActivity<ActivityGameDetailsBinding, GameDetailsViewModel>(R.layout.activity_game_details), ItemClickListener<Int> {

    override val viewModel: GameDetailsViewModel by viewModels()

    private var backgroundImage: String? = null
    private var metacriticViewModels = ArrayList<ItemMetacriticViewModel>(ArrayList())

    companion object {

        const val posterExtraName = "posterExtraName"

        fun startActivity(context: Context, transformationLayout: TransformationLayout, poster: GameListModel) {
            val intent = Intent(context, GameDetailsActivity::class.java)
            intent.putExtra(posterExtraName, poster)
            TransformationCompat.startActivity(transformationLayout, intent)
        }

    }

    override fun initView() {
        intent.getParcelableExtra<GameListModel>(posterExtraName)?.let {

            backgroundImage = it.backgroundImage

            // Add default screenshot to list
            viewModel.addScreenshot(
                GameScreenshotModel().apply {
                    id = 0
                    image = backgroundImage
                }
            )

            Glide.with(this)
                .load(backgroundImage)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .override(1920, 1080) // Some pictures are 4k resolution(3840 * 2160) or more and not work perfect with blur so resize needed here
                .apply(bitmapTransform(BlurTransformation(this)))
                .into(binding.blurBackground)

            GlideUtil.loadWithRetry(binding.primaryImage, backgroundImage)

            binding.gameTitle.text = it.name
            viewModel.slug = it.slug!!
            viewModel.getGameDetails()
            viewModel.getGameScreenshots()

        }
    }

    private fun setBlurBackground(backgroundImage: String) {

        Glide.with(this)
            .asBitmap()
            .load(backgroundImage)
            .override(1920, 1080)
            .transform(BlurTransformation(this))
            .into(object : CustomTarget<Bitmap>() {
                override fun onLoadFailed(errorDrawable: Drawable?) {
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                }

                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    // Get the new Drawable and create the TransitionDrawable
                    val layers = arrayOfNulls<Drawable>(2)
                    layers[0] = binding.blurBackground.drawable
                    layers[1] = BitmapDrawable(resources, resource)

                    val transitionDrawable = TransitionDrawable(layers)

                    // Set the TransitionDrawable as the background of the ImageView
                    binding.blurBackground.setImageDrawable(transitionDrawable)

                    val palette = Palette.from(resource).generate()
                    val dominantColor = palette.getDominantColor(ContextCompat.getColor(this@GameDetailsActivity, R.color.metacriticRedColor))

                    // Set the dominant color as the background color of the ImageView
                    // Note: If you don't do this when changing blurBackground you get a flashback
                    binding.blurBackground.setBackgroundColor(dominantColor)

                    // Enable crossFading between drawables
                    transitionDrawable.isCrossFadeEnabled = true
                    transitionDrawable.startTransition(500)
                }
            })


    }

    override fun observe() {
        viewModel.screenshots.observe(this) {
            if (!it.isNullOrEmpty()) initImagesSlider(it)
        }
        viewModel.gameModel.observe(this) {
            if (it != null) {
                binding.description.text = Html.fromHtml(it.description, Html.FROM_HTML_MODE_COMPACT)

                for (metacriticPlatform in viewModel.gameModel.value!!.metacriticPlatforms!!) {
                    val viewModel = ItemMetacriticViewModel(metacriticPlatform)
                    metacriticViewModels.add(viewModel)
                }
                initMetacriticRecycler()

            }
        }
    }

    override fun onClickListener() {
        binding.back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initImagesSlider(images: ArrayList<GameScreenshotModel>) {

        val sliderView = binding.imageSlider

        sliderView.animate().alpha(1f).duration = 800
        val adapter = DetailsActivitySliderAdapter(images, this)

        sliderView.setSliderAdapter(adapter)
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM) // indicator animator style
        sliderView.setCurrentPageListener {
            setBlurBackground(adapter.getItem(it).image!!)
        }

    }

    private fun initMetacriticRecycler() {
        binding.metacriticRecycler.apply {
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = SingleViewBindableAdapter(metacriticViewModels, R.layout.item_metacritic_recycler)
        }
    }

    override fun <T> onClick(model: T) {
        if (model is Int) {
            // Put CurrentItemPosition in argument
            val targetFragment = GameImagesFragment()
            val bundle = Bundle()
            bundle.putInt("currentPosition", model)
            targetFragment.arguments = bundle

            // Open fragment and allow to back stack
            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.game_images_fragment_container, targetFragment)
                .addToBackStack(FragmentTag.GAMES_SCREENSHOTS).commit()
        }
    }


}
