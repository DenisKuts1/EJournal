package com.chnu.ejournal

import android.content.Context
import android.graphics.drawable.Drawable
import java.util.*

/**
 * data class entity of subject from the server
 * name - the name of a subject
 * group - the name of a group
 * time - year, month, day, hour and minute of the start of the subject
 * image - id of the background image of the subject
 */
data class Subject(val context: Context, val name: String, val group: String, val time: Date, val image: Int) {
    fun getImage(): Drawable {
        val array = context.resources.obtainTypedArray(R.array.subject_images)
        val drawable = array.getDrawable(image)
        array.recycle()
        return drawable!!
    }

    fun getPrimaryImageColor(): Int{
        val array = context.resources.obtainTypedArray(R.array.primary_image_colors)
        val drawable = array.getColor(image, -1)
        array.recycle()
        return drawable
    }

    fun getSecondaryImageColor(): Int{
        val array = context.resources.obtainTypedArray(R.array.secondary_image_colors)
        val drawable = array.getColor(image, -1)
        array.recycle()
        return drawable
    }
}