package io.github.slflfl12.remote.model


import com.google.gson.annotations.SerializedName

data class PersonTvResponse(
    @SerializedName("cast")
    val personTv: List<Tv>,
    @SerializedName("id")
    val id: Int
)