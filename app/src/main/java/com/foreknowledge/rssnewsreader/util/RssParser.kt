package com.foreknowledge.rssnewsreader.util

import android.util.Log
import com.foreknowledge.rssnewsreader.RSS_URL
import com.foreknowledge.rssnewsreader.adapter.NewsRecyclerAdapter
import com.foreknowledge.rssnewsreader.model.News
import com.prof.rssparser.Parser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object RssParser {
    private val tag = javaClass.simpleName

    fun execute(
        adapter: NewsRecyclerAdapter,
        endLoading: () -> Unit
    ) =
        CoroutineScope(Dispatchers.IO).launch {
            val newsList = mutableListOf<News>()
            try {
                val articles = Parser().getChannel(RSS_URL).articles

                for (article in articles)
                    newsList.add(News(title = article.title, link = article.link))

                for (news in newsList) {
                    Log.d(tag, "news title = ${news.title}")
                    launch {
                        news.parseHtmlDataAndFill()
                        CoroutineScope(Dispatchers.Main).launch {
                            run(endLoading)
                            adapter.setNewsItem(newsList)
                            adapter.notifyDataSetChanged()
                        }
                    }
                }
            } catch (e: Exception) {
                run(endLoading)
                Log.d(tag, e.message.toString())
            }
        }
}