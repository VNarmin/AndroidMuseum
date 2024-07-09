package com.example.joy.utilities

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.joy.R

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun ImageView.setImage(imageURL : String) {
    Glide.with(this).load(imageURL).placeholder(R.drawable.place_holder).into(this)
}