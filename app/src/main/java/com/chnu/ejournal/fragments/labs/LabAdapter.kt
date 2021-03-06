package com.chnu.ejournal.fragments.labs

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chnu.ejournal.R
import com.chnu.ejournal.entities.Student
import com.chnu.ejournal.entities.Subject
import kotlin.math.roundToInt

class LabAdapter(val shapeColor: Int) : RecyclerView.Adapter<LabViewHolder>() {

    val items = ArrayList<LabItem>()

    private lateinit var listener: (Int) -> Unit

    fun setListener(listener: (Int) -> Unit) {
        this.listener = listener
    }

    fun setItems(student: Student, subject: Subject){
        subject.labs.forEach { lab ->
            val point = lab.points[student]!!
            items += LabItem(lab,  point)
        }
    }

    fun getItems(position: Int) = items[position]

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): LabViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lab_item, parent, false)
        return LabViewHolder(view, shapeColor, listener)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: LabViewHolder, position: Int) {
        holder.bindItem(items[position])
    }


}