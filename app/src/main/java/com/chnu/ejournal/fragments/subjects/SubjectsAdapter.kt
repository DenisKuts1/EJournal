package com.chnu.ejournal.fragments.subjects

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chnu.ejournal.R
import com.chnu.ejournal.entities.LessonDTO
import com.chnu.ejournal.entities.Subject
import java.lang.RuntimeException

class SubjectsAdapter() : RecyclerView.Adapter<BaseSubjectViewHolder>() {

    val items = ArrayList<SubjectsItem>()

    private lateinit var listener: (Int) -> Unit

    fun setListener(listener: (Int) -> Unit) {
        this.listener = listener
    }

    fun getItem(position: Int) = items[position]

    fun setItems(subjects: ArrayList<Subject>) {
        items.clear()

        if (subjects.isEmpty()) return

        subjects.sortBy { subject -> subject.name }
        var lastHeaderChar = '0'
        subjects.forEach { subject ->
            val firstChar = subject.name[0]
            if(lastHeaderChar != firstChar){
                items += SubjectsHeaderItem(firstChar.toString(), SubjectsItemType.HEADER)
                lastHeaderChar = firstChar
            }
            items.add(SubjectsItemBody(subject, SubjectsItemType.ITEM))
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseSubjectViewHolder {
        return when(viewType){
            SubjectsItemType.HEADER.ordinal -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.subject_header_item, parent, false)
                HeaderSubjectsViewHolder(view)
            }
            SubjectsItemType.ITEM.ordinal -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.subject_item, parent, false)
                ItemSubjectsViewHolder(view, listener)
            }
            else -> throw RuntimeException("Unknown type")
        }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: BaseSubjectViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemViewType(position: Int) = items[position].type.ordinal
}