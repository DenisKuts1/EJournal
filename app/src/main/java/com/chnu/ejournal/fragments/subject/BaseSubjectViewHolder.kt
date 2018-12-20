package com.chnu.ejournal.fragments.subject

import android.support.v7.widget.RecyclerView
import android.view.View
import com.chnu.ejournal.fragments.schedule.subjects.ScheduleItem

open abstract class BaseSubjectViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract fun bindItem(item: ScheduleItem)
}

class HeaderSchedulaViewHolder(itemView: View): BaseSubjectViewHolder(itemView){

    override fun bindItem(item: ScheduleItem) {

    }
}