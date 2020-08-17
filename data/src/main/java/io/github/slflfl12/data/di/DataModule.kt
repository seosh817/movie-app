package io.github.slflfl12.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.github.slflfl12.data.local.MovieLocalDataSource
import io.github.slflfl12.data.remote.DiscoverRemoteDataSource
import io.github.slflfl12.data.remote.MovieRemoteDataSource
import io.github.slflfl12.data.repository.DiscoverRepositoryImpl
import io.github.slflfl12.data.repository.MovieRepositoryImpl
import io.github.slflfl12.domain.repository.DiscoverRepository
import io.github.slflfl12.domain.repository.MovieRepository
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideMovieRepository(
        movieLocalDataSource: MovieLocalDataSource,
        movieRemoteDataSource: MovieRemoteDataSource
    ): MovieRepository {
        return MovieRepositoryImpl(
            movieLocalDataSource,
            movieRemoteDataSource
        )
    }

    @Provides
    @Singleton
    fun provideDiscoverRepository(
        discoverRemoteDataSource: DiscoverRemoteDataSource
    ): DiscoverRepository {
        return DiscoverRepositoryImpl(
            discoverRemoteDataSource
        )
    }
}