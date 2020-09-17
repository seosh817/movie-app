package io.github.slflfl12.domain.model

data class MovieModel(
    val page: Int,
    var keywords: List<KeywordModel>?,
    var reviews: List<ReviewModel>?,
    var videos: List<VideoModel>?,
    val adult: Boolean?,
    val backdropPath: String?,
    val genreIds: List<Int>?,
    val id: Int,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String?,
    val video: Boolean?,
    val voteAverage: Float?,
    val voteCount: Int?,
    var favorite: Boolean
): Model