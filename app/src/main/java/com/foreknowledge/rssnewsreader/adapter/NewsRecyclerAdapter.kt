package com.foreknowledge.rssnewsreader.adapter

import com.foreknowledge.rssnewsreader.R
import com.foreknowledge.rssnewsreader.base.BaseRecyclerAdapter
import com.foreknowledge.rssnewsreader.databinding.ItemNewsBinding
import com.foreknowledge.rssnewsreader.model.data.News

class NewsRecyclerAdapter: BaseRecyclerAdapter<ItemNewsBinding, News>(
    R.layout.item_news
) {
    override fun replaceItems(newItems: List<News>?) {
        if (newItems != null) {
            items = newItems
            notifyDataSetChanged()
        }
    }

}