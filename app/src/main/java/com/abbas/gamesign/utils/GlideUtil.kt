package com.abbas.gamesign.utils

import android.graphics.drawable.Drawable
import android.os.Handler
import androidx.appcompat.widget.AppCompatImageView
import com.abbas.gamesign.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions

object GlideUtil {

    fun loadWithRetry(imageView: AppCompatImageView, url: String?) {

        val MAX_RETRIES = -1 // Set -1 for infinite retries

        val options = RequestOptions().placeholder(R.drawable.loading)

        var retryCount = 0
        val handler = Handler()

        if (url != null)
            Glide.with(imageView.context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(options)
                .listener(object : RequestListener<Drawable?> {
                    override fun onLoadFailed(
                        e: GlideException?, model: Any?, target: com.bumptech.glide.request.target.Target<Drawable?>?, isFirstResource: Boolean
                    ): Boolean {
                        if (retryCount != MAX_RETRIES) {
                            handler.postDelayed({
                                Glide.with(imageView.context)
                                    .load(url)
                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .apply(options)
                                    .listener(this)
                                    .into(imageView)
                                retryCount++
                            }, 1000) // Retry after 1 second
                            return true // Return true to prevent Glide from displaying the error placeholder
                        }
                        return false // Return false to let Glide handle the error and display the error placeholder
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: com.bumptech.glide.request.target.Target<Drawable?>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        retryCount = 0 // Reset retry count on successful load
                        return false // Return false to let Glide handle the successful load and display the loaded image
                    }
                }).into(imageView)
        else Glide.with(imageView.context).load(R.drawable.loading).into(imageView)
    }

}