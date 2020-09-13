package io.github.slflfl12.domain.usecase

import io.github.slflfl12.domain.model.PersonModel
import io.github.slflfl12.domain.repository.PeopleRepository
import io.reactivex.Maybe
import io.reactivex.Single

class GetPersonWithDetailUseCase(
    private val peopleRepository: PeopleRepository
) : SingleUseCase<PersonModel, PersonModel>() {

    override fun buildUseCaseSingle(params: PersonModel): Single<PersonModel> {
        return peopleRepository.getLocalPerson(params.id).flatMap { person ->
            if (person.personDetailModel == null) {
                peopleRepository.getPersonDetail(params.id).flatMapCompletable { detail ->
                    person.personDetailModel = detail
                    peopleRepository.updatePerson(person)
                }.andThen(Single.just(person))
            } else {
                Single.just(person)
            }
        }.onErrorResumeNext {
            Single.just(params).flatMap { person ->
                peopleRepository.getPersonDetail(params.id).flatMapCompletable { detail ->
                    person.personDetailModel = detail
                    peopleRepository.updatePerson(person)
                }.andThen(Single.just(person))
            }
        }
    }
}