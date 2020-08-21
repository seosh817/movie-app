package io.github.slflfl12.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.github.slflfl12.local.model.ReviewEntity
import io.github.slflfl12.local.model.VideoEntity

class VideoListConverter {

    @TypeConverter
    fun fromString(value: String): List<VideoEntity>? {
        return Gson().fromJson<List<VideoEntity>>(value, object: TypeToken<List<VideoEntity>>() {}.type)
    }

    @TypeConverter
    fun fromList(list: List<VideoEntity>?): String {
        return Gson().toJson(list)
    }
}