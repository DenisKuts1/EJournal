package com.chnu.ejournal

import java.util.*

/**
 * data class entity of subject from the server
 * name - the name of a subject
 * group - the name of a group
 * time - year, month, day, hour and minute of the start of the subject
 * image - id of the background image of the subject
 */
data class Subject(val name: String, val group: String, val time: Date, val image: Int) {
}