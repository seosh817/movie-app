package io.github.slflfl12.remote.model


import com.google.gson.annotations.SerializedName
import io.github.slflfl12.remote.model.Video

data class VideoResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val videos: List<Video>
): Response