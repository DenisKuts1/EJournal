package com.chnu.ejournal.fragments.schedule.subjects

import android.content.Context
import android.graphics.drawable.Drawable
import com.chnu.ejournal.R
import com.chnu.ejournal.Subject
import java.util.*

enum class ScheduleItemType {
    HEADER,
    ITEM
}

interface ScheduleItem {
    val type: ScheduleItemType
}

class ScheduleHeader(date: Date) : ScheduleItem {
    override val type = ScheduleItemType.HEADER
    val calendar = Calendar.getInstance()

    init {
        calendar.time = date
    }

    fun equalOrAfter(date: Date): Boolean{
        val thisDay = calendar.get(Calendar.DAY_OF_YEAR)
        val newCalendar = Calendar.getInstance()
        newCalendar.time = date
        val comparedDay = newCalendar.get(Calendar.DAY_OF_YEAR)
        return thisDay >= comparedDay
    }

    fun getDate(context: Context): String {
        //val year = calendar.get(Calendar.YEAR)
        val dayNumber = calendar.get(Calendar.DAY_OF_WEEK)
        val day = context.resources.getStringArray(R.array.days_of_week)[dayNumber - 1]

        val monthNumber = calendar.get(Calendar.MONTH)
        val month = context.resources.getStringArray(R.array.months)[monthNumber]

        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        //println("$year:$monthNumber:$dayOfMonth ($dayNumber) - $month:$day")
        return if(dayOfMonth > 9) "$day, $month $dayOfMonth" else "$day, $month 0$dayOfMonth"
    }
}

class ScheduleSubject(private val subject: Subject, val context: Context) : ScheduleItem {
    override val type = ScheduleItemType.ITEM

    fun getName() = subject.name
    fun getGroup() = subject.group
    fun getTime(): String {
        val calendar = Calendar.getInstance()
        calendar.time = subject.time
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        return if (hour > 9) {
            if (minute > 9) {
                "$hour:$minute"
            } else {
                "$hour:0$minute"
            }
        } else {
            if (minute > 9) {
                "0$hour:$minute"
            } else {
                "0$hour:0$minute"
            }
        }
    }
    fun getImage():Drawable{
        return when(subject.image){
            0-> context.resources.getDrawable(R.drawable.subject0)
            1-> context.resources.getDrawable(R.drawable.subject1)
            2-> context.resources.getDrawable(R.drawable.subject2)
            3-> context.resources.getDrawable(R.drawable.subject3)
            4-> context.resources.getDrawable(R.drawable.subject4)
            5-> context.resources.getDrawable(R.drawable.subject5)
            else-> context.resources.getDrawable(R.drawable.subject0)

        }
    }
}

