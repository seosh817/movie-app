package io.github.slflfl12.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.github.slflfl12.domain.repository.DiscoverRepository
import io.github.slflfl12.domain.repository.MovieRepository
import io.github.slflfl12.domain.usecase.GetDiscoverMovieListUseCase
import io.github.slflfl12.domain.usecase.InsertMoviesUseCase
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideGetDiscoverMovieListUseCase(
        movieRepository: MovieRepository,
        discoverRepository: DiscoverRepository
    ): GetDiscoverMovieListUseCase {
        return GetDiscoverMovieListUseCase(movieRepository, discoverRepository)
    }

    @Provides
    @Singleton
    fun provideInsertMoviesUseCase(
        movieRepository: MovieRepository
    ): InsertMoviesUseCase {
        return InsertMoviesUseCase(movieRepository)
    }
}