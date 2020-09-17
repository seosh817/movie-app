package io.github.slflfl12.domain.usecase

import io.github.slflfl12.domain.model.TvModel
import io.github.slflfl12.domain.repository.TvRepository
import io.reactivex.Single

class GetTvFavoriteUseCase(
    private val tvRepository: TvRepository
) : SingleUseCase<TvModel, Int>() {

    override fun buildUseCaseSingle(params: Int): Single<TvModel> {
        return tvRepository.getLocalTv(params).flatMapCompletable { tv ->
            tv.favorite = !tv.favorite
            tvRepository.updateTv(tv)
        }.andThen(tvRepository.getLocalTv(params))
    }
}