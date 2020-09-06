package io.github.slflfl12.data.local

import io.github.slflfl12.data.model.PersonData
import io.reactivex.Completable
import io.reactivex.Single

interface PeopleLocalDataSource {

    fun insertPerson(person: PersonData): Completable

    fun updatePerson(person: PersonData): Completable

    fun getPerson(id: Int): Single<PersonData>
}