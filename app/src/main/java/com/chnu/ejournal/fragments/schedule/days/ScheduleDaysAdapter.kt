package com.chnu.ejournal.fragments.schedule.days

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ToggleButton
import com.chnu.ejournal.R
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subjects.PublishSubject
import java.util.*
import kotlin.collections.ArrayList

class ScheduleDaysAdapter(val subjectsListener:(Date) -> Unit) : RecyclerView.Adapter<DaysScheduleViewHolder>() {

    private val items = ArrayList<ScheduleDateEntity>()

    fun setItems(days: ArrayList<Date>) {
        days.forEachIndexed { index, date ->
            items += if (index == 0) {
                ScheduleDateEntity(date, true)
            } else {
                ScheduleDateEntity(date, false)
            }
        }
    }

    val eventSubject = PublishSubject.create<Int>()

    /*val observable = Observable.create<Boolean> { subscriber ->
        subscriber.onNext(true)
        subscriber.onCompleted()
    }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    init {
        observable.subscribe(System.out::println)
    }*/

    val listener = { button: View, position: Int ->
        if (button is ToggleButton) {
            subjectsListener(items[position].date)
            eventSubject.onNext(position)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaysScheduleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.schedule_days_list_item, parent, false)
        val viewHolder = DaysScheduleViewHolder(view, parent.context, listener)
        eventSubject.subscribe { position -> viewHolder.observe(position) }
        return viewHolder
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: DaysScheduleViewHolder, position: Int) {
        holder.itemPosition = position
        if(position == 0) holder.observe(position)

        holder.bindItem(items[position])
    }
}