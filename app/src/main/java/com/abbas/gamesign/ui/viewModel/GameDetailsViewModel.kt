package com.abbas.gamesign.ui.viewModel

import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.abbas.gamesign.data.repository.GameRepository
import com.abbas.gamesign.enums.GameDetailsAction
import com.abbas.gamesign.mapper.GameMapper
import com.abbas.gamesign.model.game.GameDetailsModel
import com.abbas.gamesign.model.game.GameScreenshotModel
import com.abbas.gamesign.ui.viewModel.base.BaseViewModel
import com.abbas.gamesign.utils.Utility
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.mapstruct.factory.Mappers
import java.text.SimpleDateFormat
import javax.inject.Inject

@HiltViewModel
class GameDetailsViewModel @Inject constructor(private val repository: GameRepository) : BaseViewModel() {

    var slug = ""
    private val gameMapper = Mappers.getMapper(GameMapper::class.java)

    private val _gameModel = MutableLiveData<GameDetailsModel>()
    val gameModel: LiveData<GameDetailsModel> get() = _gameModel

    private val _screenshots = MutableLiveData<ArrayList<GameScreenshotModel>>(ArrayList())
    val screenshots: LiveData<ArrayList<GameScreenshotModel>> get() = _screenshots

    private val _developers = MutableLiveData<String>()
    val developers: LiveData<String> get() = _developers

    private val _releasedDate = MutableLiveData<String>()
    val releasedDate: LiveData<String> get() = _releasedDate

    private val _currentAction = MutableLiveData(GameDetailsAction.DESCRIPTION)
    val currentAction: LiveData<GameDetailsAction> get() = _currentAction

    fun getGameDetails() {
        _loading.value = true
        viewModelScope.launch {
            repository.getGameDetails(slug).let {
                if (it.success && it.data != null) {
                    _gameModel.value = gameMapper.map(it.data)
                    initDevelopersTitle()
                    initReleasedDate()
                }
                _loading.value = false
            }
        }
    }

    fun getGameScreenshots() {
        viewModelScope.launch {
            repository.getGameScreenshots(slug).let {
                if (it.success && it.data != null) {
                    _screenshots.value!!.addAll(gameMapper.mapScreenshots(it.data.results!!))
                    _screenshots.value = _screenshots.value // To notify(or you can create extension)
                    delay(1000)
                }
            }
        }
    }

    fun addScreenshot(screenshotModel: GameScreenshotModel) {
        _screenshots.value!!.add(screenshotModel)
    }

    private fun initDevelopersTitle() {
        var developersTitle = ""
        for (developer in gameModel.value!!.developers!!) {
            developersTitle += developer.name + ", "
        }
        _developers.value = developersTitle.substring(0, developersTitle.length - 2)
    }

    private fun initReleasedDate() {
        val formatter = SimpleDateFormat("MMMM dd, yyyy")
        try {
            _releasedDate.value = formatter.format(gameModel.value!!.released!!)
        } catch (_: Exception) {
            _releasedDate.value = "unknown"
        }
    }

    fun descriptionClicked() {
        _currentAction.value = GameDetailsAction.DESCRIPTION
    }

    fun metacriticClicked() {
        _currentAction.value = GameDetailsAction.METACRITIC
    }

    companion object {

        @JvmStatic
        @BindingAdapter("sliderHeight")
        fun View.setLayoutHeight(height: Int?) {
            val mLayoutParams: ViewGroup.LayoutParams = layoutParams
            val displayMetrics: DisplayMetrics = context.resources.displayMetrics
            val screenWidth = displayMetrics.widthPixels / displayMetrics.density
            if (height != null) mLayoutParams.height = Utility.dpToPx(height)
            else mLayoutParams.height = Utility.dpToPx(screenWidth.toInt() + 70)
            layoutParams = mLayoutParams
        }

        @JvmStatic
        @BindingAdapter("gradientMargin")
        fun View.setGradientMargin(margin: Int?) {
            val mLayoutParams: ViewGroup.MarginLayoutParams = layoutParams as ViewGroup.MarginLayoutParams
            val displayMetrics: DisplayMetrics = context.resources.displayMetrics
            val screenWidth = displayMetrics.widthPixels / displayMetrics.density
            if (margin != null) mLayoutParams.setMargins(0, Utility.dpToPx(height), 0, 0)
            else mLayoutParams.setMargins(0, Utility.dpToPx(screenWidth.toInt() - 160), 0, 0)
            layoutParams = mLayoutParams
        }

    }

}