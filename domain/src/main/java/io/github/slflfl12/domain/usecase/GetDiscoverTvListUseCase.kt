package io.github.slflfl12.domain.usecase

import io.github.slflfl12.domain.model.TvModel
import io.github.slflfl12.domain.repository.DiscoverRepository
import io.github.slflfl12.domain.repository.TvRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GetDiscoverTvListUseCase(
    private val tvRepository: TvRepository,
    private val discoverRepository: DiscoverRepository
) : SingleUseCase<List<TvModel>, Int>() {

    override fun buildUseCaseSingle(params: Int): Single<List<TvModel>> {
        return discoverRepository.getDiscoverTvs(params).flatMap { remote ->
            tvRepository.insertTvList(remote).andThen(
                Single.just(remote)
            )
        }.onErrorResumeNext {
            tvRepository.getLocalTvList(params)
        }.subscribeOn(Schedulers.io())
    }
}