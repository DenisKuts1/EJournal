package com.chnu.ejournal.fragments.schedule.days

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import android.widget.ToggleButton
import com.chnu.ejournal.R
import java.util.*

class DaysScheduleViewHolder(itemView: View, val context: Context, val listener: (View, Int) -> Unit) : RecyclerView.ViewHolder(itemView) {
    val button by lazy { itemView.findViewById<ToggleButton>(R.id.schedule_days_toggle_button) }
    val textField by lazy { itemView.findViewById<TextView>(R.id.schedule_days_text_field) }

    var itemPosition = -1

    fun observe(position: Int){
        val isOn = itemPosition == position
        button.isChecked = isOn
        if (isOn) {
            button.setTextColor(context.resources.getColor(R.color.schedule_day_text_enabled))
        } else {
            button.setTextColor(context.resources.getColor(R.color.schedule_day_text_disabled))
        }
    }


    fun bindItem(item: ScheduleDateEntity) {
        val calendar = Calendar.getInstance()
        calendar.time = item.date
        if (item.isOn) {
            button.setTextColor(context.resources.getColor(R.color.schedule_day_text_enabled))
        } else {
            button.setTextColor(context.resources.getColor(R.color.schedule_day_text_disabled))
        }

        button.setOnClickListener{
            listener(button, itemPosition)
        }

        val dayShort = context.resources.getStringArray(R.array.days_of_week_short)[calendar[Calendar.DAY_OF_WEEK] - 1]
        button.textOff = dayShort
        button.textOn = dayShort
        button.text = dayShort
        button.refreshDrawableState()
        val monthShort = context.resources.getStringArray(R.array.months_short)[calendar[Calendar.MONTH] - 1]
        val dayOfMonth = calendar[Calendar.DAY_OF_MONTH]
        textField.text = context.resources.getString(R.string.schedule_day_list_text, monthShort, dayOfMonth)
    }
}