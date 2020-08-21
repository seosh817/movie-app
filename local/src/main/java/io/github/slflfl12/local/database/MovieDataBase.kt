package io.github.slflfl12.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.github.slflfl12.local.converter.IntegerListConverter
import io.github.slflfl12.local.converter.KeywordListConverter
import io.github.slflfl12.local.converter.ReviewListConverter
import io.github.slflfl12.local.converter.VideoListConverter
import io.github.slflfl12.local.dao.MovieDao
import io.github.slflfl12.local.model.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
@TypeConverters(IntegerListConverter::class, KeywordListConverter::class, ReviewListConverter::class, VideoListConverter::class)
abstract class MovieDataBase: RoomDatabase() {

    abstract fun movieDao(): MovieDao
}