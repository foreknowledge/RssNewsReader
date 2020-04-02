package com.foreknowledge.rssnewsreader.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.foreknowledge.rssnewsreader.R
import com.foreknowledge.rssnewsreader.model.data.News

/*
 * Glide 라이브러리 사용
 * [출처] https://github.com/bumptech/glide
 */

@BindingAdapter("bind_url_image")
fun ImageView.bindUrlImage(url: String?) {
    Glide.with(context)
        .load(url)
        .error(R.drawable.no_image)
        .into(this)
}

@BindingAdapter("bind_adapter")
fun RecyclerView.bindAdapter(newsAdapter: NewsRecyclerAdapter) {
    adapter = newsAdapter
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