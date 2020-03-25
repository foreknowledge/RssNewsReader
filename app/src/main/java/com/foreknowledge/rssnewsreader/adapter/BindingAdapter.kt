package com.foreknowledge.rssnewsreader.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("bindBitmapImage")
fun bindBitmapImage(view: ImageView, url: String?) {
    Glide.with(view.context).load(url).into(view)
}

@BindingAdapter("fillKeywords")
fun fillKeywords(view: TextView, keywords: List<String>?) {
    if (keywords == null) view.text = ""
    else view.text = keywords.joinToString()
}