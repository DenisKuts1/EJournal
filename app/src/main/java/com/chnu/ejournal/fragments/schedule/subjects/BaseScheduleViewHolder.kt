package com.chnu.ejournal.fragments.schedule.subjects

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class BaseScheduleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract fun bindItem(item: ScheduleItem)
}

