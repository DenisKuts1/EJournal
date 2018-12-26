package com.chnu.ejournal.fragments.subject

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chnu.ejournal.R
import com.chnu.ejournal.entities.Student
import com.chnu.ejournal.entities.Subject


class StudentsAdapter(val progressBarColor: Int) : RecyclerView.Adapter<StudentViewHolder>() {

    val items = ArrayList<StudentItem>()
    private lateinit var listener: (Int) -> Unit

    fun setListener(listener: (Int) -> Unit) {
        this.listener = listener
    }

    fun setUpItems(students: ArrayList<Student>, subject: Subject) {
        items.clear()
        students.forEach { student ->
            val points = subject.labs.sumBy { lab -> lab.points[student]!! }

            items.add(StudentItem(student, points))
            items.sortBy { it.student.name }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.student_item, parent, false)
        return StudentViewHolder(view, progressBarColor, listener)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bindItem(items[position])
    }
}