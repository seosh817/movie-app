package io.github.slflfl12.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.github.slflfl12.domain.usecase.*
import io.github.slflfl12.presentation.moviedetail.MovieDetailViewModel
import io.github.slflfl12.presentation.movie.MovieViewModel
import io.github.slflfl12.presentation.tvdetail.TvDetailViewModel
import io.github.slflfl12.presentation.tv.TvViewModel
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
        getMovieVideoListUseCase: GetMovieVideoListUseCase,
        getLocalMovieUseCase: GetLocalMovieUseCase
    ): MovieDetailViewModel {
        return MovieDetailViewModel(
            getMovieKeywordListUseCase,
            getMovieReviewListUseCase,
            getMovieVideoListUseCase,
            getLocalMovieUseCase
        )
    }

    @Provides
    @Singleton
    fun provideTvViewModel(
        getDiscoverTvListUseCase: GetDiscoverTvListUseCase
    ): TvViewModel {
        return TvViewModel(getDiscoverTvListUseCase)
    }

    @Provides
    @Singleton
    fun provideTvDetailViewModel(
        getTvKeywordListUseCase: GetTvKeywordListUseCase,
        getTvReviewListUseCase: GetTvReviewListUseCase,
        getTvVideoListUseCase: GetTvVideoListUseCase,
        getLocalTvUseCase: GetLocalTvUseCase
    ): TvDetailViewModel {
        return TvDetailViewModel(
            getTvKeywordListUseCase,
            getTvReviewListUseCase,
            getTvVideoListUseCase,
            getLocalTvUseCase
        )
    }


}