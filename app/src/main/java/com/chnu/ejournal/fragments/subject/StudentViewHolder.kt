package com.chnu.ejournal.fragments.subject

import android.app.Activity
import android.content.Context
import android.graphics.drawable.ShapeDrawable
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.chnu.ejournal.R
import com.chnu.ejournal.Student
import com.chnu.ejournal.fragments.subjects.SubjectsItem

class StudentViewHolder(itemView: View, val progressBarColor: Int, val progressBarBackgroundColor: Int, val screenWidth: Int): RecyclerView.ViewHolder(itemView) {
    val nameField = itemView.findViewById<TextView>(R.id.student_item_student_name)
    val poitsField = itemView.findViewById<TextView>(R.id.student_item_points)
    val progressBarLeft = itemView.findViewById<View>(R.id.student_item_progress_line)
    val progressBarRight = itemView.findViewById<View>(R.id.student_item_background_progress_line)

    fun bindItem(item: Student) {
        nameField.text = item.name
        poitsField.text = item.points.toString()
        progressBarLeft.setBackgroundColor(progressBarColor)
        progressBarRight.setBackgroundColor(progressBarBackgroundColor)
        progressBarLeft.layoutParams.width = screenWidth / 100 * item.points
        progressBarRight.layoutParams.width = screenWidth / 100 * (100 - item.points)
    }
}

