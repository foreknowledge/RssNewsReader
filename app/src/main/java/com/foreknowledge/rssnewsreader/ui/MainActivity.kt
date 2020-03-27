package com.foreknowledge.rssnewsreader.ui

import android.os.Bundle
import com.foreknowledge.rssnewsreader.NewsApplication.Companion.mainViewModel
import com.foreknowledge.rssnewsreader.R
import com.foreknowledge.rssnewsreader.base.BaseActivity
import com.foreknowledge.rssnewsreader.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {
            viewModel = mainViewModel
            lifecycleOwner = this@MainActivity
        }

    }
}
