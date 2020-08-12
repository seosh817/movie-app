package io.github.slflfl12.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.github.slflfl12.data.model.KeywordData

class KeywordListConverter {

    @TypeConverter
    fun fromString(value: String): List<KeywordData>? {
        return Gson().fromJson<List<KeywordData>>(
            value,
            object : TypeToken<List<KeywordData>>() {}.type
        )
    }

    @TypeConverter
    fun fromList(list: List<KeywordData>?): String {
        return Gson().toJson(list)
    }
}