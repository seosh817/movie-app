package io.github.slflfl12.presentation.model

data class ReviewPresentationModel(
    val id:String,
    val author:String,
    val content:String,
    val url:String
): PresentationModel