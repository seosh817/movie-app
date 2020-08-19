package io.github.slflfl12.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.github.slflfl12.domain.usecase.*
import io.github.slflfl12.presentation.movie.MovieDetailViewModel
import io.github.slflfl12.presentation.movie.MovieViewModel
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object PresentationModule {

    @Provides
    @Singleton
    fun provideMovieViewModel(
        getDiscoverMovieListUseCase: GetDiscoverMovieListUseCase
    ): MovieViewModel {
        return MovieViewModel(getDiscoverMovieListUseCase)
    }

    @Provides
    @Singleton
    fun provideMovieDetialViewModel(
        getMovieKeywordListUseCase: GetMovieKeywordListUseCase,
        getMovieReviewListUseCase: GetMovieReviewListUseCase,
        getMovieVideoListUseCase: GetMovieVideoListUseCase
    ): MovieDetailViewModel  {
        return MovieDetailViewModel(getMovieKeywordListUseCase, getMovieReviewListUseCase, getMovieVideoListUseCase)
    }


}