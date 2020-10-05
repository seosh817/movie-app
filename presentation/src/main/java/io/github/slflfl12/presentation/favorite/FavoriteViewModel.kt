package io.github.slflfl12.presentation.favorite

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.slflfl12.domain.usecase.GetFavoriteMovieListUseCase
import io.github.slflfl12.domain.usecase.GetFavoriteTvListUseCase
import io.github.slflfl12.domain.usecase.GetMovieFavoriteUseCase
import io.github.slflfl12.domain.usecase.GetTvFavoriteUseCase
import io.github.slflfl12.presentation.base.BaseViewModel
import io.github.slflfl12.presentation.mapper.MoviePresentationMapper
import io.github.slflfl12.presentation.mapper.TvPresentationMapper
import io.github.slflfl12.presentation.model.MoviePresentationModel
import io.github.slflfl12.presentation.model.TvPresentationModel
import io.github.slflfl12.presentation.rxbus.RxBus
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class FavoriteViewModel @ViewModelInject constructor(
    private val getFavoriteMovieListUseCase: GetFavoriteMovieListUseCase,
    private val getFavoriteTvListUseCase: GetFavoriteTvListUseCase
) : BaseViewModel() {

    private val _movieList = MutableLiveData<List<MoviePresentationModel>>()
    val movieList: LiveData<List<MoviePresentationModel>>
        get() = _movieList

    private val _tvList = MutableLiveData<List<TvPresentationModel>>()
    val tvList: LiveData<List<TvPresentationModel>>
        get() = _tvList

    init {
        initViewModel()
    }

    private fun initViewModel() {
        getFavoriteMovieAndTv()
    }

    private fun getFavoriteMovieAndTv() {
        Single.zip(
            getFavoriteMovieListUseCase(Unit),
            getFavoriteTvListUseCase(Unit), { t1, t2 ->
                t1.map(MoviePresentationMapper::mapToPresentation) to t2.map(TvPresentationMapper::mapToPresentation)
            }
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _movieList.value = it.first
                _tvList.value = it.second
            }, {

            }).addTo(compositeDisposable)
    }



}