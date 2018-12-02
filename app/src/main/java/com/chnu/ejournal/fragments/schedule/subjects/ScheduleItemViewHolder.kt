package com.chnu.ejournal.fragments.schedule.subjects

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.chnu.ejournal.R

class ScheduleItemViewHolder(subjectView: View, val context: Context, val listener: (Int) -> Unit): BaseScheduleViewHolder(subjectView) {

    private val timeField = subjectView.findViewById<TextView>(R.id.schedule_subject_time)
    private val nameField = subjectView.findViewById<TextView>(R.id.schedule_subject_name)
    private val groupField = subjectView.findViewById<TextView>(R.id.schedule_subject_group)
    private val image = subjectView.findViewById<ImageView>(R.id.schedule_subject_item_image)

    init {
        //val layout = subjectView.findViewById<RelativeLayout>(R.id.schedule_item_layout)
        subjectView.setOnClickListener {
            if(adapterPosition != RecyclerView.NO_POSITION){
                listener(adapterPosition)
            }
        }
    }

    override fun bindItem(item: ScheduleItem) {
        val subject = item as ScheduleSubject
        nameField.text = subject.getName()
        groupField.text = subject.getGroup()
        timeField.text = subject.getTime()
        val background = GradientDrawable()
        image.setImageDrawable(subject.getImage())
        //background.cornerRadius = 10.0f

    }
}