package com.foreknowledge.rssnewsreader.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.foreknowledge.rssnewsreader.model.data.News

@BindingAdapter("bind_bitmap_image")
fun ImageView.bindBitmapImage(url: String?) {
    Glide.with(context).load(url).into(this)
}

@BindingAdapter("bind_adapter")
fun RecyclerView.bindAdapter(list: List<News>) {
    adapter = NewsRecyclerAdapter(list).apply { setHasStableIds(true) }
}

@BindingAdapter("set_keyword")
fun TextView.setKeyword(keyword: String?) {
    visibility =
        if (keyword == null)
            View.INVISIBLE
        else {
            text = keyword
            View.VISIBLE
        }
}