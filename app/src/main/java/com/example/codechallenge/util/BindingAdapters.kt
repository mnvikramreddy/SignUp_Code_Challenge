package com.example.codechallenge.util

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("imageFromBitmap")
fun bindImageFromBitmap(view: ImageView, imageBitmap: Bitmap?) {
    view.setImageBitmap(imageBitmap)
}