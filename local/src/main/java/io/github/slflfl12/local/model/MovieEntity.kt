package io.github.slflfl12.local.model

import androidx.room.PrimaryKey
import androidx.room.Entity
import io.github.slflfl12.data.model.KeywordData
import io.github.slflfl12.data.model.ReviewData
import io.github.slflfl12.data.model.VideoData


@Entity(tableName = "movie")
data class MovieEntity(
    var page: Int,
    var keywords: List<KeywordEntity>,
    var videos: List<VideoEntity>,
    var reviews: List<ReviewEntity>,
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    @PrimaryKey
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
) : io.github.slflfl12.local.model.Entity