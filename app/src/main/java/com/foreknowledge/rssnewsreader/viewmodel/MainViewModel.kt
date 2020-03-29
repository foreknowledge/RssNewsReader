package com.foreknowledge.rssnewsreader.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.foreknowledge.rssnewsreader.RSS_URL
import com.foreknowledge.rssnewsreader.adapter.NewsRecyclerAdapter
import com.foreknowledge.rssnewsreader.model.News
import com.prof.rssparser.Parser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    lateinit var adapter: NewsRecyclerAdapter

    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        _isLoading.postValue(true)
        initAdapter()
    }

    private fun initAdapter() : List<News> {
        val newsList = mutableListOf<News>()
        adapter = NewsRecyclerAdapter(newsList)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val articles = Parser().getChannel(RSS_URL).articles

                for (article in articles)
                    newsList.add(News(title = article.title, link = article.link))

                for (news in newsList)
                    launch {
                        news.fill()
                        CoroutineScope(Dispatchers.Main).launch{
                            _isLoading.postValue(false)
                            //adapter.updateItems(newsList)
                            adapter.notifyDataSetChanged()
                        }
                    }
            } catch (e: Exception) {
                Log.d(javaClass.simpleName, e.message.toString())
                _isLoading.postValue(false)
            }
        }

        return newsList
    }
}