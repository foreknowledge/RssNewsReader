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
        val newsList = mutableListOf<News>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                Log.d("test", "rss parse start")
                val parser = Parser()
                val articleList = parser.getChannel(rssUrl)
                val articles = articleList.articles

                for (article in articles)
                    newsList.add(News(title = article.title, link = article.link))

                for (news in newsList)
                    launch {
                        Log.d("test", "news title = ${news.title}")
                        news.fill()
                        NewsApplication.newsList.add(news)
                        launch(Dispatchers.Main) {
                            NewsApplication.mainRecyclerAdapter?.notifyDataSetChanged()
                        }
                        Log.d("test", "news imageUrl = ${news.imageUrl}")
                    }
                Log.d("test", "rss parse end")
            } catch (e: Exception) {
                Log.d("test", e.message.toString())
            }
        }
    }
}