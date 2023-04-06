package com.abbas.gamesign.ui.fragment.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    private var isViewLoaded: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        postponeEnterTransition()

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startPostponedEnterTransition() // this mean after view created the transaction animation from fragment should start
        initView()
        onClickListener()
        observe()
        onReadyView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.isViewLoaded = false
    }

    protected abstract fun initView()
    protected abstract fun observe()
    protected abstract fun onClickListener()

    protected open fun onReadyView() {}

    protected fun getArgument(key: String): Any? {
        val bundle = arguments
        if (bundle != null) {
            return bundle.getInt(key)
        }
        return null
    }


}