package com.foreknowledge.rssnewsreader.ui

import android.os.Bundle
import com.foreknowledge.rssnewsreader.NewsApplication.Companion.mainViewModel
import com.foreknowledge.rssnewsreader.R
import com.foreknowledge.rssnewsreader.adapter.NewsRecyclerAdapter
import com.foreknowledge.rssnewsreader.base.BaseActivity
import com.foreknowledge.rssnewsreader.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.run {
            mainViewModel.run {
                if (newsList.value != null)
                    adapter = NewsRecyclerAdapter(newsList.value!!)
                viewModel = this
            }
            lifecycleOwner = this@MainActivity
        }

    }
}
