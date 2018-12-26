package com.chnu.ejournal.fragments.subject

import android.content.res.ColorStateList
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.chnu.ejournal.R
import com.chnu.ejournal.entities.Student

class StudentViewHolder(itemView: View, val progressBarColor: Int): RecyclerView.ViewHolder(itemView) {
    val nameField = itemView.findViewById<TextView>(R.id.student_item_student_name)
    val poitsField = itemView.findViewById<TextView>(R.id.student_item_points)
    val progressBar = itemView.findViewById<ProgressBar>(R.id.student_item_progress_bar)


    fun bindItem(item: StudentItem) {
        nameField.text = item.name
        poitsField.text = item.points.toString()
        progressBar.progress = item.points
        progressBar.progressTintList = ColorStateList.valueOf(progressBarColor)
    }
}

data class StudentItem(val name: String, val points: Int)

