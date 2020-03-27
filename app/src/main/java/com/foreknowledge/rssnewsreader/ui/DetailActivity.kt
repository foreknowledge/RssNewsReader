package com.foreknowledge.rssnewsreader.ui

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import com.foreknowledge.rssnewsreader.NewsApplication.Companion.mainViewModel
import com.foreknowledge.rssnewsreader.R
import com.foreknowledge.rssnewsreader.base.BaseActivity
import com.foreknowledge.rssnewsreader.databinding.ActivityDetailBinding

class DetailActivity : BaseActivity<ActivityDetailBinding>(R.layout.activity_detail) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val id = intent.getIntExtra("id", -1)
        val news = mainViewModel.newsList.value?.getOrNull(id)

        binding.run {
            this.news = news
            this.webView.run {
                //settings.javaScriptEnabled = true
                webChromeClient = WebChromeClient()
                webViewClient = WebViewClient()
                loadUrl(news?.link)
            }
        }
    }
}
