package io.github.slflfl12.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.github.slflfl12.local.model.KeywordEntity
import io.github.slflfl12.local.model.ReviewEntity

class ReviewListConverter {

    @TypeConverter
    fun fromString(value: String): List<ReviewEntity>? {
        return Gson().fromJson<List<ReviewEntity>>(value, object: TypeToken<List<ReviewEntity>>() {}.type)
    }

    @TypeConverter
    fun fromList(list: List<ReviewEntity>?): String {
        return Gson().toJson(list)
    }
}