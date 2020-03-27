package com.foreknowledge.rssnewsreader.model.repository

import android.util.Log
import com.foreknowledge.rssnewsreader.model.data.News
import com.foreknowledge.rssnewsreader.util.RssParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object NewsRepository {
    fun parseNewsList(
        parseStart:() -> Unit,
        parseSuccess:() -> Unit,
        parseFail:() -> Unit
    ) : List<News> {
        run(parseStart)
        val newsList = mutableListOf<News>()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val articles = RssParser.execute()

                run(parseSuccess)

                for ((i, article) in articles.withIndex())
                    newsList.add(
                        News(
                            id = i,
                            title = article.title,
                            link = article.link
                        )
                    )

                for (news in newsList)
                    launch {
                        news.fill()
                    }
            } catch (e: Exception) {
                Log.d("test", e.message.toString())
                run(parseFail)
            }
        }

        return newsList
    }
}