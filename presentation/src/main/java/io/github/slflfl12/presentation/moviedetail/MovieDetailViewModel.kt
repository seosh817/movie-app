package io.github.slflfl12.presentation.moviedetail

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import io.github.slflfl12.domain.model.KeywordModel
import io.github.slflfl12.domain.model.ReviewModel
import io.github.slflfl12.domain.model.VideoModel
import io.github.slflfl12.domain.usecase.*
import io.github.slflfl12.presentation.base.BaseViewModel
import io.github.slflfl12.presentation.mapper.MoviePresentationMapper
import io.github.slflfl12.presentation.model.KeywordPresentationModel
import io.github.slflfl12.presentation.model.MoviePresentationModel
import io.github.slflfl12.presentation.model.ReviewPresentationModel
import io.github.slflfl12.presentation.model.VideoPresentationModel
import io.github.slflfl12.presentation.util.Event
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function3
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject


class MovieDetailViewModel @ViewModelInject constructor(
    private val getMovieKeywordListUseCase: GetMovieKeywordListUseCase,
    private val getMovieReviewListUseCase: GetMovieReviewListUseCase,
    private val getMovieVideoListUseCase: GetMovieVideoListUseCase,
    private val getLocalMovieUseCase: GetLocalMovieUseCase,
    private val getMovieFavoriteUseCase: GetMovieFavoriteUseCase
) : BaseViewModel() {

    var movieSubject: BehaviorSubject<MoviePresentationModel> = BehaviorSubject.create()

    private val _movie = MutableLiveData<MoviePresentationModel>()
    val movie: LiveData<MoviePresentationModel>
        get() = _movie

    val keywordList: LiveData<List<KeywordPresentationModel>> =
        Transformations.map(_movie) { movie ->
            movie.keywords
        }

    val reviewList: LiveData<List<ReviewPresentationModel>> = Transformations.map(_movie) { movie ->
        movie.reviews
    }

    val videoList: LiveData<List<VideoPresentationModel>> = Transformations.map(_movie) { movie ->
        movie.videos
    }

    val favorite = MutableLiveData<Boolean>()

    private val _networkError = MutableLiveData<Throwable>()
    val networkError: LiveData<Throwable>
        get() = _networkError


    private val _videoItemClickEvent = MutableLiveData<Event<VideoPresentationModel>>()
    val videoItemClickEvent: LiveData<Event<VideoPresentationModel>>
        get() = _videoItemClickEvent


    init {
        movieSubject.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                getMovie(it)
            }, {

            }).addTo(compositeDisposable)
    }

    private fun getMovie(movie: MoviePresentationModel) {
        Single.zip(
            getMovieKeywordListUseCase(MoviePresentationMapper.mapToDomain(movie)),
            getMovieReviewListUseCase(MoviePresentationMapper.mapToDomain(movie)),
            getMovieVideoListUseCase(MoviePresentationMapper.mapToDomain(movie)),
            Function3<List<KeywordModel>, List<ReviewModel>, List<VideoModel>, Triple<List<KeywordModel>, List<ReviewModel>, List<VideoModel>>> { t1, t2, t3 ->
                Triple(t1, t2, t3)
            }).flatMap {
            getLocalMovieUseCase(movie.id)
        }.subscribeOn(Schedulers.io())
            .map(MoviePresentationMapper::mapToPresentation)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ movie ->
                _movie.value = movie
                favorite.value = movie.favorite
            }, {
                _networkError.value = it
            }).addTo(compositeDisposable)
    }

    fun onClickFavorite(movie: MoviePresentationModel) {
        getMovieFavoriteUseCase(movie.id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ movie ->
                favorite.value = movie.favorite
            }, {

            }).addTo(compositeDisposable)
    }



}