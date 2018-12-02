package com.chnu.ejournal.fragments.schedule.subjects

import android.content.Context
import android.view.View
import android.widget.TextView
import com.chnu.ejournal.R

class ScheduleHeaderViewHolder(headerView: View, val context: Context): BaseScheduleViewHolder(headerView) {

    private val dateField = headerView.findViewById<TextView>(R.id.schedule_header_text_field)


    override fun bindItem(item: ScheduleItem) {
        val header = item as ScheduleHeader
        dateField.text = header.getDate(context)
    }
}