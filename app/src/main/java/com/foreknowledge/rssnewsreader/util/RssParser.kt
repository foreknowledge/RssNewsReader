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

    fun execute(
        newsList: MutableList<News>,
        adapter: NewsRecyclerAdapter,
        endLoading: () -> Unit
    ) =
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val articles = Parser().getChannel(RSS_URL).articles

                for (article in articles)
                    newsList.add(News(title = article.title, link = article.link))

                for (news in newsList)
                    launch {
                        news.fill()
                        CoroutineScope(Dispatchers.Main).launch{
                            run(endLoading)
                            //adapter.updateItems(newsList)
                            adapter.notifyDataSetChanged()
                        }
                    }
            } catch (e: Exception) {
                Log.d(javaClass.simpleName, e.message.toString())
                run(endLoading)
            }
        }
}