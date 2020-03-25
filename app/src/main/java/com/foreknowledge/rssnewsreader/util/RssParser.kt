package com.foreknowledge.rssnewsreader.util

import android.util.Log
import com.foreknowledge.rssnewsreader.adapter.NewsRecyclerAdapter
import com.foreknowledge.rssnewsreader.model.News
import com.prof.rssparser.Parser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class RssParser {
    private val rssUrl = "https://news.google.com/rss?hl=ko&gl=KR&ceid=KR:ko"

    fun execute() {
        val newsList = mutableListOf<News>()
        runBlocking(Dispatchers.IO) {
            launch {
                try {
                    val parser = Parser()
                    val articleList = parser.getChannel(rssUrl)
                    val articles = articleList.articles

                    for (article in articles)
                        newsList.add(News(title = article.title, link = article.link))

                    for (news in newsList)
                        launch { news.fill() }
                } catch (e: Exception) {
                    Log.d("test", e.message.toString())
                }
            }
        }

        NewsRecyclerAdapter.newsList = newsList
    }
}