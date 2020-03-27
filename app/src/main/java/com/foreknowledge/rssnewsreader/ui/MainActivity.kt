package com.foreknowledge.rssnewsreader.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.foreknowledge.rssnewsreader.NewsApplication
import com.foreknowledge.rssnewsreader.R
import com.foreknowledge.rssnewsreader.adapter.NewsRecyclerAdapter
import com.foreknowledge.rssnewsreader.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        with (NewsApplication) {
            val newsRecyclerAdapter = NewsRecyclerAdapter(newsList)
            newsRecyclerAdapter.setHasStableIds(true)

            newsAdapter = newsRecyclerAdapter
            binding.adapter = newsAdapter
        }

    }
}
