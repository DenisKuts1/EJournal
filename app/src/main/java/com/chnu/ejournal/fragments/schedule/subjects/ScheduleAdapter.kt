package com.chnu.ejournal.fragments.schedule.subjects

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chnu.ejournal.R
import com.chnu.ejournal.Subject
import java.lang.RuntimeException
import java.util.*

class ScheduleAdapter(val context: Context) : RecyclerView.Adapter<BaseScheduleViewHolder>() {
    private val items = ArrayList<ScheduleItem>()
    private lateinit var listener: (Int) -> Unit

    fun setListener(listener: (Int) -> Unit) {
        this.listener = listener
    }

    fun setItems(subjects: ArrayList<Subject>){
        items.clear()

        if(subjects.isEmpty()) return

        subjects.sortBy { subject -> subject.time }
        var lastDay = -1
        val calendar = Calendar.getInstance()
        subjects.forEach {subject ->
            calendar.time = subject.time
            val day = calendar.get(Calendar.DAY_OF_YEAR)
            if(day > lastDay){
                items.add(ScheduleHeader(calendar.time))
                lastDay = day
            }
            items.add(ScheduleSubject(subject, context))
        }
    }

    fun getPosition(date: Date): Int{
        val item = items.filter { item -> item is ScheduleHeader }.firstOrNull { item -> (item as ScheduleHeader).equalOrAfter(date) }
        return if(item != null){
            items.indexOf(item)
        } else {
            items.size - 1
        }
    }

    fun getItem(position: Int) = items[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseScheduleViewHolder {
        when(viewType){
            ScheduleItemType.HEADER.ordinal -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.schedule_header_item, parent, false)
                return ScheduleHeaderViewHolder(view, parent.context)
            }
            ScheduleItemType.ITEM.ordinal -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.schedule_subject_item, parent, false)
                return ScheduleItemViewHolder(view, parent.context, listener)
            }
            else -> throw RuntimeException("Unknown type")
        }
    }

    override fun getItemViewType(position: Int) = items[position].type.ordinal

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: BaseScheduleViewHolder, position: Int) {
        holder.bindItem(items[position])
    }
}