package com.example.joy.utilities

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("set_image")
fun setImageResource(imageView : ImageView, imageURL : String) {
    imageView.setImage(imageURL)
}