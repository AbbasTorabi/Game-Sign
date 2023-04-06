package com.abbas.gamesign.ui.fragment.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.abbas.gamesign.BR
import com.abbas.gamesign.ui.viewModel.base.BaseViewModel

abstract class BaseBindableVmFragment<B : ViewDataBinding, V : BaseViewModel>(private val layoutId: Int) : BaseFragment() {

    private var _binding: B? = null
    protected val binding: B get() = _binding!!

    protected abstract val viewModel: V

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.apply {
            lifecycleOwner = this@BaseBindableVmFragment
        }
        binding.setVariable(BR.viewmodel, viewModel)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}