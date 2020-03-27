package com.foreknowledge.rssnewsreader.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.ViewModelProvider
import com.foreknowledge.rssnewsreader.NewsApplication.Companion.mainViewModel
import com.foreknowledge.rssnewsreader.viewmodel.MainViewModel

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = ViewModelProvider.AndroidViewModelFactory(application).create(MainViewModel::class.java)

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 1300)
    }
}
