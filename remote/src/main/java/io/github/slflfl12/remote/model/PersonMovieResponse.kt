package io.github.slflfl12.remote.model


import com.google.gson.annotations.SerializedName

data class PersonMovieResponse(
    @SerializedName("cast")
    val personMovie: List<Movie>,
    @SerializedName("id")
    val id: Int
)