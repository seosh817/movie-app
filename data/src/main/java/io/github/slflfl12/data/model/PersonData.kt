package io.github.slflfl12.data.model

data class PersonData(
    val page: Int,
    val adult: Boolean,
    var personDetailData: PersonDetailData? = null,
    val gender: Int,
    val id: Int,
    val name: String,
    val popularity: Float?,
    val profilePath: String?
): Data