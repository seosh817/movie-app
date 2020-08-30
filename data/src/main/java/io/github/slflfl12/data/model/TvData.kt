package io.github.slflfl12.data.model

data class TvData(
    val page: Int,
    val keywords: List<KeywordData>? = ArrayList(),
    val reviews: List<ReviewData>? = ArrayList(),
    val videos: List<VideoData>? = ArrayList(),
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
    val favorite: Boolean
): Data