package io.github.slflfl12.remote.source

import io.github.slflfl12.data.model.PersonData
import io.github.slflfl12.data.model.PersonDetailData
import io.github.slflfl12.data.remote.PeopleRemoteDataSource
import io.github.slflfl12.remote.api.PeopleApiService
import io.github.slflfl12.remote.mapper.PersonDetailRemoteMapper
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class PeopleRemoteDataSourceImpl(
    private val peopleApiService: PeopleApiService
) : PeopleRemoteDataSource {

    override fun getPopularPeople(page: Int): Single<List<PersonData>> {
        return peopleApiService.getPopularPeople(page).map {
            it.people.map {
                PersonData(
                    page,
                    adult = it.adult,
                    id = it.id,
                    gender = it.gender,
                    name = it.name,
                    popularity = it.popularity,
                    profilePath = it.profilePath
                )
            }
        }.subscribeOn(Schedulers.io())
    }

    override fun getPersonDetail(id: Int): Single<PersonDetailData> {
        return peopleApiService.getPersonDetail(id).map(PersonDetailRemoteMapper::mapToData)
            .subscribeOn(Schedulers.io())
    }

}