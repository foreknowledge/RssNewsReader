package com.foreknowledge.rssnewsreader.model.data

import android.content.Intent
import android.util.Log
import android.view.View
import com.foreknowledge.rssnewsreader.ui.DetailActivity
import com.foreknowledge.rssnewsreader.util.HtmlParser
import com.foreknowledge.rssnewsreader.util.HtmlParser.getDescription
import com.foreknowledge.rssnewsreader.util.HtmlParser.getImageUrl
import com.foreknowledge.rssnewsreader.util.KeywordExtractor
import java.lang.Exception

data class News (
    val id: Int,
    val title: String? = null,
    var description: String? = null,
    var imageUrl: String? = null,
    var keywords: List<String>? = null,
    val link: String? = null
) {
    fun onClick(view: View) {
        val context = view.context

        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("id", id)

        context.startActivity(intent)
    }

    fun fill() {
        try {
            HtmlParser.parse(imageUrl).run {
                description = getDescription()?.trim()
                imageUrl = getImageUrl()
            }

            keywords = description?.let {
                KeywordExtractor.extract(it)
            }
        }
        catch (e: Exception) { Log.d("test", e.message.toString()) }
    }
}