package com.foreknowledge.rssnewsreader.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.ViewModelProvider
import com.foreknowledge.rssnewsreader.NewsApplication.Companion.mainViewModel
import com.foreknowledge.rssnewsreader.model.repository.NewsRepository
import com.foreknowledge.rssnewsreader.viewmodel.MainViewModel
import com.foreknowledge.rssnewsreader.viewmodel.MainViewModelFactory

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(NewsRepository))[MainViewModel::class.java]

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 1300)
    }
}
