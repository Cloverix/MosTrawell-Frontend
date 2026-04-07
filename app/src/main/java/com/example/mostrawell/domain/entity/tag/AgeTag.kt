package com.example.mostrawell.domain.entity.tag

enum class AgeTag: Tag {
    FOR_KIDS,
    ADULTS_ONLY;

    override fun getName(): String {
        return name
            .replace("_", " ")
            .lowercase()
            .replaceFirstChar { it.uppercase() }
    }
}