package io.github.slflfl12.remote.source

import io.github.slflfl12.data.model.MovieData
import io.github.slflfl12.data.model.PersonData
import io.github.slflfl12.data.model.PersonDetailData
import io.github.slflfl12.data.model.TvData
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


    override fun getPersonMovie(id: Int): Single<List<MovieData>> {
        return peopleApiService.getPersonMovie(id).map {
            it.personMovie.map {
                MovieData(
                    page = 0,
                    keywords = null,
                    reviews = null,
                    videos = null,
                    adult = it.adult,
                    backdropPath = it.backdropPath,
                    genreIds = it.genreIds,
                    id = it.id,
                    originalLanguage = it.originalLanguage,
                    originalTitle = it.originalTitle,
                    overview = it.overview,
                    popularity = it.popularity,
                    posterPath = it.posterPath,
                    releaseDate = it.releaseDate,
                    title = it.title,
                    video = it.video,
                    voteAverage = it.voteAverage,
                    voteCount = it.voteCount,
                    favorite = false
                )
            }
        }.subscribeOn(Schedulers.io())
    }


    override fun getPersonTv(id: Int): Single<List<TvData>> {
        return peopleApiService.getPersonTv(id).map {
            it.personTv.map {
                TvData(
                    page = 0,
                    keywords = null,
                    reviews = null,
                    videos = null,
                    backdropPath = it.backdropPath,
                    firstAirDate = it.firstAirDate,
                    genreIds = it.genreIds,
                    id = it.id,
                    name = it.name,
                    originCountry = it.originCountry,
                    originalLanguage = it.originalLanguage,
                    originalName = it.originalName,
                    overview = it.overview,
                    popularity = it.popularity,
                    posterPath = it.posterPath,
                    voteAverage = it.voteAverage,
                    voteCount = it.voteCount,
                    favorite = false
                )
            }
        }
    }
}