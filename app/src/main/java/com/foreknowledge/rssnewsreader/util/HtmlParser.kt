package com.foreknowledge.rssnewsreader.util

import android.util.Log
import com.foreknowledge.rssnewsreader.model.News
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.lang.Exception

object HtmlParser {
    private val tag = javaClass.simpleName

    fun News.parseHtmlDataAndFill() {
        if (link == null) return

        try {
            parse(link).run {
                description = getDescription()?.refine()
                imageUrl = getImageUrl()
            }

            keywords = description?.let {
                KeywordExtractor.extract(it)
            }

        } catch (e: Exception) {
            Log.d(tag, e.message.toString())
        }
    }

    private fun parse(url: String?): Document = Jsoup.connect(url).get()
    private fun Document.getDescription() = select("meta[property=og:description]").getOrNull(0)?.attr("content")
    private fun Document.getImageUrl() = select("meta[property=og:image]").getOrNull(0)?.attr("content")

    private fun String.refine() =
        this.trim()
            .replace("\n", " ")
            .replace("\t", " ")
}