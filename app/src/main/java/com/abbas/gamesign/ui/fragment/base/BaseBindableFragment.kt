package com.abbas.gamesign.ui.fragment.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseBindableFragment<B : ViewDataBinding>(private val layoutId: Int) : BaseFragment() {

    private var _binding: B? = null
    protected val binding: B get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.apply {
            lifecycleOwner = this@BaseBindableFragment
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}