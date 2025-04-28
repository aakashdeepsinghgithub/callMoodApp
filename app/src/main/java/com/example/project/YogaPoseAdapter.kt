package com.example.project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class YogaPoseAdapter(private val poseList: List<YogaPose>) :
    RecyclerView.Adapter<YogaPoseAdapter.PoseViewHolder>() {

    inner class PoseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.poseImage)
        val title: TextView = view.findViewById(R.id.poseTitle)
        val sanskrit: TextView = view.findViewById(R.id.poseSanskrit)
        val benefits: TextView = view.findViewById(R.id.poseBenefits)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pose_block, parent, false)
        return PoseViewHolder(view)
    }

    override fun onBindViewHolder(holder: PoseViewHolder, position: Int) {
        val pose = poseList[position]
        holder.image.setImageResource(pose.imageResId)
        holder.title.text = pose.title
        holder.sanskrit.text = pose.sanskritName
        holder.benefits.text = pose.benefits
    }

    override fun getItemCount() = poseList.size
}
