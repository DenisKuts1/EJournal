package com.chnu.ejournal.entities

import android.content.Context
import android.graphics.drawable.Drawable
import com.chnu.ejournal.R
import java.util.*
import kotlin.collections.ArrayList

/**
 * data class entity of subject from the server
 * name - the name of a subject
 * group - the name of a group
 * time - year, month, day, hour and minute of the start of the subject
 * image - id of the background image of the subject
 */
data class Subject(val name: String, val group: Group, val time: Date, val image: Int, val lessonNumber: Int): Comparable<Subject> {
    val labs = ArrayList<Lab>()

    fun getImage(context: Context): Drawable {
        val array = context.resources.obtainTypedArray(R.array.subject_images)
        val drawable = array.getDrawable(image)
        array.recycle()
        return drawable!!
    }

    fun getPrimaryImageColor(context: Context): Int {
        val array = context.resources.obtainTypedArray(R.array.primary_image_colors)
        val drawable = array.getColor(image, -1)
        array.recycle()
        return drawable
    }

    fun getSecondaryImageColor(context: Context): Int {
        val array = context.resources.obtainTypedArray(R.array.secondary_image_colors)
        val drawable = array.getColor(image, -1)
        array.recycle()
        return drawable
    }

    override operator fun compareTo(other: Subject): Int {
        val calendar = Calendar.getInstance()
        calendar.time = time

        val otherCalendar = Calendar.getInstance()
        otherCalendar.time = other.time
        val day = calendar.get(Calendar.DAY_OF_YEAR)
        val otherDay = otherCalendar.get(Calendar.DAY_OF_YEAR)
        return when {
            day > otherDay -> 1
            day < otherDay -> -1
            else -> {
                when {
                    lessonNumber > other.lessonNumber -> 1
                    lessonNumber < other.lessonNumber -> -1
                    else -> 0
                }
            }

        }
    }
}