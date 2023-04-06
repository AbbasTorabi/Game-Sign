package com.abbas.gamesign.ui.adapter

import android.os.SystemClock
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abbas.gamesign.BR
import com.abbas.gamesign.databinding.ItemGameRecyclerBinding
import com.abbas.gamesign.ui.activity.GameDetailsActivity
import com.abbas.gamesign.ui.viewModel.item.ItemGameViewModel

class GameRecyclerAdapter constructor(private val viewModels: List<ItemGameViewModel>) : RecyclerView.Adapter<GameRecyclerAdapter.GameViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val binding = ItemGameRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: GameViewHolder, i: Int) {
        viewHolder.bind(i)
    }

    override fun getItemCount(): Int {
        return getViewModelList().size
    }

    inner class GameViewHolder(private val binding: ItemGameRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {

        private var previousTime = SystemClock.elapsedRealtime()

        fun bind(position: Int) {
            val viewModel: ItemGameViewModel = getViewModelList()[position]!!
            binding.setVariable(BR.viewmodel, viewModel)
            binding.title.postDelayed({
                binding.title.isSelected = true
            }, 2000)
            binding.root.setOnClickListener {
                val now = SystemClock.elapsedRealtime()
                if (now - previousTime >= binding.itemPosterTransformationLayout.duration) {
                    GameDetailsActivity.startActivity(binding.root.context, binding.itemPosterTransformationLayout, viewModel.model)
                    previousTime = now
                }
            }

            binding.executePendingBindings()
        }
    }

    private fun getViewModelList(): List<ItemGameViewModel?> {
        return viewModels
    }

}
