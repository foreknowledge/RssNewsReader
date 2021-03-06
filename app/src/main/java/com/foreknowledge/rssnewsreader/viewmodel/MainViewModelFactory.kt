package com.foreknowledge.rssnewsreader.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.foreknowledge.rssnewsreader.model.repository.NewsRepository

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(
    private val newsRepository: NewsRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T = MainViewModel(newsRepository) as T
}