package com.example.project

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.setPadding

class questions : AppCompatActivity() {

    private val questions = listOf(
        "I enjoy trying new things.",
        "I prefer planning things in advance.",
        "I get stressed out easily.",
        "I like working in a team.",
        "I am confident in my abilities.",
        "I often help others without expecting anything in return.",
        "I enjoy being the center of attention.",
        "I prefer quiet and peaceful environments.",
        "I stay calm even in difficult situations.",
        "I am open to feedback and criticism.",
        "I enjoy solving complex problems.",
        "I get excited about new challenges.",
        "I find it easy to make friends.",
        "I value honesty above all else.",
        "I often reflect on my emotions and thoughts."
    )

    private val radioGroups = mutableListOf<RadioGroup>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        val container = findViewById<LinearLayout>(R.id.questionContainer)

        for ((index, questionText) in questions.withIndex()) {
            val questionLayout = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(24)
                setBackgroundColor(Color.WHITE)
                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                params.setMargins(0, 0, 0, 24)
                layoutParams = params
                elevation = 4f
            }

            val textView = TextView(this).apply {
                text = "${index + 1}. $questionText"
                textSize = 16f
                setTextColor(Color.BLACK)
                setPadding(0, 0, 0, 8)
            }

            val radioGroup = RadioGroup(this).apply {
                orientation = RadioGroup.HORIZONTAL
                addView(createRadioButton("Disagree", 1))
                addView(createRadioButton("Agree", 2))
                addView(createRadioButton("Completely Agree", 3))
            }

            radioGroups.add(radioGroup)
            questionLayout.addView(textView)
            questionLayout.addView(radioGroup)
            container.addView(questionLayout)
        }

        val submitButton = Button(this).apply {
            text = "Submit"
            textSize = 18f
            setTextColor(Color.WHITE)
            setBackgroundColor(Color.parseColor("#6200EE"))
            setPadding(16)
            setOnClickListener { calculateScore(container) }
        }

        container.addView(submitButton)

        val homebtn = findViewById<ImageView>(R.id.homejao)
        homebtn.setOnClickListener {
            val intent = Intent(this, mainpage::class.java)
            startActivity(intent)
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
    }

    private fun createRadioButton(text: String, value: Int): RadioButton {
        return RadioButton(this).apply {
            this.text = text
            this.tag = value
        }
    }

    private fun calculateScore(container: LinearLayout) {
        var total = 0
        var answeredCount = 0

        for (group in radioGroups) {
            val selected = group.checkedRadioButtonId
            if (selected != -1) {
                val selectedButton = findViewById<RadioButton>(selected)
                val value = selectedButton.tag as Int
                total += value
                answeredCount++
            }
        }

        if (answeredCount < questions.size) {
            Toast.makeText(this, "Please answer all questions.", Toast.LENGTH_SHORT).show()
            return
        }

        val average = total.toFloat() / questions.size

        // Background color logic based on average
        val bgColor = when {
            average <= 1.5 -> "#FFCDD2" // Red-ish
            average <= 2.2 -> "#FFF9C4" // Yellow-ish
            else -> "#C8E6C9" // Green-ish
        }

        container.setBackgroundColor(Color.parseColor(bgColor))

        // Show average score
        Toast.makeText(this, "Your score: %.2f".format(average), Toast.LENGTH_LONG).show()

        // Send average score to mainpage activity
        val intent = Intent(this, mainpage::class.java)
        intent.putExtra("score", average)
        startActivity(intent)
        finish()

    }

}
