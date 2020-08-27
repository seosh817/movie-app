package io.github.slflfl12.data.model

data class TvData(
    val page: Int,
    var keywords: List<KeywordData>?,
    val reviews: List<ReviewData>?,
    val videos: List<VideoData>?,
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
): Data