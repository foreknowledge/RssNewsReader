package com.foreknowledge.rssnewsreader.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

@BindingAdapter("bindBitmapImage")
fun ImageView.bindBitmapImage(url: String?) {
    Glide.with(context).load(url).into(this)
}

@BindingAdapter("fillKeywords")
fun TextView.fillKeywords(keywords: List<String>?) {
    text = keywords?.joinToString() ?: ""
}

@BindingAdapter("bindAdapter")
fun RecyclerView.bindAdapter(adapter: NewsRecyclerAdapter) = setAdapter(adapter)