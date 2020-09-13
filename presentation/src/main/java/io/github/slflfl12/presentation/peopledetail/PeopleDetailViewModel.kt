package io.github.slflfl12.presentation.peopledetail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import io.github.slflfl12.domain.usecase.GetPersonMovieUseCase
import io.github.slflfl12.domain.usecase.GetPersonTvUseCase
import io.github.slflfl12.domain.usecase.GetPersonWithDetailUseCase
import io.github.slflfl12.presentation.base.BaseViewModel
import io.github.slflfl12.presentation.mapper.MoviePresentationMapper
import io.github.slflfl12.presentation.mapper.PersonPresentationMapper
import io.github.slflfl12.presentation.mapper.TvPresentationMapper
import io.github.slflfl12.presentation.model.MoviePresentationModel
import io.github.slflfl12.presentation.model.PersonDetailPresentationModel
import io.github.slflfl12.presentation.model.PersonPresentationModel
import io.github.slflfl12.presentation.model.TvPresentationModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

class PeopleDetailViewModel @ViewModelInject constructor(
    private val getPersonWithDetailUseCase: GetPersonWithDetailUseCase,
    private val getPersonMovieUseCase: GetPersonMovieUseCase,
    private val getPersonTvUseCase: GetPersonTvUseCase
) : BaseViewModel() {


    var personSubject: BehaviorSubject<PersonPresentationModel> = BehaviorSubject.create()

    private val _personDetail = MutableLiveData<PersonDetailPresentationModel>()
    val personDetail: LiveData<PersonDetailPresentationModel>
        get() = _personDetail

    private val _personMovieList = MutableLiveData<List<MoviePresentationModel>>()
    val personMovieList: LiveData<List<MoviePresentationModel>>
        get() = _personMovieList

    private val _personTvList = MutableLiveData<List<TvPresentationModel>>()
    val personTvList: LiveData<List<TvPresentationModel>>
        get() = _personTvList

    private val _networkError = MutableLiveData<Throwable>()
    val networkError: LiveData<Throwable>
        get() = _networkError


    init {
        personSubject.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                getPersonDetail(it)
                getPersonMovie(it.id)
                getPersonTv(it.id)
            }, {

            }).addTo(compositeDisposable)
    }

    private fun getPersonDetail(person:PersonPresentationModel) {
        getPersonWithDetailUseCase(PersonPresentationMapper.mapToDomain(person))
            .subscribeOn(Schedulers.io())
            .map(PersonPresentationMapper::mapToPresentation)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ person ->
                _personDetail.value = person.personDetailPresentationModel
            }, {
                _networkError.value = it
            }).addTo(compositeDisposable)
    }

    private fun getPersonMovie(id: Int) {
        getPersonMovieUseCase(id)
            .subscribeOn(Schedulers.io())
            .map { it.map(MoviePresentationMapper::mapToPresentation) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ movieList ->
                _personMovieList.value = movieList
            }, {
                _networkError.value = it
            }).addTo(compositeDisposable)
    }


    private fun getPersonTv(id: Int) {
        getPersonTvUseCase(id)
            .subscribeOn(Schedulers.io())
            .map { it.map(TvPresentationMapper::mapToPresentation) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ tvList ->
                _personTvList.value = tvList
            }, {
                _networkError.value = it
            }).addTo(compositeDisposable)
    }
}