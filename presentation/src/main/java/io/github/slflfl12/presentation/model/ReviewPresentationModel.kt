package io.github.slflfl12.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReviewPresentationModel(
    val id:String,
    val author:String,
    val content:String,
    val url:String
): PresentationModel, Parcelable