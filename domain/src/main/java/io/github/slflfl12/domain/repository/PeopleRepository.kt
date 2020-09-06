package io.github.slflfl12.domain.repository

import io.github.slflfl12.domain.model.PersonDetailModel
import io.github.slflfl12.domain.model.PersonModel
import io.reactivex.Completable
import io.reactivex.Single

interface PeopleRepository {

    fun insertPerson(person: PersonModel): Completable

    fun updatePerson(person: PersonModel): Completable

    fun getPerson(id: Int): Single<PersonModel>

    fun getPopularPeople(page: Int): Single<List<PersonModel>>

    fun getPersonDetail(id: Int): Single<PersonDetailModel>
}