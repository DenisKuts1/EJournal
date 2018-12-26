package com.chnu.ejournal.fragments.schedule.subjects

import android.content.Context
import android.graphics.drawable.Drawable
import com.chnu.ejournal.R
import com.chnu.ejournal.entities.LabCreator
import com.chnu.ejournal.entities.LessonDTO
import com.chnu.ejournal.entities.Subject
import java.util.*

enum class ScheduleItemType {
    HEADER,
    ITEM
}

interface ScheduleItem {
    val type: ScheduleItemType
}

class ScheduleHeader(val date: Date) : ScheduleItem {
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

class ScheduleSubject(val subject: Subject, val context: Context) : ScheduleItem {
    override val type = ScheduleItemType.ITEM

    fun getName() = subject.name
    fun getGroup() = subject.group
    fun getTime(): String {
        return when(subject.lessonNumber){
            1 -> "08:20"
            2 -> "09:50"
            3 -> "11:30"
            4 -> "13:00"
            5 -> "14:40"
            6 -> "16:10"
            else -> "08:40"
        }
    }
    fun getImage():Drawable{
        return subject.getImage(context)
    }
}

