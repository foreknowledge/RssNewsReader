package com.foreknowledge.rssnewsreader.model.repository

import androidx.lifecycle.MutableLiveData
import com.foreknowledge.rssnewsreader.adapter.NewsRecyclerAdapter
import com.foreknowledge.rssnewsreader.model.data.News
import com.foreknowledge.rssnewsreader.util.RssParser

object NewsRepository {
    fun parseRssData(
        newsLiveList: MutableLiveData<List<News>>,
        notifyItemChanged:(id: Int) -> Unit,
        endLoading: () -> Unit,
        showFailMessage: () -> Unit
    ) {
        RssParser.execute(
            newsLiveList = newsLiveList,
            notifyItemChanged = notifyItemChanged,
            endLoading = endLoading,
            showFailMsg = showFailMessage
        )
    }
}