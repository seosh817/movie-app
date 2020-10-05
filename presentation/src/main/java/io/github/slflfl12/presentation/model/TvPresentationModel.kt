package io.github.slflfl12.presentation.model

import android.os.Parcelable
import io.github.slflfl12.domain.model.KeywordModel
import io.github.slflfl12.domain.model.ReviewModel
import io.github.slflfl12.domain.model.VideoModel
import io.github.slflfl12.presentation.rxbus.RxBusEvent
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvPresentationModel(
    val page: Int,
    val keywords: List<KeywordPresentationModel>?,
    val reviews: List<ReviewPresentationModel>?,
    val videos: List<VideoPresentationModel>?,
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
) : PresentationModel, Parcelable, RxBusEvent