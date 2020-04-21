package com.foreknowledge.rssnewsreader.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.foreknowledge.rssnewsreader.model.data.News
import com.foreknowledge.rssnewsreader.model.repository.NewsRepository

class MainViewModel(
    private val repository: NewsRepository
) : ViewModel() {
    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isFailToFetch = MutableLiveData(false)
    val isFailToFetch: LiveData<Boolean> = _isFailToFetch

    private val _newsLiveList = MutableLiveData(listOf<News>())
    val newsLiveList: LiveData<List<News>> = _newsLiveList

    private val stopLoading: () -> Unit = { _isLoading.postValue(false) }

    fun initOrRefreshAdapter(
        notifyItemChanged:(id: Int) -> Unit,
        endLoading:(() -> Unit)? = null
    ) {
        _isFailToFetch.postValue(false)
        repository.parseRssData(
            newsLiveList = _newsLiveList,
            notifyItemChanged = notifyItemChanged,
            endLoading = endLoading ?: stopLoading,
            showFailMessage = { _isFailToFetch.postValue(true)}
        )
    }
}