package com.chnu.ejournal.fragments.subjects

enum class SubjectsItemType {
    HEADER,
    ITEM
}

data class SubjectsItem(val text: String, val type: SubjectsItemType)