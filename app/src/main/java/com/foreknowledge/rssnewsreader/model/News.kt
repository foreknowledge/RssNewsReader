package com.foreknowledge.rssnewsreader.model

import android.content.Intent
import android.util.Log
import android.view.View
import com.foreknowledge.rssnewsreader.ui.DetailActivity
import com.foreknowledge.rssnewsreader.util.KeywordExtractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import java.lang.Exception

data class News (
    var title: String? = null,
    var description: String? = null,
    var imageUrl: String? = null,
    var keywords: List<String>? = null,
    var link: String? = null
) {
    fun onClick(view: View) {
        val context = view.context

        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("link", link)

        context.startActivity(intent)
    }

    fun fill() {
        try {
            val doc = Jsoup.connect(link).get()

            description = doc.select("meta[property=og:description]").getOrNull(0)?.attr("content")
            imageUrl = doc.select("meta[property=og:image]").getOrNull(0)?.attr("content")

            keywords = description?.let {
                KeywordExtractor.extract(it)
            }
        } catch (e: Exception) { Log.d("test", e.message.toString()) }
    }
}