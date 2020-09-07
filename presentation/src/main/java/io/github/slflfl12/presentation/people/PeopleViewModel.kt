package io.github.slflfl12.presentation.people

import android.util.Log
import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.slflfl12.domain.usecase.GetPeopleListUseCase
import io.github.slflfl12.presentation.base.BaseViewModel
import io.github.slflfl12.presentation.mapper.PersonPresentationMapper
import io.github.slflfl12.presentation.model.PersonPresentationModel
import io.github.slflfl12.presentation.util.Event
import io.github.slflfl12.presentation.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

class PeopleViewModel @ViewModelInject constructor(
    private val getPeopleListUseCase: GetPeopleListUseCase
): BaseViewModel() {

    private val _peopleList = MutableLiveData<List<PersonPresentationModel>>()
    val peopleList: LiveData<List<PersonPresentationModel>>
        get() = _peopleList

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
                    refresh(it)
                } else {
                    loadMore()
                }
            }, {

            }).addTo(pageDisposable)

    }


    private fun loadMore() {
        pageNum.value?.let { pageNum ->
            getPeopleListUseCase(pageNum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { it.map(PersonPresentationMapper::mapToPresentation) }
                .doOnSubscribe { showLoading() }
                .doAfterTerminate { hideLoading() }
                .subscribe({ people ->
                    if (people.isNotEmpty()) {
                        _peopleList.value = people
                    } else {
                        _lastPagingThrowable.call()
                    }
                }, {
                    Log.e("error", it.message!!)
                    _networkErrorResponse.value = it
                }).addTo(compositeDisposable)
        }

    }


    private fun refresh(pageNum: Int) {
        getPeopleListUseCase(pageNum)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showSwipeLoading() }
            .doAfterTerminate { hideSwipeLoading() }
            .map { it.map(PersonPresentationMapper::mapToPresentation) }
            .subscribe({ people ->
                if (people.isNotEmpty()) {
                    _peopleList.value = people
                }
            }, {
                Log.e("error", it.message.toString())
                _networkErrorResponse.value = it
            }).addTo(compositeDisposable)
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