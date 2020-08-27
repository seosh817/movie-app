package io.github.slflfl12.presentation.tv

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.slflfl12.domain.usecase.GetDiscoverTvListUseCase
import io.github.slflfl12.presentation.base.BaseViewModel
import io.github.slflfl12.presentation.model.TvPresentationModel
import io.github.slflfl12.presentation.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

class TvViewModel @ViewModelInject constructor(
    private val getDiscoverTvListUseCase: GetDiscoverTvListUseCase
): BaseViewModel() {

    private val _tvList = MutableLiveData<List<TvPresentationModel>>()
    val tvList: LiveData<List<TvPresentationModel>>
        get() = _tvList

    private val _swipeLoading = MutableLiveData<Boolean>()
    val swipeLoading: LiveData<Boolean>
        get() = _swipeLoading

    private val _networkErrorResponse = MutableLiveData<Throwable>()
    val networkErrorResponse: LiveData<Throwable>
        get() = _networkErrorResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _lastPagingThrowable = SingleLiveEvent<Unit>()
    val lastPagingThrowable: LiveData<Unit>
        get() = _lastPagingThrowable

    private val pageSubject: BehaviorSubject<Int> = BehaviorSubject.create()

    private val pageDisposable = CompositeDisposable()
    private val pageNum = MutableLiveData<Int>()

    init {
        onResetPage()
        pageSubject
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it == 1) {
                } else {

                }
            }, {

            }).addTo(pageDisposable)
    }


    fun onResetPage() {
        pageNum.value = 1
        pageNum.value?.let {
            pageSubject.onNext(it)
        }

    }

    fun plusPage() {
        pageNum.value?.let {
            pageNum.value = it + 1
            pageSubject.onNext(it + 1)
        }
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

    fun unBindPageDisposable() {
        pageDisposable.clear()
    }
}