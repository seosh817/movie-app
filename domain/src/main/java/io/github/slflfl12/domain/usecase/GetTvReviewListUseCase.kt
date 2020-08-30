package io.github.slflfl12.domain.usecase

import android.util.Log
import io.github.slflfl12.domain.model.ReviewModel
import io.github.slflfl12.domain.repository.TvRepository
import io.reactivex.Single

class GetTvReviewListUseCase(
    private val tvRepository: TvRepository
) : SingleUseCase<List<ReviewModel>, Int>() {

    override fun buildUseCaseSingle(params: Int): Single<List<ReviewModel>> =
        tvRepository.getLocalTv(params).flatMap { tv ->
            if (tv.reviews.isNullOrEmpty()) {
                tvRepository.getReviewList(params).flatMapCompletable {
                    tv.reviews = it
                    tvRepository.updateTv(tv)
                }.andThen(tvRepository.getReviewList(params))
            } else {
                Single.just(tv.reviews)
            }
        }
}