package com.foreknowledge.rssnewsreader.ui

import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
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

        binding.title = intent.getStringExtra(EXTRA_TITLE)
        binding.keywords = intent.getStringExtra(EXTRA_KEYWORDS)?.split(",")
        binding.webView.run {
            //settings.javaScriptEnabled = true
            webChromeClient = object: WebChromeClient() {
                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    super.onProgressChanged(view, newProgress)
                    binding.progressBar.progress = newProgress
                }
            }
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView, url: String) {
                    super.onPageFinished(view, url)
                    binding.progressBar.visibility = View.GONE
                }
            }
            loadUrl(intent.getStringExtra(EXTRA_LINK))
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        binding.run {
            webView.stopLoading()
            webView.destroy()
        }
    }
}
