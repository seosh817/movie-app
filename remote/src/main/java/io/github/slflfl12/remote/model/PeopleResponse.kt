package io.github.slflfl12.remote.model


import com.google.gson.annotations.SerializedName

data class PeopleResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val people: List<Person>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)