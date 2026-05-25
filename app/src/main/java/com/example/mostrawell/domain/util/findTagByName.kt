package com.example.mostrawell.domain.util

import com.example.mostrawell.domain.entity.tag.AgeTag
import com.example.mostrawell.domain.entity.tag.EntertainmentTag
import com.example.mostrawell.domain.entity.tag.LocationTag
import com.example.mostrawell.domain.entity.tag.Tag

fun findTagByName(originalName: String): Tag? {
    var tag: Tag? = EntertainmentTag.entries.firstOrNull { it.originalName == originalName }
    if (tag != null) {
        return tag
    }
    tag = LocationTag.entries.firstOrNull { it.originalName == originalName }
    if (tag != null) {
        return tag
    }
    tag = AgeTag.entries.firstOrNull { it.originalName == originalName }
    return tag
}