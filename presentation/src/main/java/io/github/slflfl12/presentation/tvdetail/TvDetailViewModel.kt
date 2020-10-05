package io.github.slflfl12.presentation.tvdetail

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
import io.github.slflfl12.presentation.mapper.TvPresentationMapper
import io.github.slflfl12.presentation.model.*
import io.github.slflfl12.presentation.rxbus.RxBus
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function3
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

class TvDetailViewModel @ViewModelInject constructor(
    private val getTvKeywordListUseCase: GetTvKeywordListUseCase,
    private val getTvReviewListUseCase: GetTvReviewListUseCase,
    private val getTvVideoListUseCase: GetTvVideoListUseCase,
    private val getLocalTvUseCase: GetLocalTvUseCase,
    private val getTvFavoriteUseCase: GetTvFavoriteUseCase
) : BaseViewModel() {

    var tvSubject: BehaviorSubject<TvPresentationModel> = BehaviorSubject.create()

    private val _tv = MutableLiveData<TvPresentationModel>()
    val tv: LiveData<TvPresentationModel>
        get() = _tv

    val keywordList: LiveData<List<KeywordPresentationModel>> = Transformations.map(_tv) { tv ->
        tv.keywords
    }

    val reviewList: LiveData<List<ReviewPresentationModel>> = Transformations.map(_tv) { tv ->
        tv.reviews
    }

    val videoList: LiveData<List<VideoPresentationModel>> = Transformations.map(_tv) { tv ->
        tv.videos
    }

    val favorite = MutableLiveData<Boolean>()

    private val _networkError = MutableLiveData<Throwable>()
    val networkError: LiveData<Throwable>
        get() = _networkError

    init {
        tvSubject.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                getTv(it)
            }, {

            }).addTo(compositeDisposable)
    }

    private fun getTv(tv: TvPresentationModel) {
        Single.zip(
            getTvKeywordListUseCase(TvPresentationMapper.mapToDomain(tv)),
            getTvReviewListUseCase(TvPresentationMapper.mapToDomain(tv)),
            getTvVideoListUseCase(TvPresentationMapper.mapToDomain(tv)),
            Function3<List<KeywordModel>, List<ReviewModel>, List<VideoModel>, Triple<List<KeywordModel>, List<ReviewModel>, List<VideoModel>>> { t1, t2, t3 ->
                Triple(t1, t2, t3)
            }
        ).flatMap {
            getLocalTvUseCase(tv.id)
        }.subscribeOn(Schedulers.io())
            .map(TvPresentationMapper::mapToPresentation)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ tv ->
                _tv.value = tv
                favorite.value = tv.favorite
            }, {
                _networkError.value = it
            }).addTo(compositeDisposable)
    }

    fun onClickFavorite(tv: TvPresentationModel) {
        getTvFavoriteUseCase(tv.id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map(TvPresentationMapper::mapToPresentation)
            .subscribe({ tv ->
                favorite.value = tv.favorite
                RxBus.tvOnNext(tv)
            }, {

            }).addTo(compositeDisposable)
    }

}
