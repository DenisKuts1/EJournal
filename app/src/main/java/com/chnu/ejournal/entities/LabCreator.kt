package com.chnu.ejournal.entities

import com.chnu.ejournal.entities.Subject
import java.util.*

object LabCreator {
    fun createLabsForSubject(subject: Subject) {
        subject.labs.clear()
        (1..5).forEach { i ->
            subject.labs += Lab("Лаб роб $i", 5, LabType.LAB)
        }
        subject.labs += Lab("Контрольна 1", 10, LabType.TEST)
        (5..10).forEach { i ->
            subject.labs += Lab("Лаб роб $i", 5, LabType.LAB)
        }
        subject.labs += Lab("Контрольна 2", 10, LabType.TEST)
        subject.labs += Lab("Іспит", 30, LabType.EXAM)

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
                Student("Жалба Станіслав", 1),
                Student("Бахір Антоніна", 1),

                Student("Нікітін Олексій", 2),
                Student("Нікорич Василь", 2),
                Student("Оренчак Віталій", 2),
                Student("Ропчан Олег", 2),
                Student("Ткачук Назарій", 2),
                Student("Харена Максим", 2),
                Student("Череватов Микола", 2),
                Student("Чернеуцан Петро", 2),
                Student("Шух Ярослав", 2),
                Student("Ковцун Оксана", 2),
                Student("Калакайло Михайло", 2),
                Student("Боднарчук Артем",2),
                Student("Борейко Сергій",2),
                Student("Грищук Микола", 2)))
    }

    val subjects = arrayListOf(
            Subject("Мережі", defaultGroup1, Date(118, 11, 1, 9, 40), 14),
            Subject("Мат.аналіз", defaultGroup1, Date(118, 11, 1, 13, 0), 11),
            Subject("Криптографія", defaultGroup1, Date(118, 11, 1, 8, 20), 8),
            Subject("Програмування інтернет", defaultGroup1, Date(118, 11, 1, 9, 50), 10),
            Subject("Архітектура комп'ютера", defaultGroup1, Date(118, 11, 3, 9, 50), 9),
            Subject("Вимоги до ПЗ", defaultGroup1, Date(118, 11, 2, 8, 20), 13),
            Subject("Якийсь довгий предмет", defaultGroup1, Date(118, 11, 3, 8, 20), 16),
            Subject("Людино-машинна взаємодія", defaultGroup1, Date(118, 11, 4, 9, 50), 15),
            Subject("Штучний інтелект", defaultGroup1, Date(118, 11, 5, 8, 20), 17),
            Subject("Веб дизайн", defaultGroup1, Date(118, 11, 5, 9, 50), 18)).also {
        it.forEach { subject ->
            createLabsForSubject(subject)
        }
    }
}