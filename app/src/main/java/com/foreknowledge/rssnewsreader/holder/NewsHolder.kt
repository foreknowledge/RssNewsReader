package com.foreknowledge.rssnewsreader.holder

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.foreknowledge.rssnewsreader.databinding.ItemNewsBinding
import com.foreknowledge.rssnewsreader.model.data.News

class NewsHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(news: News) {
        binding.run {
            setNews(news)
            executePendingBindings()
        }
    }
}