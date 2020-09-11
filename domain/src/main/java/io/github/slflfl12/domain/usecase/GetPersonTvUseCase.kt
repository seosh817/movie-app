package io.github.slflfl12.domain.usecase

import io.github.slflfl12.domain.model.TvModel
import io.github.slflfl12.domain.repository.PeopleRepository
import io.reactivex.Single

class GetPersonTvUseCase(
    private val peopleRepository: PeopleRepository
): SingleUseCase<List<TvModel>, Int>() {

    override fun buildUseCaseSingle(params: Int): Single<List<TvModel>> {
        return peopleRepository.getPersonTv(params)
    }
}