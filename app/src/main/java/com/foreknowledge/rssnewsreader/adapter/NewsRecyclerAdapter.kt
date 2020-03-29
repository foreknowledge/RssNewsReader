package com.foreknowledge.rssnewsreader.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.foreknowledge.rssnewsreader.databinding.ItemNewsBinding
import com.foreknowledge.rssnewsreader.holder.NewsHolder
import com.foreknowledge.rssnewsreader.model.News
import com.foreknowledge.rssnewsreader.util.NewsDiffUtil

class NewsRecyclerAdapter(private var newsList: MutableList<News> = mutableListOf()) : RecyclerView.Adapter<NewsHolder>() {

    override fun getItemCount(): Int = newsList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder =
        NewsHolder(ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: NewsHolder, position: Int) =
        holder.bind(newsList[position])

    fun setNewsItem(newList: MutableList<News>) {
        newsList = newList
    }

    fun updateItems(newList: List<News>) {
        val diffUtil = NewsDiffUtil(newsList, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtil, true)

        newsList.clear()
        newsList.addAll(newList)

        diffResult.dispatchUpdatesTo(this)
    }
}