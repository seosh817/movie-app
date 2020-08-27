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
        return tvRepository.getLocalTvList(params).flatMap { cached ->
            if (cached.isEmpty()) {
                discoverRepository.getDiscoverTvs(params).flatMap { tvs ->
                    tvRepository.insertTvList(tvs).andThen(
                        Single.just(tvs)
                    )
                }
            } else {
                Single.just(cached)
            }
        }.subscribeOn(Schedulers.io())
    }
}