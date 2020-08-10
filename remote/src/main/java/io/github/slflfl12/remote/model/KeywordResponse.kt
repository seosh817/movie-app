package io.github.slflfl12.remote.model


import com.google.gson.annotations.SerializedName
import io.github.slflfl12.remote.model.Keyword

data class KeywordResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("keywords")
    val keywords: List<Keyword>
): Response