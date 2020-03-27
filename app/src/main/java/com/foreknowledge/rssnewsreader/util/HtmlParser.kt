package com.foreknowledge.rssnewsreader.util

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

object HtmlParser {
    fun parse(url: String?): Document = Jsoup.connect(url).get()

    fun Document.getDescription() = select("meta[property=og:description]").getOrNull(0)?.attr("content")
    fun Document.getImageUrl() = select("meta[property=og:image]").getOrNull(0)?.attr("content")
}