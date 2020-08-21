package io.github.slflfl12.data.model

data class MovieData(
    val page: Int,
    val keywords: List<KeywordData> = ArrayList(),
    val reviews: List<ReviewData> = ArrayList(),
    val videos: List<VideoData> = ArrayList(),
    val adult: Boolean,
    val backdropPath: String,
    val genreIds: List<Int>,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
    val favorite: Boolean
): Data