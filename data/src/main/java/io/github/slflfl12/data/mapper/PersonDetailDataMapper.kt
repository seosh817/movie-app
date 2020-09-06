package io.github.slflfl12.data.mapper

import io.github.slflfl12.data.model.PersonDetailData
import io.github.slflfl12.domain.model.PersonDetailModel

object PersonDetailDataMapper: DataMapper<PersonDetailData, PersonDetailModel> {

    override fun mapToData(from: PersonDetailModel): PersonDetailData {
        return PersonDetailData(
            adult = from.adult,
            alsoKnownAs = from.alsoKnownAs,
            biography = from.biography,
            birthday = from.birthday,
            deathday = from.deathday,
            gender = from.gender,
            homepage = from.homepage,
            id = from.id,
            imdbId = from.imdbId,
            knownForDepartment = from.knownForDepartment,
            name = from.name,
            placeOfBirth = from.placeOfBirth,
            popularity = from.popularity,
            profilePath = from.profilePath
        )
    }

    override fun mapToDomain(from: PersonDetailData): PersonDetailModel {
        return PersonDetailModel(
            adult = from.adult,
            alsoKnownAs = from.alsoKnownAs,
            biography = from.biography,
            birthday = from.birthday,
            deathday = from.deathday,
            gender = from.gender,
            homepage = from.homepage,
            id = from.id,
            imdbId = from.imdbId,
            knownForDepartment = from.knownForDepartment,
            name = from.name,
            placeOfBirth = from.placeOfBirth,
            popularity = from.popularity,
            profilePath = from.profilePath
        )
    }
}