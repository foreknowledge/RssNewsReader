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
        view.context.run {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("id", id)

            startActivity(intent)
        }
    }

    fun fill() {
        try {
            HtmlParser.parse(link).run {
                description = getDescription()?.trim()
                imageUrl = getImageUrl()
            }

            keywords = description?.let {
                KeywordExtractor.extract(it)
            }
        }
        catch (e: Exception) { Log.d("News::", e.message.toString()) }
    }

    override fun hashCode(): Int = this.hashCode()

    override fun equals(other: Any?): Boolean =
        this.hashCode() == other.hashCode()
}