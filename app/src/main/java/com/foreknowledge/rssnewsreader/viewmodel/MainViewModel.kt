package com.foreknowledge.rssnewsreader.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.foreknowledge.rssnewsreader.adapter.NewsRecyclerAdapter
import com.foreknowledge.rssnewsreader.util.RssParser

class MainViewModel : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    var adapter: NewsRecyclerAdapter

    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        _isLoading.postValue(true)
        adapter = NewsRecyclerAdapter()
        initOrRefreshAdapter { _isLoading.postValue(false) }
    }

    fun initOrRefreshAdapter(endLoading:() -> Unit) {
        RssParser.execute(adapter) { run(endLoading) }
    }
}