package com.foreknowledge.rssnewsreader.model.repository

import android.util.Log
import com.foreknowledge.rssnewsreader.adapter.NewsRecyclerAdapter
import com.foreknowledge.rssnewsreader.model.data.News
import com.foreknowledge.rssnewsreader.util.RssParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object NewsRepository {
    fun parseNewsList(
        adapter: NewsRecyclerAdapter?,
        endLoading:() -> Unit
    ) : List<News> {
        val newsList = mutableListOf<News>()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val articles = RssParser.execute()
                run(endLoading)

                for ((i, article) in articles.withIndex())
                    newsList.add(News(id = i, title = article.title, link = article.link))

                for (news in newsList)
                    launch {
                        news.fill()
                        adapter?.updateItems(newsList)
                    }
            } catch (e: Exception) {
                Log.d("NewsRepository::", e.message.toString())
                run(endLoading)
            }
        }

        return newsList
    }
}