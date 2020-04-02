package com.foreknowledge.rssnewsreader.util

import android.util.Log
import com.foreknowledge.rssnewsreader.RSS_URL
import com.foreknowledge.rssnewsreader.adapter.NewsRecyclerAdapter
import com.foreknowledge.rssnewsreader.model.data.News
import com.foreknowledge.rssnewsreader.util.HtmlParser.parseHtmlDataAndFill
import com.prof.rssparser.Parser
import kotlinx.coroutines.*

/*
 * Rss Parser 라이브러리 사용
 * [출처] https://github.com/prof18/RSS-Parser
 *
 * OkHttp 라이브러리 사용
 * [출처] https://github.com/square/okhttp
 *
 * Coroutine 라이브러리 사용
 * [출처] https://github.com/Kotlin/kotlinx.coroutines
 */

object RssParser {
    private val tag = javaClass.simpleName

    fun execute(
        adapter: NewsRecyclerAdapter,
        endLoading: () -> Unit,
        showFailMsg: () -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val newsList = mutableListOf<News>()
            try {
                val articles = Parser().getChannel(RSS_URL).articles

                for ((i, article) in articles.withIndex()) {
                    Log.d("tag", "title = ${article.title}")
                    newsList.add(
                        News(
                            id = i,
                            title = article.title,
                            link = article.link
                        )
                    )
                }
                adapter.updateItems(newsList)

                val jobs = mutableListOf<Job>()
                for (news in newsList)
                    jobs.add(
                        launch {
                            news.parseHtmlDataAndFill()
                            CoroutineScope(Dispatchers.Main).launch { adapter.notifyItemChanged(news.id) }
                        }
                    )

                for (job in jobs)
                    job.join()

                CoroutineScope(Dispatchers.Main).launch {
                    endLoading()
                }

            } catch (e: Exception) {
                endLoading()
                showFailMsg()
                Log.d(tag, e.message.toString())
            }
        }
    }
}