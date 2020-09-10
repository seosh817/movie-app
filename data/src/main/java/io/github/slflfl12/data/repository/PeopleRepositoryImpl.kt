package io.github.slflfl12.data.repository

import io.github.slflfl12.data.local.PeopleLocalDataSource
import io.github.slflfl12.data.mapper.PersonDataMapper
import io.github.slflfl12.data.mapper.PersonDetailDataMapper
import io.github.slflfl12.data.remote.PeopleRemoteDataSource
import io.github.slflfl12.domain.model.PersonDetailModel
import io.github.slflfl12.domain.model.PersonModel
import io.github.slflfl12.domain.repository.PeopleRepository
import io.reactivex.Completable
import io.reactivex.Single

class PeopleRepositoryImpl(
    private val peopleLocalDataSource: PeopleLocalDataSource,
    private val peopleRemoteDataSource: PeopleRemoteDataSource
) : PeopleRepository {

    override fun insertPerson(person: PersonModel): Completable {
        return peopleLocalDataSource.insertPerson(PersonDataMapper.mapToData(person))
    }

    override fun updatePerson(person: PersonModel): Completable {
        return peopleLocalDataSource.updatePerson(PersonDataMapper.mapToData(person))
    }

    override fun getLocalPerson(id: Int): Single<PersonModel> {
        return peopleLocalDataSource.getPerson(id).map(PersonDataMapper::mapToDomain)
    }

    override fun getPopularPeople(page: Int): Single<List<PersonModel>> {
        return peopleRemoteDataSource.getPopularPeople(page)
            .map { it.map(PersonDataMapper::mapToDomain) }
    }

    override fun getPersonDetail(id: Int): Single<PersonDetailModel> {
        return peopleRemoteDataSource.getPersonDetail(id).map(PersonDetailDataMapper::mapToDomain)
    }


}