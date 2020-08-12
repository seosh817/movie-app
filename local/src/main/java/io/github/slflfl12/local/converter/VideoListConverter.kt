package io.github.slflfl12.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.github.slflfl12.data.model.VideoData

class VideoListConverter {

    @TypeConverter
    fun fromString(value: String): List<VideoData>? {
        return Gson().fromJson<List<VideoData>>(value, object :TypeToken<List<VideoData>>() {}.type)
    }

    @TypeConverter
    fun fromList(list: List<VideoData>?): String {
        return Gson().toJson(list)
    }
}