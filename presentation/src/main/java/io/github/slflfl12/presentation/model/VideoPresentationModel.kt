package io.github.slflfl12.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VideoPresentationModel(
    val id:String,
    val key:String,
    val name:String,
    val site:String,
    val size:Int,
    val type:String
): PresentationModel, Parcelable