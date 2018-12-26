package com.chnu.ejournal.entities

class Lab(val name: String, val maxPoints: Int, val type: LabType) {
    val points = HashMap<Student, Int>()
}

enum class LabType{
    LAB, TEST, EXAM
}