package com.foreknowledge.rssnewsreader.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModelProvider
import com.foreknowledge.rssnewsreader.*
import com.foreknowledge.rssnewsreader.base.BaseActivity
import com.foreknowledge.rssnewsreader.databinding.ActivityDetailBinding
import com.foreknowledge.rssnewsreader.model.repository.NewsRepository
import com.foreknowledge.rssnewsreader.viewmodel.MainViewModel
import com.foreknowledge.rssnewsreader.viewmodel.MainViewModelFactory

class DetailActivity : BaseActivity<ActivityDetailBinding>(R.layout.activity_detail) {
    private val mainViewModel by lazy {
        ViewModelProvider(this, MainViewModelFactory(NewsRepository))[MainViewModel::class.java]
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val newsId = intent.getIntExtra(EXTRA_NEWS_ID, -1)
        binding.news = mainViewModel.newsLiveList.value?.getOrNull(newsId)
        binding.webView.run {
            settings.javaScriptEnabled = true
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
            loadUrl(binding.news?.link)
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
