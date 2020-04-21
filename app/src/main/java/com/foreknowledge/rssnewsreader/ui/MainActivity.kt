package com.foreknowledge.rssnewsreader.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.foreknowledge.rssnewsreader.R
import com.foreknowledge.rssnewsreader.adapter.NewsRecyclerAdapter
import com.foreknowledge.rssnewsreader.base.BaseActivity
import com.foreknowledge.rssnewsreader.databinding.ActivityMainBinding
import com.foreknowledge.rssnewsreader.model.repository.NewsRepository
import com.foreknowledge.rssnewsreader.viewmodel.MainViewModel
import com.foreknowledge.rssnewsreader.viewmodel.MainViewModelFactory

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val mainViewModel by lazy {
        ViewModelProvider(this, MainViewModelFactory(NewsRepository))[MainViewModel::class.java]
    }

    private val newsRecyclerAdapter = NewsRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(binding) {
            viewModel = mainViewModel
            lifecycleOwner = this@MainActivity
            newsAdapter = newsRecyclerAdapter

            swipeRefresh.setOnRefreshListener {
                mainViewModel.initOrRefreshAdapter(
                    notifyItemChanged = { id -> newsRecyclerAdapter.notifyItemChanged(id) },
                    endLoading = { swipeRefresh.isRefreshing = false }
                )
            }
        }

        with (mainViewModel) {
            initOrRefreshAdapter(
                notifyItemChanged = { id -> newsRecyclerAdapter.notifyItemChanged(id) }
            )
            newsLiveList.observe(this@MainActivity, Observer {
                newsRecyclerAdapter.replaceItems(it)
            })
        }

    }
}
