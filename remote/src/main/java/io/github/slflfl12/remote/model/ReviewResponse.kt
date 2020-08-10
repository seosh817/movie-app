package io.github.slflfl12.remote.model


import com.google.gson.annotations.SerializedName
import io.github.slflfl12.remote.model.Review

data class ReviewResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val reviews: List<Review>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
): Response