package io.github.slflfl12.data.model

class MovieData(
    var page: Int,
    var keywords: List<KeywordData>? = ArrayList(),
    var videos: List<VideoData>? = ArrayList(),
    var reviews: List<ReviewData>? = ArrayList(),
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,
    var favorite: Boolean = false
): Data