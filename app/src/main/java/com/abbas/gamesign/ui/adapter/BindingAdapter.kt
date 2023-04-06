package com.abbas.gamesign.ui.adapter

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.abbas.gamesign.utils.GlideUtil


class BindingAdapter {

    companion object {

        @JvmStatic
        @BindingAdapter("visibility")
        fun View.setBoolToVisibility(visibility: Boolean) {
            this.visibility = if (visibility) View.VISIBLE else View.GONE
        }

        @JvmStatic
        @BindingAdapter("loadUrl")
        fun AppCompatImageView.setImageUrl(url: String?) {
            GlideUtil.loadWithRetry(this, url)
        }

    }

}