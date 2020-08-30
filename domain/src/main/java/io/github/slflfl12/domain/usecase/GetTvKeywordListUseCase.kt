package io.github.slflfl12.domain.usecase

import android.util.Log
import io.github.slflfl12.domain.model.KeywordModel
import io.github.slflfl12.domain.repository.TvRepository
import io.reactivex.Single

class GetTvKeywordListUseCase(
    private val tvRepository: TvRepository
) : SingleUseCase<List<KeywordModel>, Int>() {

    override fun buildUseCaseSingle(params: Int): Single<List<KeywordModel>> =
        tvRepository.getLocalTv(params).flatMap { tv ->
            if (tv.keywords.isNullOrEmpty()) {
                tvRepository.getKeywordList(params).flatMapCompletable {
                    tv.keywords = it
                    tvRepository.updateTv(tv)
                }.andThen(tvRepository.getKeywordList(params))
            } else {
                Single.just(tv.keywords)
            }
        }
}