package com.abbas.gamesign.ui.viewModel.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    protected val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

}