package com.williamzabot.queens.presenter.extensions

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl

fun ImageView.urlImage(url: String) = Glide.with(this).load(GlideUrl(url)).into(this)

fun View.visible(visible: Boolean) {
    visibility = if (visible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}