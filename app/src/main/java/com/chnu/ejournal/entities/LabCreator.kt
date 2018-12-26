package com.chnu.ejournal.entities

import com.chnu.ejournal.entities.Subject
import java.util.*

object LabCreator {
    fun createLabsForSubject(subject: Subject) {
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
                lab.points += Pair(student, random.nextInt(lab.maxPoints + 1))
            }
        }
    }


    val defaultGroup1 = Group("543м").also {
        it.students.addAll(arrayListOf(Student("Куц Денис", 1),
                Student("Ніколаєвич Владислав", 1),
                Student("Чижевський Василь", 1),
                Student("Великий Князь Архо Владислав", 2),
                Student("Гаврилиця Федір", 1),
                Student("Лабінськой Віктор", 1),
                Student("Лисенко Юлія", 1),
                Student("Лазоряк Олександр", 1),
                Student("Твардовський Роман", 1),
                Student("Слободяник Олексій", 1),
                Student("Бахір Антоніна", 1),

                Student("Куц Денис1", 2),
                Student("Ніколаєвич Владислав1", 2),
                Student("Чижевський Василь1", 2),
                Student("Великий Князь Архо Владислав1", 2),
                Student("Гаврилиця Федір1", 2),
                Student("Лабінськой Віктор1", 2),
                Student("Лисенко Юлія1", 2),
                Student("Лазоряк Олександр1", 2),
                Student("Твардовський Роман1", 2),
                Student("Слободяник Олексій1", 2),
                Student("Бахір Антоніна1", 2)))
    }

    val subjects = arrayListOf(
            Subject("Networking", defaultGroup1, Date(118, 11, 1, 9, 40), 14),
            Subject("Math analysis", defaultGroup1, Date(118, 11, 1, 13, 0), 11),
            Subject("Cryptography", defaultGroup1, Date(118, 11, 1, 8, 20), 8),
            Subject("Coding with Mironiv", defaultGroup1, Date(118, 11, 1, 9, 50), 10),
            Subject("Computer architecture", defaultGroup1, Date(118, 11, 3, 9, 50), 9),
            Subject("Quality assurance", defaultGroup1, Date(118, 11, 2, 8, 20), 13),
            Subject("Computer architecture", defaultGroup1, Date(118, 11, 3, 8, 20), 8),
            Subject("Computer architecture", defaultGroup1, Date(118, 11, 4, 9, 50), 8),
            Subject("Computer architecture", defaultGroup1, Date(118, 11, 5, 8, 20), 8),
            Subject("Computer architecture", defaultGroup1, Date(118, 11, 5, 9, 50), 8)).also {
        it.forEach { subject ->
            createLabsForSubject(subject)
        }
    }
}