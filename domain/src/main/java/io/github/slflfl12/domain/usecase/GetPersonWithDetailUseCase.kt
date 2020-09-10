package io.github.slflfl12.domain.usecase

import io.github.slflfl12.domain.model.PersonModel
import io.github.slflfl12.domain.repository.PeopleRepository
import io.reactivex.Single

class GetPersonWithDetailUseCase(
    private val peopleRepository: PeopleRepository
) : SingleUseCase<PersonModel, Int>() {

    override fun buildUseCaseSingle(params: Int): Single<PersonModel> {
        return peopleRepository.getLocalPerson(params).flatMap { person ->
            if (person.personDetailModel != null) {
                peopleRepository.getPersonDetail(params).flatMapCompletable { detail ->
                    person.personDetailModel = detail
                    peopleRepository.updatePerson(person)
                }.andThen(Single.just(person))
            } else {
                Single.just(person)
            }
        }
    }
}