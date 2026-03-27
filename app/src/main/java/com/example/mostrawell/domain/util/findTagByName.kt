package com.example.mostrawell.domain.util

import com.example.mostrawell.domain.entity.tag.AgeTag
import com.example.mostrawell.domain.entity.tag.EntertainmentTag
import com.example.mostrawell.domain.entity.tag.LocationTag
import com.example.mostrawell.domain.entity.tag.Tag

fun findTagByName(tagName: String): Tag? {
    var tag: Tag? = EntertainmentTag.entries.firstOrNull { it.name == tagName }
    if (tag != null) {
        return tag
    }
    tag = LocationTag.entries.firstOrNull { it.name == tagName }
    if (tag != null) {
        return tag
    }
    tag = AgeTag.entries.firstOrNull { it.name == tagName }
    return tag
}