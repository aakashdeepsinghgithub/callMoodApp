package com.example.project

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.*
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

class dialpad : AppCompatActivity() {

    private lateinit var numberDisplay: TextView
    private var phoneNumber = StringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialpad)

        numberDisplay = findViewById(R.id.numberDisplay)

        // Ask for POST_NOTIFICATIONS permission for Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS), 2)
            }
        }

        val digits = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "*", "0", "#")
        val buttonIds = listOf(
            R.id.btn1, R.id.btn2, R.id.btn3,
            R.id.btn4, R.id.btn5, R.id.btn6,
            R.id.btn7, R.id.btn8, R.id.btn9,
            R.id.btnStar, R.id.btn0, R.id.btnHash
        )

        for (i in digits.indices) {
            val btnLayout = findViewById<LinearLayout>(buttonIds[i])
            val textView = btnLayout.findViewById<TextView>(R.id.btnText)
            textView.text = digits[i]

            btnLayout.setOnClickListener {
                if (phoneNumber.length < 10) {
                    phoneNumber.append(digits[i])
                    updateDisplay()
                }
            }
        }

        findViewById<ImageButton>(R.id.deleteButton).setOnClickListener {
            if (phoneNumber.isNotEmpty()) {
                phoneNumber.deleteAt(phoneNumber.length - 1)
                updateDisplay()
            }
        }

        findViewById<ImageButton>(R.id.callButton).setOnClickListener {
            if (phoneNumber.length == 10) {
                makePhoneCall(phoneNumber.toString())
            } else {
                Toast.makeText(this, "Enter a valid 10-digit number", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateDisplay() {
        numberDisplay.text = phoneNumber.toString()
    }

    private fun makePhoneCall(number: String) {
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:$number")

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 1)
        } else {
            try {
                startActivity(intent)

                // ⏳ Schedule Notification After 10 Seconds
                Handler(Looper.getMainLooper()).postDelayed({
                    showNotification()
                }, 10_000)

            } catch (e: SecurityException) {
                e.printStackTrace()
                Toast.makeText(this, "Call permission denied by system", Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            makePhoneCall(phoneNumber.toString())
        } else {
            Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("MissingPermission")
    private fun showNotification() {
        val channelId = "call_notification_channel"
        val channelName = "Call Notifications"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.bg1) // Make sure this icon exists
            .setContentTitle("Happy Calling!")
            .setContentText("Wish you a happy calling conversation 😊")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            notify(101, builder.build())
        }
    }
}
