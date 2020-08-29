package io.github.slflfl12.local.model

import androidx.room.PrimaryKey
import androidx.room.Entity

@Entity(tableName = "tv")
data class TvEntity(
    val page: Int,
    var keywords: List<KeywordEntity>?,
    val reviews: List<ReviewEntity>?,
    val videos: List<VideoEntity>?,
    val backdropPath: String?,
    val firstAirDate: String?,
    val genreIds: List<Int>?,
    @PrimaryKey
    val id: Int,
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
): io.github.slflfl12.local.model.Entity