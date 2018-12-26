package com.chnu.ejournal.entities

class Lab(val id: Long, val name: String, val maxMark: Double, val type: TaskType) {
    lateinit var points: HashMap<Student, Int>
}