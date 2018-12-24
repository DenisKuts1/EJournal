package com.chnu.ejournal.fragments.subject

enum class SubjectsItemType {
    HEADER,
    ITEM
}

data class SubjectsItem(val text: String, val type: SubjectsItemType)