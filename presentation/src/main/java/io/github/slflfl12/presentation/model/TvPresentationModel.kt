package io.github.slflfl12.presentation.model

import io.github.slflfl12.domain.model.KeywordModel
import io.github.slflfl12.domain.model.ReviewModel
import io.github.slflfl12.domain.model.VideoModel

data class TvPresentationModel(
    val page: Int,
    var keywords: List<KeywordPresentationModel>?,
    val reviews: List<ReviewPresentationModel>?,
    val videos: List<VideoPresentationModel>?,
    val backdropPath: String?,
    val firstAirDate: String?,
    val genreIds: List<Int>?,
    val id: Int?,
    val name: String,
    val originCountry: List<String>?,
    val originalLanguage: String?,
    val originalName: String?,
    val overview: String?,
    val popularity: Float,
    val posterPath: String?,
    val voteAverage: Double,
    val voteCount: Int,
    val favorite: Boolean
): PresentationModel