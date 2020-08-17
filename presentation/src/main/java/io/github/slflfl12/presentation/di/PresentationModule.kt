package io.github.slflfl12.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.github.slflfl12.domain.usecase.GetDiscoverMovieListUseCase
import io.github.slflfl12.domain.usecase.InsertMoviesUseCase
import io.github.slflfl12.presentation.movie.MovieViewModel
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object PresentationModule {

    @Provides
    @Singleton
    fun provideMovieViewModel(
        getDiscoverMovieListUseCase: GetDiscoverMovieListUseCase,
        insertMoviesUseCase: InsertMoviesUseCase
    ): MovieViewModel {
        return MovieViewModel(getDiscoverMovieListUseCase, insertMoviesUseCase)
    }


}