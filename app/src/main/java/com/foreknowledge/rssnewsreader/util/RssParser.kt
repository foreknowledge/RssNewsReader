package com.foreknowledge.rssnewsreader.util

import android.util.Log
import com.foreknowledge.rssnewsreader.NewsApplication
import com.foreknowledge.rssnewsreader.model.News
import com.prof.rssparser.Parser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RssParser {
    private val rssUrl = "https://news.google.com/rss?hl=ko&gl=KR&ceid=KR:ko"

    fun execute() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val newsList = NewsApplication.newsList
                val parser = Parser()
                val articleList = parser.getChannel(rssUrl)
                val articles = articleList.articles

                for ((i, article) in articles.withIndex())
                    newsList.add(News(id = i, title = article.title, link = article.link))

                for (news in newsList)
                    launch {
                        news.fill()
                        launch(Dispatchers.Main) {
                            NewsApplication.newsAdapter?.notifyDataSetChanged()
                        }
                    }
            } catch (e: Exception) {
                Log.d("test", e.message.toString())
            }
        }
    }
}