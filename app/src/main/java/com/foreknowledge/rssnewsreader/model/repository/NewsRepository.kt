package com.foreknowledge.rssnewsreader.model.repository

import com.foreknowledge.rssnewsreader.adapter.NewsRecyclerAdapter
import com.foreknowledge.rssnewsreader.util.RssParser

object NewsRepository {
    fun setAdapter(
        adapter: NewsRecyclerAdapter,
        endLoading: () -> Unit,
        showFailMessage: () -> Unit
    ) {
        RssParser.execute(
            adapter = adapter,
            endLoading = endLoading,
            showFailMsg = showFailMessage
        )
    }
}