package io.github.slflfl12.remote.model


import com.google.gson.annotations.SerializedName

data class Person(
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("gender")
    val gender: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("popularity")
    val popularity: Float?,
    @SerializedName("profile_path")
    val profilePath: String?
): Response