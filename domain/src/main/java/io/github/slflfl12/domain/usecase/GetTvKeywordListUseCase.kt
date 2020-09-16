package io.github.slflfl12.domain.usecase

import io.github.slflfl12.domain.model.KeywordModel
import io.github.slflfl12.domain.model.TvModel
import io.github.slflfl12.domain.repository.TvRepository
import io.reactivex.Single

class GetTvKeywordListUseCase(
    private val tvRepository: TvRepository
) : SingleUseCase<List<KeywordModel>, TvModel>() {

    override fun buildUseCaseSingle(params: TvModel): Single<List<KeywordModel>> {
        return tvRepository.getLocalTv(params.id).flatMap { tv ->
            if (tv.keywords.isNullOrEmpty()) {
                tvRepository.getKeywordList(params.id).flatMapCompletable {
                    tv.keywords = it
                    tvRepository.updateTv(tv)
                }.andThen(tvRepository.getKeywordList(params.id))
            } else {
                tvRepository.getKeywordList(params.id)
            }
        }.onErrorResumeNext {
            tvRepository.insertTv(params).andThen(
                Single.just(params)
            ).flatMap { tv ->
                tvRepository.getKeywordList(params.id).flatMapCompletable {
                    tv.keywords = it
                    tvRepository.updateTv(tv)
                }.andThen(tvRepository.getKeywordList(params.id))
            }
        }
    }
}