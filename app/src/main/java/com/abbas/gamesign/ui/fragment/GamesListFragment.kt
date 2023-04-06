package com.abbas.gamesign.ui.fragment

import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import com.abbas.gamesign.R
import com.abbas.gamesign.databinding.FragmentGamesListBinding
import com.abbas.gamesign.ui.adapter.GameRecyclerAdapter
import com.abbas.gamesign.ui.element.PageableRecyclerView
import com.abbas.gamesign.ui.fragment.base.BaseBindableVmFragment
import com.abbas.gamesign.ui.viewModel.GamesListViewModel
import com.abbas.gamesign.ui.viewModel.item.ItemGameViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GamesListFragment : BaseBindableVmFragment<FragmentGamesListBinding, GamesListViewModel>(R.layout.fragment_games_list), PageableRecyclerView.OnScrollChanged {

    override val viewModel: GamesListViewModel by viewModels()

    private var gamesRecyclerView: PageableRecyclerView? = null
    private var gamesItemViewModels: ArrayList<ItemGameViewModel>? = null

    override fun initView() {
        gamesRecyclerView = binding.gamesRecycler
    }

    override fun observe() {

        viewModel.gamesList.observe(this) {
            if (it != null && it.isNotEmpty()) {
                if (viewModel.numberPage == 1) {
                    gamesItemViewModels = ArrayList()
                    gamesRecyclerView?.adapter = null
                }
                for (order in it) {
                    val item = ItemGameViewModel(order)
                    gamesItemViewModels!!.add(item)
                }

                configGamesRecycler()

            }
        }
    }

    override fun onClickListener() {
    }

    private fun configGamesRecycler() {

        gamesRecyclerView!!.apply {
            setOnScrollChanged(this@GamesListFragment)
            recyclerTag = "GAMES"
            tag = "ready"
        }
        val animation: LayoutAnimationController = AnimationUtils.loadLayoutAnimation(activity, R.anim.layout_animation_fall_down)
        if (gamesRecyclerView!!.layoutAnimation == null || !gamesRecyclerView!!.isAnimating) gamesRecyclerView!!.layoutAnimation = animation

        if (gamesRecyclerView!!.adapter == null) {
            gamesRecyclerView!!.apply {
                itemAnimator = DefaultItemAnimator()
                adapter = GameRecyclerAdapter(gamesItemViewModels!!)
            }
        } else {
            gamesRecyclerView!!.apply {
                layoutAnimation = null
                adapter!!.notifyDataSetChanged()
            }
        }
    }

    override fun onLoadMore(tag: String?) {
        viewModel.loadMore()
    }

}