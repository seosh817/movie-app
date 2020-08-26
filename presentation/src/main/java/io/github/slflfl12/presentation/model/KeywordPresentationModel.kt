package io.github.slflfl12.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class KeywordPresentationModel(
    val id: Int,
    val name:String
): PresentationModel, Parcelable