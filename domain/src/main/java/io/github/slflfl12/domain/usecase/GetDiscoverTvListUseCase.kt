package io.github.slflfl12.domain.usecase

import android.util.Log
import io.github.slflfl12.domain.model.TvModel
import io.github.slflfl12.domain.repository.DiscoverRepository
import io.github.slflfl12.domain.repository.TvRepository
import io.reactivex.Single

class GetDiscoverTvListUseCase(
    private val tvRepository: TvRepository,
    private val discoverRepository: DiscoverRepository
) : SingleUseCase<List<TvModel>, Int>() {

    override fun buildUseCaseSingle(params: Int): Single<List<TvModel>> {
        return tvRepository.getLocalTvList(params).flatMap { tvs ->
            if (tvs.size < 20) {
                discoverRepository.getDiscoverTvs(params).flatMap { remote ->
                    tvRepository.insertTvList(remote).andThen(
                        Single.just(remote)
                    )
                }
            } else {
                Single.just(tvs)
            }
        }
    }
}