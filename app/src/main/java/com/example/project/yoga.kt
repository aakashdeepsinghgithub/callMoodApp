package com.example.project

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class yoga : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: YogaPoseAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_yoga)

        recyclerView = findViewById(R.id.yogaRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val poses = listOf(
            YogaPose("Chair Pose", "Utkatasana", R.drawable.pose1, "• Strengthens thighs\n• Tones core\n• Builds endurance"),
            YogaPose("Chair with Gaze Up", "Utkatasana Variation", R.drawable.pose2, "• Opens chest\n• Enhances breath control\n• Improves posture"),
            YogaPose("Chair with Prayer Hands", "Utkatasana Anjali Mudra", R.drawable.pose3, "• Enhances focus\n• Engages heart center\n• Strengthens legs"),
            YogaPose("Chair with Open Arm Twist", "Parivrtta Utkatasana", R.drawable.pose4, "• Detoxifies organs\n• Increases spinal flexibility\n• Boosts balance"),
            YogaPose("Awkward Pose", "Utkata Konasana", R.drawable.pose5, "• Strengthens hips and thighs\n• Opens chest and shoulders\n• Builds stamina"),
            YogaPose("Figure Four Pose", "Eka Pada Utkatasana", R.drawable.pose6, "• Opens hips\n• Improves ankle flexibility\n• Enhances balance"),
            YogaPose("Revolved Figure Four", "Parivrtta Eka Pada Utkatasana", R.drawable.pose7, "• Strengthens obliques\n• Opens spine\n• Deepens focus"),
            YogaPose("Revolved Figure Four with Arms Extended", "Advanced Variation", R.drawable.pose8, "• Enhances twist depth\n• Builds upper body strength\n• Improves flexibility"),
            YogaPose("Revolved Chair", "Parivrtta Utkatasana", R.drawable.pose9, "• Stimulates digestion\n• Improves spinal mobility\n• Boosts concentration"),
            YogaPose("Revolved Chair with Arms Extended", "Deep Parivrtta Utkatasana", R.drawable.pose10, "• Deepens twist\n• Opens shoulders\n• Improves endurance"),
            YogaPose("Bound Revolved Chair", "Baddha Parivrtta Utkatasana", R.drawable.pose11, "• Deep detox twist\n• Tones abdomen\n• Increases flexibility")
        )

        adapter = YogaPoseAdapter(poses)
        recyclerView.adapter = adapter


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
}
