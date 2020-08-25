package io.github.slflfl12.presentation.movie

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
    private val getLocalMovieUseCase: GetLocalMovieUseCase
) : BaseViewModel() {

    var idSubject: BehaviorSubject<Int> = BehaviorSubject.create()

    private val _movie = MutableLiveData<MoviePresentationModel>()
    val movie: LiveData<MoviePresentationModel>
        get() = _movie

    val keywordList: LiveData<List<KeywordPresentationModel>> = Transformations.map(_movie) { movie ->
        movie.keywords
    }

    val reviewList: LiveData<List<ReviewPresentationModel>> = Transformations.map(_movie) { movie ->
        movie.reviews
    }

    val videoList: LiveData<List<VideoPresentationModel>> = Transformations.map(_movie) { movie ->
        movie.videos
    }

    private lateinit var movieModel: MoviePresentationModel



    private val _videoItemClickEvent = MutableLiveData<Event<VideoPresentationModel>>()
    val videoItemClickEvent: LiveData<Event<VideoPresentationModel>>
        get() = _videoItemClickEvent



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
                _movie.value = movie
                movieModel = movie
            }, {

            }).addTo(compositeDisposable)
    }

    fun onVideoItemClick(videoPresentationModel: VideoPresentationModel) {
        _videoItemClickEvent.value = Event(videoPresentationModel)
    }

    fun getMovie(): MoviePresentationModel = movieModel

}