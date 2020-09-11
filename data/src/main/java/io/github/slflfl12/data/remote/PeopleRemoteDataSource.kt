package io.github.slflfl12.data.remote

import io.github.slflfl12.data.model.MovieData
import io.github.slflfl12.data.model.PersonData
import io.github.slflfl12.data.model.PersonDetailData
import io.github.slflfl12.data.model.TvData
import io.reactivex.Single

interface PeopleRemoteDataSource {

    fun getPopularPeople(page: Int): Single<List<PersonData>>

    fun getPersonDetail(id: Int): Single<PersonDetailData>

    fun getPersonMovie(id: Int): Single<List<MovieData>>

    fun getPersonTv(id: Int): Single<List<TvData>>

}