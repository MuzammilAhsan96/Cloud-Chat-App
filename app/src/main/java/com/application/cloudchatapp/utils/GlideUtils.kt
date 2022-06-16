package com.application.cloudchatapp.utils

import android.widget.ImageView
import com.application.cloudchatapp.R
import com.application.cloudchatapp.base.App
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

object GlideUtils {

    fun loadSmallImage(
        view: ImageView?,
        url: String?
    ) {

        view?.let {
            Glide.with(App.INSTANCE)
                .load(url)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.ic_small_placeholder)
                        .error(R.drawable.ic_small_placeholder)
                        .skipMemoryCache(false)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                )
                .into(it)
        }
    }
}