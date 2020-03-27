package com.foreknowledge.rssnewsreader.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import com.foreknowledge.rssnewsreader.NewsApplication
import com.foreknowledge.rssnewsreader.R
import com.foreknowledge.rssnewsreader.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityDetailBinding>(this, R.layout.activity_detail)

        val id = intent.getIntExtra("id", 0)
        val news = NewsApplication.newsList[id]
        binding.news = news

        with(binding.webView) {
            //settings.javaScriptEnabled = true
            webChromeClient = WebChromeClient()
            webViewClient = WebViewClient()
            loadUrl(news.link)
        }
    }
}
