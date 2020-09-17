package io.github.slflfl12.domain.usecase

import io.github.slflfl12.domain.model.TvModel
import io.github.slflfl12.domain.repository.TvRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GetFavoriteTvListUseCase(
    private val tvRepository: TvRepository
) : SingleUseCase<List<TvModel>, Unit>() {

    override fun buildUseCaseSingle(params: Unit): Single<List<TvModel>> {
        return tvRepository.getFavoriteTvList()
            .subscribeOn(Schedulers.io())
    }
}