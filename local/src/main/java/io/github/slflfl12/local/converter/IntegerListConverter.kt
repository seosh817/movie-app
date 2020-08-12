package io.github.slflfl12.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class IntegerListConverter {

    @TypeConverter
    fun fromInteger(value: String): List<Int>? {
        return Gson().fromJson<List<Int>>(value, object : TypeToken<List<Int>>() {}.type)
    }

    @TypeConverter
    fun fromList(list: List<Int>): String {
        return Gson().toJson(list)
    }


}