package com.abbas.gamesign.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.abbas.gamesign.data.repository.GameRepository
import com.abbas.gamesign.mapper.GameMapper
import com.abbas.gamesign.model.game.GameListModel
import com.abbas.gamesign.ui.viewModel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.mapstruct.factory.Mappers
import javax.inject.Inject

@HiltViewModel
class GamesListViewModel @Inject constructor(private val repository: GameRepository) : BaseViewModel() {

    var numberPage = 1
    private val _gamesList = MutableLiveData<List<GameListModel>>()
    val gamesList: LiveData<List<GameListModel>> get() = _gamesList

    private val gameMapper = Mappers.getMapper(GameMapper::class.java)

    init {
        getGamesList(numberPage)
    }

    fun getGamesList(page: Int) {
        if (page == 1) {
            _loading.value = true
            _gamesList.value = ArrayList()
        }
        viewModelScope.launch {
            repository.getGamesList(
                page, 30, "4,5,6,7,8,9", "android,ios", "-released", "40,100"
            ).let {
                _loading.value = false
                if (it.success) {
                    if (!_gamesList.value.isNullOrEmpty()) {
                        numberPage += 1
                    } else numberPage = 1
                    _gamesList.value = gameMapper.map(it.data!!.results!!)
                } else {
                    if (_gamesList.value != null) numberPage += 1
                }
            }
        }
    }

    fun loadMore() {
        getGamesList(numberPage + 1)
    }

}