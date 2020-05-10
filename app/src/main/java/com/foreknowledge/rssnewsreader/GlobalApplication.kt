package com.foreknowledge.rssnewsreader

import android.app.Application
import com.foreknowledge.rssnewsreader.viewmodel.MainViewModel

/**
 * Create by Yeji on 11,May,2020.
 */
class GlobalApplication: Application() {
    companion object {
        lateinit var mainViewModel: MainViewModel
    }
}