package com.chnu.ejournal.fragments.subjects

import com.chnu.ejournal.entities.Subject

enum class SubjectsItemType {
    HEADER,
    ITEM
}
open class SubjectsItem(val type: SubjectsItemType)

class SubjectsHeaderItem(val name: String, type: SubjectsItemType): SubjectsItem(type)

class SubjectsItemBody(val subject: Subject, type: SubjectsItemType) : SubjectsItem(type)
