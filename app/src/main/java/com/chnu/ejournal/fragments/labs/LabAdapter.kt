package com.chnu.ejournal.fragments.labs

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chnu.ejournal.R
import com.chnu.ejournal.entities.Student
import com.chnu.ejournal.entities.Subject

class LabAdapter(val shapeColor: Int) : RecyclerView.Adapter<LabViewHolder>() {

    val items = ArrayList<LabItem>()

    fun setItems(student: Student, subject: Subject){

    }

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): LabViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.student_item, parent, false)
        return LabViewHolder(view, shapeColor)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: LabViewHolder, position: Int) {
        holder.bindItem(items[position])
    }
}