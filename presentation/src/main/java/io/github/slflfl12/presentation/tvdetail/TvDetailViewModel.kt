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
    private val getLocalTvUseCase: GetLocalTvUseCase
) : BaseViewModel() {

    var idSubject: BehaviorSubject<Int> = BehaviorSubject.create()

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

    private val _networkError = MutableLiveData<Throwable>()
    val networkError: LiveData<Throwable>
        get() = _networkError

    private lateinit var tvModel: TvPresentationModel

    init {
        idSubject.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                getTv(it)
            }, {

            }).addTo(compositeDisposable)
    }

    private fun getTv(id: Int) {
        Single.zip(
            getTvKeywordListUseCase(id),
            getTvReviewListUseCase(id),
            getTvVideoListUseCase(id),
            Function3<List<KeywordModel>, List<ReviewModel>, List<VideoModel>, Triple<List<KeywordModel>, List<ReviewModel>, List<VideoModel>>> { t1, t2, t3 ->
                Triple(t1, t2, t3)
            }
        ).flatMap {
            getLocalTvUseCase(id)
        }.subscribeOn(Schedulers.io())
            .map(TvPresentationMapper::mapToPresentation)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ tv ->
                _tv.value = tv
                tvModel = tv
            }, {
                _networkError.value = it
            }).addTo(compositeDisposable)
    }

}
