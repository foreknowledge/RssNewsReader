package com.foreknowledge.rssnewsreader.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.foreknowledge.rssnewsreader.R
import com.foreknowledge.rssnewsreader.base.BaseActivity
import com.foreknowledge.rssnewsreader.databinding.ActivityMainBinding
import com.foreknowledge.rssnewsreader.model.repository.NewsRepository
import com.foreknowledge.rssnewsreader.viewmodel.MainViewModel
import com.foreknowledge.rssnewsreader.viewmodel.MainViewModelFactory

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val mainViewModel by lazy {
        ViewModelProvider(this, MainViewModelFactory(NewsRepository))[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.run {
            viewModel = mainViewModel
            lifecycleOwner = this@MainActivity

            swipeRefresh.setOnRefreshListener {
                mainViewModel.initOrRefreshAdapter {
                    swipeRefresh.isRefreshing = false
                }
            }
        }

    }
}
