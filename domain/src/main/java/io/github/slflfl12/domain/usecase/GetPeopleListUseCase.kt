package io.github.slflfl12.domain.usecase

import io.github.slflfl12.domain.model.PersonModel
import io.github.slflfl12.domain.repository.PeopleRepository
import io.reactivex.Single

class GetPeopleListUseCase(
    private val peopleRepository: PeopleRepository
): SingleUseCase<List<PersonModel>, Int>() {

    override fun buildUseCaseSingle(params: Int): Single<List<PersonModel>> {
        return peopleRepository.getPopularPeople(params)

    }
}