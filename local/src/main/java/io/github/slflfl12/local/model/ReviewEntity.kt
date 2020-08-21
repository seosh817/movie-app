package io.github.slflfl12.local.model

data class ReviewEntity(
    val author: String,
    val content: String,
    val id: String,
    val url: String
): Entity