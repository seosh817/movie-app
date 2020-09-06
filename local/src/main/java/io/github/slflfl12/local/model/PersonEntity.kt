package io.github.slflfl12.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "people")
data class PersonEntity(
    val page: Int,
    val adult: Boolean,
    @Embedded(prefix = "detail_")
    var personDetailEntity: PersonDetailEntity?,
    val gender: Int,
    @PrimaryKey
    val id: Int,
    val name: String,
    val popularity: Float?,
    val profilePath: String?
): io.github.slflfl12.local.model.Entity