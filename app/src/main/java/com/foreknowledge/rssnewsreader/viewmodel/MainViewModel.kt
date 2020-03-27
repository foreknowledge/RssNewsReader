package com.foreknowledge.rssnewsreader.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.foreknowledge.rssnewsreader.model.data.News
import com.foreknowledge.rssnewsreader.model.repository.NewsRepository

class MainViewModel(
    private val repository : NewsRepository
) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    private val _newsList = MutableLiveData<List<News>>()

    val isLoading: LiveData<Boolean>
        get() = _isLoading

    val newsList: LiveData<List<News>>
        get() = _newsList

    init {
        _isLoading.postValue(true)
        initNewsList()
    }

    private fun initNewsList() {
        _newsList.postValue(
            repository.parseNewsList(
                endLoading = { _isLoading.postValue(false) }
            )
        )
    }
}