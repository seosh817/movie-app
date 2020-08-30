package io.github.slflfl12.domain.usecase

import android.util.Log
import io.github.slflfl12.domain.model.TvModel
import io.github.slflfl12.domain.repository.TvRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GetLocalTvUseCase(
    private val tvRepository: TvRepository
) : SingleUseCase<TvModel, Int>() {
    override fun buildUseCaseSingle(params: Int): Single<TvModel> =
        tvRepository.getLocalTv(params)
            .subscribeOn(Schedulers.io())
}