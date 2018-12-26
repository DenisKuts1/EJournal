package com.chnu.ejournal.entities

import android.content.Context
import android.graphics.drawable.Drawable
import com.chnu.ejournal.R

data class LessonDTO(val lessonId: Long, val groupId: Long, val day: Days, val lessonNumber: Int,
                val lessonPlacement: LessonPlacement, val lessonName: String, val subgroup: String, val image: Long ) {
}