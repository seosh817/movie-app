package io.github.slflfl12.remote.model


import com.google.gson.annotations.SerializedName

data class Review(
    @SerializedName("author")
    val author: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String
): Response