package com.foreknowledge.rssnewsreader.ui

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import com.foreknowledge.rssnewsreader.EXTRA_KEYWORDS
import com.foreknowledge.rssnewsreader.EXTRA_LINK
import com.foreknowledge.rssnewsreader.EXTRA_TITLE
import com.foreknowledge.rssnewsreader.R
import com.foreknowledge.rssnewsreader.base.BaseActivity
import com.foreknowledge.rssnewsreader.databinding.ActivityDetailBinding

class DetailActivity : BaseActivity<ActivityDetailBinding>(R.layout.activity_detail) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.run {
            title = intent.getStringExtra(EXTRA_TITLE)
            keywords = intent.getStringExtra(EXTRA_KEYWORDS)?.split(",")
            webView.run {
                //settings.javaScriptEnabled = true
                webChromeClient = WebChromeClient()
                webViewClient = WebViewClient()
                loadUrl(intent.getStringExtra(EXTRA_LINK))
            }
        }
    }
}
