package com.foreknowledge.rssnewsreader.model

import android.content.Intent
import android.os.SystemClock
import android.view.View
import com.foreknowledge.rssnewsreader.EXTRA_KEYWORDS
import com.foreknowledge.rssnewsreader.EXTRA_LINK
import com.foreknowledge.rssnewsreader.EXTRA_TITLE
import com.foreknowledge.rssnewsreader.MIN_CLICK_INTERVAL
import com.foreknowledge.rssnewsreader.ui.DetailActivity

private var mLastClickTime = 0L

data class News (
    val id: Int,
    val title: String? = null,
    var description: String? = null,
    var imageUrl: String? = null,
    var keywords: List<String>? = null,
    val link: String? = null
) {
    fun onClick(view: View) {
        // 중복 클릭 방지
        if (SystemClock.elapsedRealtime() - mLastClickTime < MIN_CLICK_INTERVAL) return
        mLastClickTime = SystemClock.elapsedRealtime()

        view.context.run {
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra(EXTRA_TITLE, title)
                putExtra(EXTRA_LINK, link)
                putExtra(EXTRA_KEYWORDS, keywords?.joinToString(","))
            }

            startActivity(intent)
        }
    }
}