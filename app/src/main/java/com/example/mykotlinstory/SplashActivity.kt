package com.example.mykotlinstory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        moveToLoginScreem()

    }
    private fun moveToLoginScreem(){
        // this code for delay 6 sec
        Handler(Looper.myLooper()!!).postDelayed({
            // this function will delete the splash activity from memory
            finish()
            val i = Intent(this,LoginActivity::class.java)
            startActivity(i)
        },6000)







    }



}