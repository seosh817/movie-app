package io.github.slflfl12.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.github.slflfl12.local.converter.*
import io.github.slflfl12.local.dao.MovieDao
import io.github.slflfl12.local.dao.PeopleDao
import io.github.slflfl12.local.dao.TvDao
import io.github.slflfl12.local.model.MovieEntity
import io.github.slflfl12.local.model.PersonEntity
import io.github.slflfl12.local.model.TvEntity

@Database(entities = [MovieEntity::class, TvEntity::class, PersonEntity::class], version = 1, exportSchema = false)
@TypeConverters(StringListConverter::class, IntegerListConverter::class, KeywordListConverter::class, ReviewListConverter::class, VideoListConverter::class)
abstract class AppDataBase: RoomDatabase() {

    abstract fun movieDao(): MovieDao

    abstract fun tvDao(): TvDao

    abstract fun peopleDao(): PeopleDao
}