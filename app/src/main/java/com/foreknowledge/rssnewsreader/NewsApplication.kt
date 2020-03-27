package com.foreknowledge.rssnewsreader

import android.app.Application
import com.foreknowledge.rssnewsreader.viewmodel.MainViewModel

class NewsApplication : Application() {
    companion object {
        lateinit var mainViewModel : MainViewModel
    }
}