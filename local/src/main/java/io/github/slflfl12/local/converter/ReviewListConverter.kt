package io.github.slflfl12.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.github.slflfl12.data.model.ReviewData

class ReviewListConverter {

    @TypeConverter
    fun fromString(value: String): List<ReviewData>? {
        return Gson().fromJson<List<ReviewData>>(
            value,
            object : TypeToken<List<ReviewData>>() {}.type
        )

    }

    @TypeConverter
    fun fromList(list: List<ReviewData>?): String {
        return Gson().toJson(list)
    }
}