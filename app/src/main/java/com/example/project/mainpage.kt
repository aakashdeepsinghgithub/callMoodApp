package com.example.project

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.content.Intent
import android.widget.ImageView
import android.widget.ProgressBar
import android.net.Uri
import androidx.viewpager2.widget.ViewPager2
import android.widget.ImageButton

class mainpage : AppCompatActivity() {

    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mainpage)

        // 🔹 Retrieve saved username and email from SharedPreferences
        val sharedPref = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val username = sharedPref.getString("USERNAME", "Guest")
        val email = sharedPref.getString("EMAIL", "No Email")

        // 🔹 Display the username in the TextView
        val heyusetext = findViewById<TextView>(R.id.loginname)
        heyusetext.text = "Welcome, $username"

        val loginn = findViewById<ImageView>(R.id.loginkaro)
        loginn.setOnClickListener {
            val intent = Intent(this, login::class.java)
            startActivity(intent)
        }

        val checkmood = findViewById<TextView>(R.id.checkmood)
        checkmood.setOnClickListener {
            val intent = Intent(this, questions::class.java)
            startActivity(intent)
        }

        val call = findViewById<TextView>(R.id.call)
        call.setOnClickListener {
            val intent = Intent(this, dialpad::class.java)
            startActivity(intent)
        }

        val scoreFloat = intent.getFloatExtra("score", 0.0f)
        val score = scoreFloat.toInt()

        val sadProgress = findViewById<ProgressBar>(R.id.sadprogress)
        val confusedProgress = findViewById<ProgressBar>(R.id.confusedprogress)
        val cheerfulProgress = findViewById<ProgressBar>(R.id.happyprogress)

        when (score) {
            1 -> {
                sadProgress.progress = 80
                confusedProgress.progress = 50
                cheerfulProgress.progress = 20
            }
            2 -> {
                sadProgress.progress = 50
                confusedProgress.progress = 80
                cheerfulProgress.progress = 50
            }
            3 -> {
                sadProgress.progress = 20
                confusedProgress.progress = 50
                cheerfulProgress.progress = 80
            }
            else -> {
                sadProgress.progress = 50
                confusedProgress.progress = 50
                cheerfulProgress.progress = 50
            }
        }

        val videobtn = findViewById<ImageView>(R.id.videojao)
        videobtn.setOnClickListener {
            val youtubeIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=8-2WQF3SWwo"))
            startActivity(youtubeIntent)
        }

        val yogabtn = findViewById<ImageView>(R.id.yogajao)
        yogabtn.setOnClickListener {
            val intent = Intent(this, yoga::class.java)
            startActivity(intent)
        }

        val profilebtn = findViewById<ImageView>(R.id.profilejao)
        profilebtn.setOnClickListener {
            val intent = Intent(this, profile::class.java)
            startActivity(intent)
        }

        val imageList = listOf(
            R.drawable.q1, R.drawable.q2, R.drawable.q3,
            R.drawable.q4, R.drawable.q5, R.drawable.q6,
            R.drawable.q7, R.drawable.q8, R.drawable.q9,
            R.drawable.q10, R.drawable.q11, R.drawable.q12,
            R.drawable.q13, R.drawable.q14, R.drawable.q15
        )

        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val adapter = ImageAdapter(imageList)
        viewPager.adapter = adapter

        val btnPrev = findViewById<ImageButton>(R.id.btnPrev)
        val btnNext = findViewById<ImageButton>(R.id.btnNext)

        btnPrev.setOnClickListener {
            val current = viewPager.currentItem
            if (current > 0) viewPager.currentItem = current - 1
        }

        btnNext.setOnClickListener {
            val current = viewPager.currentItem
            if (current < imageList.size - 1) viewPager.currentItem = current + 1
        }
    }
}
