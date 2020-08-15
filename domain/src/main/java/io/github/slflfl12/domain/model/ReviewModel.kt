package io.github.slflfl12.domain.model

data class ReviewModel(
    val author: String,
    val content: String,
    val id: String,
    val url: String
): Model