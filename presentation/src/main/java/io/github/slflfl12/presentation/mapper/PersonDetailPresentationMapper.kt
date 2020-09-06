package io.github.slflfl12.presentation.mapper

import io.github.slflfl12.domain.model.PersonDetailModel
import io.github.slflfl12.presentation.model.PersonDetailPresentationModel

object PersonDetailPresentationMapper: PresentationMapper<PersonDetailPresentationModel, PersonDetailModel> {

    override fun mapToDomain(from: PersonDetailPresentationModel): PersonDetailModel {
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

    override fun mapToPresentation(from: PersonDetailModel): PersonDetailPresentationModel {
        return PersonDetailPresentationModel(
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