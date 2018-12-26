package com.chnu.ejournal.fragments.labs

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.chnu.ejournal.R

class LabViewHolder(itemView: View, val shapeColor: Int) : RecyclerView.ViewHolder(itemView) {
    val nameField = itemView.findViewById<TextView>(R.id.lab_item_name)
    val poitsField = itemView.findViewById<TextView>(R.id.lab_item_points)
    val layout = itemView.findViewById<RelativeLayout>(R.id.lab_item_layout)


    fun bindItem(item: LabItem) {
        nameField.text = item.name
        poitsField.text = "${item.point}/${item.maxPoint}"
        // TODO magic with shape for layout
    }
}

class LabItem(val name: String, val maxPoint: Int, val point: Int)