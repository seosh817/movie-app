package io.github.slflfl12.local.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.slflfl12.data.local.MovieLocalDataSource
import io.github.slflfl12.data.local.PeopleLocalDataSource
import io.github.slflfl12.data.local.TvLocalDataSource
import io.github.slflfl12.data.remote.PeopleRemoteDataSource
import io.github.slflfl12.local.R
import io.github.slflfl12.local.dao.MovieDao
import io.github.slflfl12.local.dao.PeopleDao
import io.github.slflfl12.local.dao.TvDao
import io.github.slflfl12.local.database.AppDataBase
import io.github.slflfl12.local.source.MovieLocalDataSourceImpl
import io.github.slflfl12.local.source.PeopleLocalDataSourceImpl
import io.github.slflfl12.local.source.TvLocalDataSourceImpl
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            context.getString(R.string.database)
        )
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(
        appDatabase: AppDataBase
    ): MovieDao {
        return appDatabase.movieDao()
    }

    @Provides
    @Singleton
    fun provideMovieDataSource(
        movieDao: MovieDao
    ): MovieLocalDataSource {
        return MovieLocalDataSourceImpl(movieDao)
    }

    @Provides
    @Singleton
    fun provideTvDao(
        appDatabase: AppDataBase
    ): TvDao {
        return appDatabase.tvDao()
    }

    @Provides
    @Singleton
    fun provideTvDataSource(
        tvDao: TvDao
    ): TvLocalDataSource {
        return TvLocalDataSourceImpl(tvDao)
    }

    @Provides
    @Singleton
    fun providePeopleDao(
        appDatabase: AppDataBase
    ): PeopleDao {
        return appDatabase.peopleDao()
    }

    @Provides
    @Singleton
    fun providePeopleDataSource(
        peopleDao: PeopleDao
    ): PeopleLocalDataSource {
        return PeopleLocalDataSourceImpl(peopleDao)
    }


}