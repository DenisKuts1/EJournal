package com.chnu.ejournal.fragments.subjects

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.chnu.ejournal.R

abstract class BaseSubjectViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract fun bindItem(item: SubjectsItem)
}

class HeaderSubjectsViewHolder(headerView: View): BaseSubjectViewHolder(headerView){

    val textField = headerView.findViewById<TextView>(R.id.subject_list_header_item_text)

    override fun bindItem(item: SubjectsItem) {
        textField.text = item.text
    }
}

class ItemSubjectsViewHolder(itemView: View): BaseSubjectViewHolder(itemView){

    val textField = itemView.findViewById<TextView>(R.id.subject_list_item_text)

    override fun bindItem(item: SubjectsItem) {
        textField.text = item.text
    }
}