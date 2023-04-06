package com.abbas.gamesign.ui.activity.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseBindableActivity<B : ViewDataBinding>(private val layoutId: Int) : AppCompatActivity() {

    private var _binding: B? = null
    protected val binding: B get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this

        initView()
        observe()
        onClickListener()
    }

    protected abstract fun initView()
    protected abstract fun observe()
    protected abstract fun onClickListener()

}