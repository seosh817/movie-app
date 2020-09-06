package io.github.slflfl12.local.mapper

import io.github.slflfl12.data.model.PersonDetailData
import io.github.slflfl12.local.model.PersonDetailEntity

object PersonDetailLocalMapper : LocalMapper<PersonDetailEntity, PersonDetailData> {

    override fun mapToData(from: PersonDetailEntity): PersonDetailData {
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

    override fun mapToLocal(from: PersonDetailData): PersonDetailEntity {
        return PersonDetailEntity(
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