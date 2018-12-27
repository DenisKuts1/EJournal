package com.chnu.ejournal.fragments.labs

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.chnu.ejournal.R
import com.chnu.ejournal.entities.Lab

class LabViewHolder(itemView: View, val shapeColor: Int, val listener: (Int) -> Unit) : RecyclerView.ViewHolder(itemView) {
    val nameField = itemView.findViewById<TextView>(R.id.lab_item_name)
    val poitsField = itemView.findViewById<TextView>(R.id.lab_item_points)
    val layout = itemView.findViewById<RelativeLayout>(R.id.lab_item_layout)

    init {
        //val layout = subjectView.findViewById<RelativeLayout>(R.id.schedule_item_layout)
        itemView.setOnClickListener {
            if(adapterPosition != RecyclerView.NO_POSITION){
                listener(adapterPosition)
            }
        }
    }


    fun bindItem(item: LabItem) {
        nameField.text = item.lab.name
        poitsField.text = "${item.points}/${item.lab.maxMark}"
        // TODO magic with shape for layout
    }
}

class LabItem(val lab: Lab, val points: Int)