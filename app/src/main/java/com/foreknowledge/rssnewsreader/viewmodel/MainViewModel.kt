package com.foreknowledge.rssnewsreader.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.foreknowledge.rssnewsreader.adapter.NewsRecyclerAdapter
import com.foreknowledge.rssnewsreader.model.repository.NewsRepository

class MainViewModel(
    private val repository: NewsRepository
) : ViewModel() {
    private val _isLoading = MutableLiveData(true)
    private val _isFailToFetch = MutableLiveData(false)
    var newsAdapter = NewsRecyclerAdapter()

    val isLoading: LiveData<Boolean>
        get() = _isLoading

    val isFailToFetch: LiveData<Boolean>
        get() = _isFailToFetch

    init {
        initOrRefreshAdapter { _isLoading.postValue(false) }
    }

    fun initOrRefreshAdapter(endLoading:() -> Unit) {
        _isFailToFetch.postValue(false)
        repository.setAdapter(
            adapter = newsAdapter,
            endLoading = endLoading,
            showFailMessage = { _isFailToFetch.postValue(true)}
        )
    }
}