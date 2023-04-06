package com.abbas.gamesign.ui.activity.base

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.abbas.gamesign.BR
import com.abbas.gamesign.ui.viewModel.base.BaseViewModel
import com.skydoves.transformationlayout.TransformationAppCompatActivity

abstract class TransformBindableActivity<B: ViewDataBinding, V : BaseViewModel>(private val layoutId: Int) : TransformationAppCompatActivity() {

    private var _binding: B? = null
    protected val binding: B get() = _binding!!

    protected abstract val viewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = DataBindingUtil.setContentView(this, layoutId)
        binding.apply {
            lifecycleOwner = this@TransformBindableActivity
        }
        binding.setVariable(BR.viewmodel, viewModel)

        initView()
        observe()
        onClickListener()
    }

    protected abstract fun initView()
    protected abstract fun observe()
    protected abstract fun onClickListener()

}