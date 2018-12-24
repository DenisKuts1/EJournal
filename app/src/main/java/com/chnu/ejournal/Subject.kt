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
        return when(image){
            0-> context.resources.getDrawable(R.drawable.subject0)
            1-> context.resources.getDrawable(R.drawable.subject1)
            2-> context.resources.getDrawable(R.drawable.subject2)
            3-> context.resources.getDrawable(R.drawable.subject3)
            4-> context.resources.getDrawable(R.drawable.subject4)
            5-> context.resources.getDrawable(R.drawable.subject5)
            6-> context.resources.getDrawable(R.drawable.arch)
            7-> context.resources.getDrawable(R.drawable.arch2)
            8-> context.resources.getDrawable(R.drawable.crypto)
            9-> context.resources.getDrawable(R.drawable.arch4)
            10-> context.resources.getDrawable(R.drawable.code)
            11-> context.resources.getDrawable(R.drawable.math2)
            12-> context.resources.getDrawable(R.drawable.net)
            13-> context.resources.getDrawable(R.drawable.qc)
            14-> context.resources.getDrawable(R.drawable.net2)

            else-> context.resources.getDrawable(R.drawable.subject0)

        }
    }
}