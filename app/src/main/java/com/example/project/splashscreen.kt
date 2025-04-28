package com.example.project

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class splashscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splashscreen)

        // Delay for 3 seconds before moving to mainpage
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, mainpage::class.java)
            startActivity(intent)
            finish() // prevent going back to splashscreen
        }, 3000)
    }
}
