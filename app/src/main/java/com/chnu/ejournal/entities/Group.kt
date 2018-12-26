package com.chnu.ejournal.entities

class Group(val id: Long, val number: String) {
    val students = ArrayList<Student>()
}