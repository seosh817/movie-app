package io.github.slflfl12.presentation.movie

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.slflfl12.domain.usecase.GetDiscoverMovieListUseCase
import io.github.slflfl12.presentation.base.BaseViewModel
import io.github.slflfl12.presentation.mapper.MoviePresentationMapper
import io.github.slflfl12.presentation.model.MoviePresentationModel
import io.github.slflfl12.presentation.util.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import kotlin.properties.Delegates

class MovieViewModel @ViewModelInject constructor(
    private val getDiscoverMovieListUseCase: GetDiscoverMovieListUseCase
) : BaseViewModel() {


    private val _movieList = MutableLiveData<List<MoviePresentationModel>>()
    val movieList: LiveData<List<MoviePresentationModel>>
        get() = _movieList

    private val _swipeLoading = MutableLiveData<Boolean>()
    val swipeLoading: LiveData<Boolean>
        get() = _swipeLoading

    private val _networkErrorResponse = MutableLiveData<Throwable>()
    val networkErrorResponse: LiveData<Throwable>
        get() = _networkErrorResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _toDetailEvent = MutableLiveData<Event<MoviePresentationModel>>()
    val toDetailEvent: LiveData<Event<MoviePresentationModel>>
        get() = _toDetailEvent

    private val pageSubject:BehaviorSubject<Int> = BehaviorSubject.create()


    private var pageNum by Delegates.notNull<Int>()


    init {
        onResetPage()
        pageSubject
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if(it == 1) {
                    refresh(it)
                }
                else {
                    loadMore()
                }
            }, {

            }).addTo(compositeDisposable)

    }


    private fun loadMore() {
        getDiscoverMovieListUseCase(pageNum)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.map(MoviePresentationMapper::mapToPresentation) }
            .doOnSubscribe { showLoading() }
            .doAfterTerminate { hideLoading() }
            .subscribe({ movies ->
                _movieList.value = movies
            }, {
                Log.d("error", it.message!!)
                _networkErrorResponse.value = it
            }).addTo(compositeDisposable)
    }


    private fun refresh(pageNum: Int) {
        Log.d("refresh", "refresh")
        getDiscoverMovieListUseCase(pageNum)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showSwipeLoading() }
            .doAfterTerminate { hideSwipeLoading() }
            .map { it.map(MoviePresentationMapper::mapToPresentation) }
            .subscribe({ movies ->
                if (movies.isNotEmpty()) {
                    _movieList.value = movies
                }
            }, {
                Log.d("error", it.message.toString())
                _networkErrorResponse.value = it
            }).addTo(compositeDisposable)
    }


    fun navigateToDetail(moviePresentationModel: MoviePresentationModel) {
        _toDetailEvent.value = Event(moviePresentationModel)
    }

    fun onResetPage() {
        pageNum = 1
        pageSubject.onNext(pageNum)
    }

    fun plusPage() {
        pageSubject.onNext(++pageNum)
    }

    private fun showLoading() {
        _isLoading.value = true
    }

    private fun hideLoading() {
        _isLoading.value = false
    }

    private fun showSwipeLoading() {
        _swipeLoading.value = true
    }

    private fun hideSwipeLoading() {
        _swipeLoading.value = false
    }


}