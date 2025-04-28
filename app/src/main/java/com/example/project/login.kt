package com.example.project

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class login : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        val btn = findViewById<Button>(R.id.login_button)
        val signupbtn = findViewById<TextView>(R.id.signupkaro)

        btn.setOnClickListener {
            val enteredUsername = findViewById<EditText>(R.id.username).text.toString()
            val enteredEmail = findViewById<EditText>(R.id.email).text.toString()
            val enteredPassword = findViewById<EditText>(R.id.password).text.toString()

            // Retrieve saved credentials from SharedPreferences
            val sharedPref = getSharedPreferences("UserData", MODE_PRIVATE)
            val isRegistered = sharedPref.getBoolean("IS_REGISTERED", false)
            val savedUsername = sharedPref.getString("USERNAME", "")
            val savedEmail = sharedPref.getString("EMAIL", "")
            val savedPassword = sharedPref.getString("PASSWORD", "")

            if (isRegistered &&
                enteredUsername == savedUsername &&
                enteredEmail == savedEmail &&
                enteredPassword == savedPassword
            ) {
                // Save session details (optional)
                val sessionPref = getSharedPreferences("MyPrefs", MODE_PRIVATE)
                with(sessionPref.edit()) {
                    putString("USERNAME", enteredUsername)
                    putString("EMAIL", enteredEmail)
                    apply()
                }

                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()

                // Navigate to main page
                val intent = Intent(this, mainpage::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(
                    this,
                    "Login failed. Please sign up first or check your credentials.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        signupbtn.setOnClickListener {
            val intent = Intent(this, signup::class.java)
            startActivity(intent)
        }
    }
}
