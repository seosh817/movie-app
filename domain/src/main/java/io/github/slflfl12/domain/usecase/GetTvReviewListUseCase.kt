package io.github.slflfl12.domain.usecase

import android.util.Log
import io.github.slflfl12.domain.model.ReviewModel
import io.github.slflfl12.domain.model.TvModel
import io.github.slflfl12.domain.repository.TvRepository
import io.reactivex.Single

class GetTvReviewListUseCase(
    private val tvRepository: TvRepository
) : SingleUseCase<List<ReviewModel>, TvModel>() {

    override fun buildUseCaseSingle(params: TvModel): Single<List<ReviewModel>> {
        return tvRepository.getLocalTv(params.id).flatMap { tv ->
            if (tv.keywords.isNullOrEmpty()) {
                tvRepository.getReviewList(params.id).flatMapCompletable {
                    tv.reviews = it
                    tvRepository.updateTv(tv)
                }.andThen(tvRepository.getReviewList(params.id))
            } else {
                tvRepository.getReviewList(params.id)
            }
        }.onErrorResumeNext {
            tvRepository.insertTv(params).andThen(
                Single.just(params)
            ).flatMap { tv ->
                tvRepository.getReviewList(params.id).flatMapCompletable {
                    tv.reviews = it
                    tvRepository.updateTv(tv)
                }.andThen(tvRepository.getReviewList(params.id))
            }
        }
    }
}