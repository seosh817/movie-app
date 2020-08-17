package io.github.slflfl12.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.github.slflfl12.local.converter.IntegerListConverter
import io.github.slflfl12.local.dao.MovieDao
import io.github.slflfl12.local.model.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
@TypeConverters(IntegerListConverter::class)
abstract class MovieDataBase: RoomDatabase() {

    abstract fun movieDao(): MovieDao
}