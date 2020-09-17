package io.github.slflfl12.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.github.slflfl12.domain.usecase.*
import io.github.slflfl12.presentation.favorite.FavoriteViewModel
import io.github.slflfl12.presentation.moviedetail.MovieDetailViewModel
import io.github.slflfl12.presentation.movie.MovieViewModel
import io.github.slflfl12.presentation.people.PeopleViewModel
import io.github.slflfl12.presentation.peopledetail.PeopleDetailViewModel
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
        getLocalMovieUseCase: GetLocalMovieUseCase,
        getMovieFavoriteUseCase: GetMovieFavoriteUseCase
    ): MovieDetailViewModel {
        return MovieDetailViewModel(
            getMovieKeywordListUseCase,
            getMovieReviewListUseCase,
            getMovieVideoListUseCase,
            getLocalMovieUseCase,
            getMovieFavoriteUseCase
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
        getLocalTvUseCase: GetLocalTvUseCase,
        getTvFavoriteUseCase: GetTvFavoriteUseCase
    ): TvDetailViewModel {
        return TvDetailViewModel(
            getTvKeywordListUseCase,
            getTvReviewListUseCase,
            getTvVideoListUseCase,
            getLocalTvUseCase,
            getTvFavoriteUseCase
        )
    }

    @Provides
    @Singleton
    fun providePeopleViewModel(
        getPersonListUseCase: GetPeopleListUseCase
    ): PeopleViewModel {
        return PeopleViewModel(getPersonListUseCase)
    }

    @Provides
    @Singleton
    fun providePeopleDetailViewModel(
        getPersonWithDetailUseCase: GetPersonWithDetailUseCase,
        getPersonMovieUseCase: GetPersonMovieUseCase,
        getPersonTvUseCase: GetPersonTvUseCase
    ): PeopleDetailViewModel {
        return PeopleDetailViewModel(
            getPersonWithDetailUseCase,
            getPersonMovieUseCase,
            getPersonTvUseCase
        )
    }

    @Provides
    @Singleton
    fun provideFavoriteViewModel(
        getFavoriteMovieListUseCase: GetFavoriteMovieListUseCase,
        getFavoriteTvListUseCase: GetFavoriteTvListUseCase
    ): FavoriteViewModel {
        return FavoriteViewModel(getFavoriteMovieListUseCase, getFavoriteTvListUseCase)
    }


}