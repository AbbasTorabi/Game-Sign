package com.abbas.gamesign.ui.activity

import android.os.Bundle
import com.abbas.gamesign.R
import com.abbas.gamesign.databinding.ActivityMainBinding
import com.abbas.gamesign.ui.activity.base.BaseBindableActivity
import com.abbas.gamesign.ui.fragment.GamesListFragment
import com.skydoves.transformationlayout.onTransformationStartContainer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseBindableActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationStartContainer()
        super.onCreate(savedInstanceState)
    }

    override fun initView() {
        supportFragmentManager.beginTransaction()
            .setReorderingAllowed(true).replace(R.id.main_fragment_container, GamesListFragment())
            .commit()
    }

    override fun observe() {
    }

    override fun onClickListener() {
    }

}