package com.chnu.ejournal.fragments.subject

import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.ShapeDrawable
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.chnu.ejournal.R
import com.chnu.ejournal.Student
import com.chnu.ejournal.fragments.subjects.SubjectsItem

class StudentViewHolder(itemView: View, val progressBarColor: Int, val screenWidth: Int): RecyclerView.ViewHolder(itemView) {
    val nameField = itemView.findViewById<TextView>(R.id.student_item_student_name)
    val poitsField = itemView.findViewById<TextView>(R.id.student_item_points)
    val progressBar = itemView.findViewById<ProgressBar>(R.id.student_item_progress_bar)


    fun bindItem(item: Student) {
        nameField.text = item.name
        poitsField.text = item.points.toString()
        progressBar.progress = item.points
        progressBar.progressTintList = ColorStateList.valueOf(progressBarColor)
    }
}

