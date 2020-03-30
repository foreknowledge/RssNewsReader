package com.foreknowledge.rssnewsreader.util

import android.util.Log
import com.foreknowledge.rssnewsreader.RSS_URL
import com.foreknowledge.rssnewsreader.adapter.NewsRecyclerAdapter
import com.foreknowledge.rssnewsreader.model.News
import com.foreknowledge.rssnewsreader.util.HtmlParser.parseHtmlDataAndFill
import com.prof.rssparser.Parser
import kotlinx.coroutines.*

object RssParser {
    private val tag = javaClass.simpleName

    fun execute(
        adapter: NewsRecyclerAdapter,
        endLoading: () -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
            val newsList = mutableListOf<News>()
            try {
                val articles = Parser().getChannel(RSS_URL).articles

                for ((i, article) in articles.withIndex())
                    newsList.add(News(id = i,title = article.title, link = article.link))

                adapter.setNewsItem(newsList)

                val jobs = mutableListOf<Job>()
                for (news in newsList)
                    jobs.add(
                        launch {
                            news.parseHtmlDataAndFill()
                            CoroutineScope(Dispatchers.Main).launch { adapter.notifyItemChanged(news.id)}
                        }
                    )

                for (job in jobs)
                    job.join()

                CoroutineScope(Dispatchers.Main).launch {
                    run(endLoading)
                }

            } catch (e: Exception) {
                run(endLoading)
                Log.d(tag, e.message.toString())
            }
        }
}