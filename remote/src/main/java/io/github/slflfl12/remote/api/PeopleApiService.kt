package io.github.slflfl12.remote.api

import io.github.slflfl12.remote.model.PeopleResponse
import io.github.slflfl12.remote.model.PersonDetail
import io.github.slflfl12.remote.model.PersonMovieResponse
import io.github.slflfl12.remote.model.PersonTvResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PeopleApiService {

    @GET("/3/person/popular?language=en")
    fun getPopularPeople(@Query("page") page: Int): Single<PeopleResponse>

    @GET("/3/person/{person_id}/movie_credits")
    fun getPersonMovie(@Path("person_id")id: Int): Single<PersonMovieResponse>

    @GET("/3/person/{person_id}/tv_credits")
    fun getPersonTv(@Path("person_id")id: Int): Single<PersonTvResponse>

    @GET("/3/person/{person_id}")
    fun getPersonDetail(@Path("person_id") id: Int): Single<PersonDetail>
}