package io.github.slflfl12.presentation.model

import android.os.Parcelable
import io.github.slflfl12.domain.model.Model
import io.github.slflfl12.domain.model.PersonDetailModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PersonPresentationModel(
    val page: Int,
    val adult: Boolean,
    var personDetailPresentationModel: PersonDetailPresentationModel?,
    val gender: Int,
    val id: Int,
    val name: String,
    val popularity: Float?,
    val profilePath: String?
) : PresentationModel, Parcelable