package com.chnu.ejournal.fragments.subject

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chnu.ejournal.R
import com.chnu.ejournal.Student


class StudentsAdapter(val progressBarColor: Int, val screenWidth: Int) : RecyclerView.Adapter<StudentViewHolder>()  {

    val items = ArrayList<Student>()
    fun setItems(students: ArrayList<Student>){
        items.clear()
        items.addAll(students)
        items.sortBy { it.name }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.student_item, parent, false)
        return StudentViewHolder(view, progressBarColor, screenWidth)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bindItem(items[position])
    }
}