package com.foreknowledge.rssnewsreader.util

import com.prof.rssparser.Parser

object RssParser {
    private const val rssUrl = "https://news.google.com/rss?hl=ko&gl=KR&ceid=KR:ko"

    suspend fun execute() = Parser().getChannel(rssUrl).articles
}