package com.foreknowledge.rssnewsreader.model

import android.content.Intent
import android.util.Log
import android.view.View
import com.foreknowledge.rssnewsreader.EXTRA_KEYWORDS
import com.foreknowledge.rssnewsreader.EXTRA_LINK
import com.foreknowledge.rssnewsreader.EXTRA_TITLE
import com.foreknowledge.rssnewsreader.ui.DetailActivity
import com.foreknowledge.rssnewsreader.util.HtmlParser
import com.foreknowledge.rssnewsreader.util.HtmlParser.getDescription
import com.foreknowledge.rssnewsreader.util.HtmlParser.getImageUrl
import com.foreknowledge.rssnewsreader.util.KeywordExtractor
import java.lang.Exception

data class News (
    val title: String? = null,
    var description: String? = null,
    var imageUrl: String? = null,
    var keywords: List<String>? = null,
    val link: String? = null
) {
    private val tag = javaClass.simpleName

    fun onClick(view: View) {
        view.context.run {
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra(EXTRA_TITLE, title)
                putExtra(EXTRA_LINK, link)
                putExtra(EXTRA_KEYWORDS, keywords?.joinToString(","))
            }

            startActivity(intent)
        }
    }

    fun parseHtmlDataAndFill() {
        try {
            HtmlParser.parse(link).run {
                description = getDescription()?.trim()
                imageUrl = getImageUrl()
            }

            keywords = description?.let {
                KeywordExtractor.extract(it)
            }
        }
        catch (e: Exception) {
            Log.d(tag, e.message.toString())
        }
    }
}