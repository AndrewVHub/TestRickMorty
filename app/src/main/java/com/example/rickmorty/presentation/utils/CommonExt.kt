package com.example.rickmorty.presentation.utils

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.load(url: String, error: Int? = null) {
    if (error == null)
        Picasso.get().load(url).into(this)
    else Picasso.get().load(url).error(error).into(this)
}
