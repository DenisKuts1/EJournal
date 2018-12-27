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
        textField.text = (item as SubjectsHeaderItem).name
    }
}

class ItemSubjectsViewHolder(itemView: View, val listener: (Int) -> Unit): BaseSubjectViewHolder(itemView){

    val textField = itemView.findViewById<TextView>(R.id.subject_list_item_text)

    init {
        itemView.setOnClickListener{
            if(adapterPosition != RecyclerView.NO_POSITION){
                listener(adapterPosition)
            }
        }
    }

    override fun bindItem(item: SubjectsItem) {
        textField.text = (item as SubjectsItemBody).subject.name
    }
}