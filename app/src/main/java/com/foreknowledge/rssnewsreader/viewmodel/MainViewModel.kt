package com.foreknowledge.rssnewsreader.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.foreknowledge.rssnewsreader.adapter.NewsRecyclerAdapter
import com.foreknowledge.rssnewsreader.util.RssParser

class MainViewModel : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    private val _showFailMsg = MutableLiveData<Boolean>()
    var adapter: NewsRecyclerAdapter

    val isLoading: LiveData<Boolean>
        get() = _isLoading

    val showFailMsg: LiveData<Boolean>
        get() = _showFailMsg

    init {
        _isLoading.postValue(true)
        _showFailMsg.postValue(false)
        adapter = NewsRecyclerAdapter()
        initOrRefreshAdapter { _isLoading.postValue(false) }
    }

    fun initOrRefreshAdapter(endLoading:() -> Unit) {
        _showFailMsg.postValue(false)
        RssParser.execute(adapter, endLoading = endLoading, showFailMsg = { _showFailMsg.postValue(true)})
    }
}