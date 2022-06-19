package com.example.rickmorty.presentation.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import androidx.core.view.isVisible
import com.squareup.picasso.Picasso

fun ImageView.load(url: String, error: Int? = null) {
    if (error == null)
        Picasso.get().load(url).into(this)
    else Picasso.get().load(url).error(error).into(this)
}

fun View.show(){
    this.isVisible = true
}

fun View.hide(){
    this.isVisible = false
}
