package io.github.slflfl12.data.remote

import io.github.slflfl12.data.model.PersonData
import io.github.slflfl12.data.model.PersonDetailData
import io.reactivex.Single

interface PeopleRemoteDataSource {

    fun getPopularPeople(page: Int): Single<List<PersonData>>

    fun getPersonDetail(id: Int): Single<PersonDetailData>
}