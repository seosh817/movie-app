package io.github.slflfl12.domain.model

data class PersonModel(
    val page: Int,
    val adult: Boolean,
    var personDetailModel: PersonDetailModel?,
    val gender: Int,
    val id: Int,
    val name: String,
    val popularity: Float?,
    val profilePath: String?
): Model