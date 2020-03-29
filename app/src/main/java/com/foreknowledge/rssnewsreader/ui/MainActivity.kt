package com.foreknowledge.rssnewsreader.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.foreknowledge.rssnewsreader.R
import com.foreknowledge.rssnewsreader.base.BaseActivity
import com.foreknowledge.rssnewsreader.databinding.ActivityMainBinding
import com.foreknowledge.rssnewsreader.viewmodel.MainViewModel

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val mainViewModel = ViewModelProvider.AndroidViewModelFactory(application).create(MainViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.run {
            viewModel = mainViewModel
            lifecycleOwner = this@MainActivity
        }

    }
}
