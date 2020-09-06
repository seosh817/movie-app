package io.github.slflfl12.local.source

import io.github.slflfl12.data.local.PeopleLocalDataSource
import io.github.slflfl12.data.model.PersonData
import io.github.slflfl12.local.dao.PeopleDao
import io.github.slflfl12.local.mapper.PersonLocalMapper
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class PeopleLocalDataSourceImpl(
    private val peopleDao: PeopleDao
) : PeopleLocalDataSource {

    override fun insertPerson(person: PersonData): Completable {
        return peopleDao.insertPerson(PersonLocalMapper.mapToLocal(person))
            .subscribeOn(Schedulers.io())
    }

    override fun updatePerson(person: PersonData): Completable {
        return peopleDao.updatePerson(PersonLocalMapper.mapToLocal(person))
            .subscribeOn(Schedulers.io())
    }

    override fun getPerson(id: Int): Single<PersonData> {
        return peopleDao.getPerson(id).map(PersonLocalMapper::mapToData)
            .subscribeOn(Schedulers.io())
    }
}