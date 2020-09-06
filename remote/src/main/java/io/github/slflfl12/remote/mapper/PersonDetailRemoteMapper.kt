package io.github.slflfl12.remote.mapper

import io.github.slflfl12.data.model.PersonDetailData
import io.github.slflfl12.remote.model.PersonDetail

object PersonDetailRemoteMapper: RemoteMapper<PersonDetail, PersonDetailData> {

    override fun mapToData(from: PersonDetail): PersonDetailData {
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
}