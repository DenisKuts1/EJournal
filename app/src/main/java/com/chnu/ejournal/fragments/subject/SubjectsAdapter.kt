package com.chnu.ejournal.fragments.subject

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chnu.ejournal.R
import com.chnu.ejournal.Subject
import com.chnu.ejournal.fragments.schedule.subjects.BaseScheduleViewHolder
import com.chnu.ejournal.fragments.schedule.subjects.ScheduleHeaderViewHolder
import com.chnu.ejournal.fragments.schedule.subjects.ScheduleItemType
import com.chnu.ejournal.fragments.schedule.subjects.ScheduleItemViewHolder
import java.lang.RuntimeException

class SubjectsAdapter : RecyclerView.Adapter<BaseSubjectViewHolder>() {

    val items = ArrayList<SubjectsItem>()

    fun setItems(subjects: ArrayList<Subject>) {
        items.clear()

        if (subjects.isEmpty()) return

        subjects.sortBy { subject -> subject.name }
        var lastHeaderChar = '0'
        subjects.forEach { subject ->
            val firstChar = subject.name[0]
            if(lastHeaderChar != firstChar){
                items += SubjectsItem(firstChar.toString(), SubjectsItemType.HEADER)
                lastHeaderChar = firstChar
            }
            items.add(SubjectsItem(subject.name, SubjectsItemType.ITEM))
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseSubjectViewHolder {
        return when(viewType){
            SubjectsItemType.HEADER.ordinal -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.subject_header_item, parent, false)
                HeaderSubjectsViewHolder(view)
            }
            SubjectsItemType.ITEM.ordinal -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.subject_header_item, parent, false)
                ItemSubjectsViewHolder(view)
            }
            else -> throw RuntimeException("Unknown type")
        }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: BaseSubjectViewHolder, position: Int) {
        holder.bindItem(items[position])
    }
}