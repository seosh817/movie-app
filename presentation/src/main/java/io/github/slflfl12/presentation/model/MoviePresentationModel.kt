package io.github.slflfl12.presentation.model

import android.os.Parcelable
import io.github.slflfl12.presentation.rxbus.RxBusEvent
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MoviePresentationModel(
    val page: Int,
    val keywords: List<KeywordPresentationModel>?,
    val reviews: List<ReviewPresentationModel>?,
    val videos: List<VideoPresentationModel>?,
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
    val favorite: Boolean
) : PresentationModel, Parcelable, RxBusEvent