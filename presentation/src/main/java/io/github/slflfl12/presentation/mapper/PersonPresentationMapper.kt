package io.github.slflfl12.presentation.mapper

import io.github.slflfl12.domain.model.PersonModel
import io.github.slflfl12.presentation.model.PersonPresentationModel

object PersonPresentationMapper : PresentationMapper<PersonPresentationModel, PersonModel> {

    override fun mapToDomain(from: PersonPresentationModel): PersonModel {
        return PersonModel(
            page = from.page,
            adult = from.adult,
            personDetailModel = from.personDetailPresentationModel?.let {
                PersonDetailPresentationMapper.mapToDomain(
                    it
                )
            },
            gender = from.gender,
            id = from.id,
            name = from.name,
            popularity = from.popularity,
            profilePath = from.profilePath
        )
    }

    override fun mapToPresentation(from: PersonModel): PersonPresentationModel {
        return PersonPresentationModel(
            page = from.page,
            adult = from.adult,
            personDetailPresentationModel = from.personDetailModel?.let {
                PersonDetailPresentationMapper.mapToPresentation(
                    it
                )
            },
            gender = from.gender,
            id = from.id,
            name = from.name,
            popularity = from.popularity,
            profilePath = from.profilePath
        )
    }
}