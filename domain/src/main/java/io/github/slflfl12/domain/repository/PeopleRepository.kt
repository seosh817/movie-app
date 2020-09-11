package io.github.slflfl12.domain.repository

import io.github.slflfl12.domain.model.MovieModel
import io.github.slflfl12.domain.model.PersonDetailModel
import io.github.slflfl12.domain.model.PersonModel
import io.github.slflfl12.domain.model.TvModel
import io.reactivex.Completable
import io.reactivex.Single

interface PeopleRepository {

    fun insertPerson(person: PersonModel): Completable

    fun updatePerson(person: PersonModel): Completable

    fun getLocalPerson(id: Int): Single<PersonModel>

    fun getPopularPeople(page: Int): Single<List<PersonModel>>

    fun getPersonDetail(id: Int): Single<PersonDetailModel>

    fun getPersonMovie(id: Int): Single<List<MovieModel>>

    fun getPersonTv(id: Int): Single<List<TvModel>>
}