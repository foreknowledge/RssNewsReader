package com.foreknowledge.rssnewsreader.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.foreknowledge.rssnewsreader.databinding.ItemNewsBinding
import com.foreknowledge.rssnewsreader.holder.NewsHolder
import com.foreknowledge.rssnewsreader.model.News

class NewsRecyclerAdapter(private var newsList: List<News> = listOf()) : RecyclerView.Adapter<NewsHolder>() {

    override fun getItemCount(): Int = newsList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder =
        NewsHolder(ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: NewsHolder, position: Int) =
        holder.bind(newsList[position])

    fun setNewsItem(newList: List<News>) {
        newsList = newList
    }
}