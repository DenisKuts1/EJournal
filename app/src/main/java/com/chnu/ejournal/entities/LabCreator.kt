package com.chnu.ejournal.entities

import java.util.*
import kotlin.collections.ArrayList

object LabCreator {

    val lessons = ArrayList<LessonDTO>()
    var week = -1
    val groups = ArrayList<Group>()
    val subjects = ArrayList<Subject>()

    var newPoint = 0.0
    var wasNewPoint = false

    fun subjectFromLesson(lesson: LessonDTO): Subject{
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        val nowDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1
        val dayOfWeek = lesson.day.ordinal + 1 + when (lesson.lessonPlacement) {
            LessonPlacement.EVERY_FIRST_WEEK -> {
                if (LabCreator.week == 1) {
                    0
                } else {
                    7
                }
            }
            LessonPlacement.EVERY_SECONDARY_WEEK -> {
                if (LabCreator.week == 2) {
                    0
                } else {
                    7
                }
            }
            LessonPlacement.EVERY_WEEK -> {
                0
            }
        }
        var difference = dayOfWeek - nowDayOfWeek
        if(difference < 0) difference += 7
        calendar.add(Calendar.DATE, difference)
        return Subject(lesson.lessonName, getGroupById(lesson.groupId), calendar.time, lesson.image.toInt(), lesson.lessonNumber)
    }

    fun getGroupById(id: Long) = groups.first { it.id == id }

    /*fun createLabsForSubject(subject: Subject) {
        subject.labs.clear()
        (1..10).forEach { i ->
            subject.labs += Lab("Lab$i", 5, LabType.LAB)
        }
        subject.labs += Lab("Test1", 10, LabType.TEST)
        subject.labs += Lab("Test2", 10, LabType.TEST)
        subject.labs += Lab("Exam", 30, LabType.EXAM)

        val random = Random()
        subject.labs.forEach { lab ->
            subject.group.students.forEach { student ->
                lab.points += Pair(student, random.nextInt(lab.maxMark + 1))
            }
        }
    }*/

    /*val subjects = arrayListOf(
            Subject("Networking", defaultGroup1, Date(118, 11, 1, 9, 40), 14, 1),
            Subject("Math analysis", defaultGroup1, Date(118, 11, 1, 13, 0), 11, 2),
            Subject("Cryptography", defaultGroup1, Date(118, 11, 1, 8, 20), 8, 3),
            Subject("Coding with Mironiv", defaultGroup1, Date(118, 11, 1, 9, 50), 10, 4),
            Subject("Computer architecture", defaultGroup1, Date(118, 11, 3, 9, 50), 9, 1),
            Subject("Quality assurance", defaultGroup1, Date(118, 11, 2, 8, 20), 13, 2),
            Subject("Computer architecture", defaultGroup1, Date(118, 11, 3, 8, 20), 8, 3),
            Subject("Computer architecture", defaultGroup1, Date(118, 11, 4, 9, 50), 8, 4),
            Subject("Computer architecture", defaultGroup1, Date(118, 11, 5, 8, 20), 8, 5),
            Subject("Computer architecture", defaultGroup1, Date(118, 11, 5, 9, 50), 8, 6)).also {
        it.forEach { subject ->
            createLabsForSubject(subject)
        }
    }*/
}