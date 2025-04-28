package com.example.project

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class profile : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        val usernameText = findViewById<TextView>(R.id.username)
        val emailText = findViewById<TextView>(R.id.email)

        // Get user data from SharedPreferences
        val sharedPref = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val username = sharedPref.getString("USERNAME", "")
        val email = sharedPref.getString("EMAIL", "")

        usernameText.text = username
        emailText.text = email

        val logoutbtn = findViewById<TextView>(R.id.logoutkaro)
        logoutbtn.setOnClickListener {
            val editor = sharedPref.edit()
            editor.clear() // clears all data
            editor.apply()

            Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, login::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        val homebtn = findViewById<ImageView>(R.id.homejao)
        homebtn.setOnClickListener {
            val intent = Intent(this, mainpage::class.java)
            startActivity(intent)
        }

        val videobtn = findViewById<ImageView>(R.id.videojao)
        videobtn.setOnClickListener {
            val youtubeIntent = Intent(Intent.ACTION_VIEW, android.net.Uri.parse("https://www.youtube.com/watch?v=8-2WQF3SWwo"))
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
    }
}
