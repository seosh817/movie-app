package io.github.slflfl12.presentation.movie

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
    private val getLocalMovieUseCase: GetLocalMovieUseCase
) : BaseViewModel() {

    var idSubject: BehaviorSubject<Int> = BehaviorSubject.create()

    private val _movie = MutableLiveData<MoviePresentationModel>()
    val movie: LiveData<MoviePresentationModel>
        get() = _movie



    private val _keywordList = MutableLiveData<List<KeywordPresentationModel>>()
    val keywordList: LiveData<List<KeywordPresentationModel>>
        get() = _keywordList

    private val _reviewList = MutableLiveData<List<ReviewPresentationModel>>()
    val reviewList: LiveData<List<ReviewPresentationModel>>
        get() = _reviewList

    private val _videoList = MutableLiveData<List<VideoPresentationModel>>()
    val videoList: LiveData<List<VideoPresentationModel>>
        get() = _videoList

    init {
        idSubject.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                getMovie(it)
            }, {

            }).addTo(compositeDisposable)
    }

    private fun getMovie(id: Int) {
        Single.zip(
            getMovieKeywordListUseCase(id),
            getMovieReviewListUseCase(id),
            getMovieVideoListUseCase(id),
            Function3<List<KeywordModel>, List<ReviewModel>, List<VideoModel>, Triple<List<KeywordModel>, List<ReviewModel>, List<VideoModel>>> { t1, t2, t3 ->
                Triple(t1, t2, t3)
            }).flatMap {
            getLocalMovieUseCase(id)
        }.subscribeOn(Schedulers.io())
            .map(MoviePresentationMapper::mapToPresentation)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ movie ->
                _keywordList.value= movie.keywords
                _reviewList.value = movie.reviews
                _videoList.value = movie.videos
                _movie.value = movie
            }, {

            }).addTo(compositeDisposable)
    }




}