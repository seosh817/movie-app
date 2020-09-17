package io.github.slflfl12.domain.model

data class TvModel(
    val page: Int,
    var keywords: List<KeywordModel>?,
    var reviews: List<ReviewModel>?,
    var videos: List<VideoModel>?,
    val backdropPath: String?,
    val firstAirDate: String?,
    val genreIds: List<Int>?,
    val id: Int,
    val name: String,
    val originCountry: List<String>?,
    val originalLanguage: String?,
    val originalName: String?,
    val overview: String?,
    val popularity: Float,
    val posterPath: String?,
    val voteAverage: Float?,
    val voteCount: Int,
    var favorite: Boolean
): Model