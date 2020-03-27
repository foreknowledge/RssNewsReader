package com.foreknowledge.rssnewsreader.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.foreknowledge.rssnewsreader.R
import com.foreknowledge.rssnewsreader.model.News
import com.foreknowledge.rssnewsreader.util.RssParser

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        RssParser().execute()

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 1300)
    }
}
