package com.foreknowledge.rssnewsreader.util

import com.foreknowledge.rssnewsreader.RSS_URL
import com.prof.rssparser.Parser

object RssParser {
    private

    suspend fun execute() = Parser().getChannel(RSS_URL).articles
}