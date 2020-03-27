package com.foreknowledge.rssnewsreader.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.foreknowledge.rssnewsreader.model.News
import com.foreknowledge.rssnewsreader.util.RssParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    private val _newsList = MutableLiveData<List<News>>()

    val isLoading: LiveData<Boolean>
        get() = _isLoading

    val newsList: LiveData<List<News>>
        get() = _newsList

    init {
        initValues()
    }

    private fun initValues() {
        _isLoading.postValue(true)
        parseNewsList()
    }

    private fun parseNewsList() {
        val newsList = mutableListOf<News>()
        _newsList.value = newsList

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val articles = RssParser.execute()
                _isLoading.postValue(false)

                for ((i, article) in articles.withIndex())
                    newsList.add(News(id = i, title = article.title, link = article.link))

                for (news in newsList)
                    launch {
                        news.fill()
                    }
            } catch (e: Exception) { Log.d("test", e.message.toString()) }
        }
    }
}