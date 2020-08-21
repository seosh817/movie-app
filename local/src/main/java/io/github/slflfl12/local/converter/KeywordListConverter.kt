package io.github.slflfl12.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.github.slflfl12.data.model.KeywordData
import io.github.slflfl12.local.model.KeywordEntity


class KeywordListConverter {

    @TypeConverter
    fun fromString(value: String): List<KeywordEntity>? {
        return Gson().fromJson<List<KeywordEntity>>(value, object: TypeToken<List<KeywordEntity>>() {}.type)
    }

    @TypeConverter
    fun fromList(list: List<KeywordEntity>?): String {
        return Gson().toJson(list)
    }
}