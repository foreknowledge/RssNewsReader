package com.foreknowledge.rssnewsreader.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.foreknowledge.rssnewsreader.adapter.NewsRecyclerAdapter
import com.foreknowledge.rssnewsreader.model.News
import com.foreknowledge.rssnewsreader.util.RssParser

class MainViewModel : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    lateinit var adapter: NewsRecyclerAdapter

    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        _isLoading.postValue(true)
        initAdapter()
    }

    private fun initAdapter() {
        val newsList = mutableListOf<News>()
        adapter = NewsRecyclerAdapter(newsList)

        RssParser.execute(newsList, adapter) { _isLoading.postValue(false) }
    }

    fun refreshList(endLoading:() -> Unit) {
        val newsList = mutableListOf<News>()
        RssParser.execute(newsList, adapter) { run(endLoading) }
    }
}