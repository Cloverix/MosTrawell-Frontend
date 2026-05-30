package com.example.mostrawell.domain.entity.tag

enum class AgeTag: Tag {
    FOR_KIDS,
    ADULTS_ONLY;

    override val originalName: String = name

    override fun getFormattedName(): String {
        return name
            .replace("_", " ")
            .lowercase()
            .replaceFirstChar { it.uppercase() }
    }
}