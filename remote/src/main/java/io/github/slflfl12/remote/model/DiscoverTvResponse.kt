package io.github.slflfl12.remote.model


import com.google.gson.annotations.SerializedName

data class DiscoverTvResponse(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val discoverTvs: List<Tv>,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)